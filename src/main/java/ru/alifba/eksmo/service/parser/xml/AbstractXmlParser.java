package ru.alifba.eksmo.service.parser.xml;

import org.springframework.stereotype.Service;
import ru.alifba.eksmo.model.Config;
import ru.alifba.eksmo.model.dto.xml.EntityXml;
import ru.alifba.eksmo.service.parser.dir.DirParser;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public abstract class AbstractXmlParser<FILE_XML, ENTITY_XML extends EntityXml, ENTITY> implements XmlParser {

    public Map<String, ENTITY> parse(Config config) {
        List<FILE_XML> xmls = getDirParser().parse(getDirPath(config));
        return getMap(xmls);
    }

    private Map<String, ENTITY> getMap(List<FILE_XML> xmls) {
        Map<String, ENTITY> map = new HashMap<>();
        xmls.forEach(xml -> getEntityXmlList(xml).forEach(
            entityXml -> map.put(entityXml.getGuid(), convertEntityXmlToEntity(entityXml))
        ));
        return map;
    }

    protected abstract DirParser<FILE_XML> getDirParser();

    protected abstract Path getDirPath(Config config);

    protected abstract List<ENTITY_XML> getEntityXmlList(FILE_XML fileXml);

    protected abstract ENTITY convertEntityXmlToEntity(ENTITY_XML entityXml);

}
