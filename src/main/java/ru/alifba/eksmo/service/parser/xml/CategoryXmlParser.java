package ru.alifba.eksmo.service.parser.xml;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.alifba.eksmo.model.Category;
import ru.alifba.eksmo.model.Config;
import ru.alifba.eksmo.util.CollectionUtils;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class CategoryXmlParser implements XmlParser<Category> {

    private final NicheXmlParser nicheXmlParser;
    private final SegmentXmlParser segmentXmlParser;
    private final SubjectXmlParser subjectXmlParser;

    @Override
    public Map<String, Category> parse(Config config) {
        Map<String, Category> map = CollectionUtils.combineMaps(
            nicheXmlParser.parse(config),
            segmentXmlParser.parse(config),
            subjectXmlParser.parse(config)
        );
        fixRootCategories(map);
        return map;
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

}
