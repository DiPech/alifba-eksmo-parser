package ru.alifba.eksmo.service.parser.xml;

import org.springframework.stereotype.Service;
import ru.alifba.eksmo.model.Config;
import ru.alifba.eksmo.model.dto.Dto;
import ru.alifba.eksmo.service.parser.dir.DirParser;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public abstract class AbstractXmlParser<XML, DTO extends Dto, ENTITY> implements XmlParser {

    public Map<String, ENTITY> parse(Config config) {
        List<XML> xmls = getDirParser().parse(getDirPath(config));
        return getMap(xmls);
    }

    private Map<String, ENTITY> getMap(List<XML> xmls) {
        Map<String, ENTITY> map = new HashMap<>();
        xmls.forEach(xml -> getDtoList(xml).forEach(dto -> map.put(dto.getGuid(), convertDtoToEntity(dto))));
        return map;
    }

    protected abstract DirParser<XML> getDirParser();

    protected abstract Path getDirPath(Config config);

    protected abstract List<DTO> getDtoList(XML xml);

    protected abstract ENTITY convertDtoToEntity(DTO dto);

}
