package ru.alifba.eksmo.service.parser;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.alifba.eksmo.model.Config;
import ru.alifba.eksmo.model.Publisher;
import ru.alifba.eksmo.model.dto.publisher.PublisherDto;
import ru.alifba.eksmo.model.dto.publisher.PublisherXml;
import ru.alifba.eksmo.service.parser.xml.PublisherXmlParser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PublisherParser {

    private final PublisherXmlParser publisherXmlParser;

    public Map<String, Publisher> parse(Config config) {
        List<PublisherXml> publisherXml = publisherXmlParser.parse(config.getPublisherXmlsPath());
        return getPublisherMap(publisherXml);
    }

    private Map<String, Publisher> getPublisherMap(List<PublisherXml> xmls) {
        Map<String, Publisher> hashMap = new HashMap<>();
        xmls.forEach(xml -> xml.getPublishers().getPublishers().forEach(dto -> {
            hashMap.put(dto.getGuid(), publisherFromDto(dto));
        }));
        return hashMap;
    }

    private Publisher publisherFromDto(PublisherDto dto) {
        return new Publisher(dto.getGuid(), dto.getName());
    }

}
