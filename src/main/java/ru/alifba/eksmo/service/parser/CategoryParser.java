package ru.alifba.eksmo.service.parser;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.alifba.eksmo.model.Category;
import ru.alifba.eksmo.model.Config;
import ru.alifba.eksmo.model.GuidIdentifiable;
import ru.alifba.eksmo.model.dto.niche.NicheDto;
import ru.alifba.eksmo.model.dto.niche.NicheXml;
import ru.alifba.eksmo.model.dto.subject.SubjectDto;
import ru.alifba.eksmo.model.dto.subject.SubjectXml;
import ru.alifba.eksmo.service.parser.xml.NicheXmlParser;
import ru.alifba.eksmo.service.parser.xml.SubjectXmlParser;
import ru.alifba.eksmo.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static ru.alifba.eksmo.util.CollectionUtils.buildParentChildrenRelations;

@Service
@RequiredArgsConstructor
public class CategoryParser {

    private final SubjectXmlParser subjectsXmlDirParser;
    private final NicheXmlParser nichesXmlDirParser;

    public Map<String, Category> parse(Config config) {
        List<SubjectXml> subjectXmls = subjectsXmlDirParser.parse(config.getSubjectXmlsPath());
        List<NicheXml> nicheXmls = nichesXmlDirParser.parse(config.getNicheXmlsPath());
        return getCategoryMap(subjectXmls, nicheXmls);
    }

    private Map<String, Category> getCategoryMap(List<SubjectXml> subjectXmls, List<NicheXml> nicheXmls) {
        Map<String, Category> subjectMap = buildCategoryWithoutParentsMap(subjectXmls,
            this::getDtoListFromXml, this::getCategoryFromDto);
        Map<String, Category> nicheMap = buildCategoryWithoutParentsMap(nicheXmls,
            this::getDtoListFromXml, this::getCategoryFromDto);
        Map<String, Category> categoryMap = CollectionUtils.combine(subjectMap, nicheMap);
        fillParentCategories(categoryMap);
        fillChildrenCategories(categoryMap);
        return categoryMap;
    }

    private void fillParentCategories(Map<String, Category> categoryMap) {
        categoryMap.forEach((guid, category) -> {
            if (categoryMap.containsKey(category.getParentGuid())) {
                category.setParent(categoryMap.get(category.getParentGuid()));
            }
        });
    }

    private void fillChildrenCategories(Map<String, Category> categoryMap) {
        Map<String, List<Category>> parentChildren = buildParentChildrenRelations(categoryMap);
        parentChildren.forEach((guid, list) -> categoryMap.get(guid).setChildren(list));
    }

    private <XML, DTO extends GuidIdentifiable> Map<String, Category> buildCategoryWithoutParentsMap(
        List<XML> xmls,
        Function<XML, List<DTO>> getDtoList,
        Function<DTO, Category> getFromDto) {
        Map<String, Category> hashMap = new HashMap<>();
        xmls.forEach(xml -> getDtoList.apply(xml).forEach(dto -> {
            hashMap.put(dto.getGuid(), getFromDto.apply(dto));
        }));
        return hashMap;
    }

    private List<SubjectDto> getDtoListFromXml(SubjectXml xml) {
        return xml.getSubjects().getSubjects();
    }

    private Category getCategoryFromDto(SubjectDto dto) {
        return new Category(dto.getGuid(), dto.getParentGuid(), dto.getName());
    }

    private List<NicheDto> getDtoListFromXml(NicheXml xml) {
        return xml.getNiches().getNiches();
    }

    private Category getCategoryFromDto(NicheDto dto) {
        return new Category(dto.getGuid(), dto.getParentGuid(), dto.getName());
    }

}
