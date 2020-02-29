package ru.alifba.eksmo.service.parser;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.alifba.eksmo.model.Category;
import ru.alifba.eksmo.model.Config;
import ru.alifba.eksmo.model.GuidIdentifiable;
import ru.alifba.eksmo.model.dto.niche.NicheDto;
import ru.alifba.eksmo.model.dto.niche.NicheXml;
import ru.alifba.eksmo.model.dto.segment.SegmentDto;
import ru.alifba.eksmo.model.dto.segment.SegmentXml;
import ru.alifba.eksmo.model.dto.subject.SubjectDto;
import ru.alifba.eksmo.model.dto.subject.SubjectXml;
import ru.alifba.eksmo.service.parser.xml.NicheXmlParser;
import ru.alifba.eksmo.service.parser.xml.SegmentXmlParser;
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

    private final SubjectXmlParser subjectXmlParser;
    private final NicheXmlParser nicheXmlParser;
    private final SegmentXmlParser segmentXmlParser;

    public Map<String, Category> parse(Config config) {
        List<SubjectXml> subjectXmls = subjectXmlParser.parse(config.getSubjectXmlsPath());
        List<NicheXml> nicheXmls = nicheXmlParser.parse(config.getNicheXmlsPath());
        List<SegmentXml> segmentXmls = segmentXmlParser.parse(config.getSegmentXmlsPath());
        return getCategoryMap(subjectXmls, nicheXmls, segmentXmls);
    }

    private Map<String, Category> getCategoryMap(
        List<SubjectXml> subjectXmls,
        List<NicheXml> nicheXmls,
        List<SegmentXml> segmentXmls) {
        Map<String, Category> subjectMap = buildCategoryWithoutParentsMap(subjectXmls,
            this::getDtoListFromXml, this::getCategoryFromDto);
        Map<String, Category> nicheMap = buildCategoryWithoutParentsMap(nicheXmls,
            this::getDtoListFromXml, this::getCategoryFromDto);
        Map<String, Category> segmentMap = buildCategoryWithoutParentsMap(segmentXmls,
            this::getDtoListFromXml, this::getCategoryFromDto);
        Map<String, Category> categoryMap = CollectionUtils.combineMaps(subjectMap, nicheMap, segmentMap);
        fixRootCategories(categoryMap);
        fillParentCategories(categoryMap);
        fillChildrenCategories(categoryMap);
        return categoryMap;
    }

    /*
     * Sometimes there are presented GUIDs without real categories in the data. So drop them.
     */
    private void fixRootCategories(Map<String, Category> categoryMap) {
        categoryMap.forEach((guid, category) -> {
            String parentGuid = category.getParentGuid();
            if (parentGuid == null || parentGuid.length() == 0) {
                return;
            }
            if (!categoryMap.containsKey(parentGuid)) {
                category.setParentGuid(null);
                category.setParent(null);
            }
        });
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

    private List<SegmentDto> getDtoListFromXml(SegmentXml xml) {
        return xml.getSegments().getSegments();
    }

    private Category getCategoryFromDto(SegmentDto dto) {
        return new Category(dto.getGuid(), dto.getParentGuid(), dto.getName());
    }

}
