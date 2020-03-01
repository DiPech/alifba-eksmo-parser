package ru.alifba.eksmo.service.parser.xml;

import org.springframework.stereotype.Service;
import ru.alifba.eksmo.model.dto.publisher.PublisherDto;
import ru.alifba.eksmo.model.dto.publisher.PublisherXml;
import ru.alifba.eksmo.model.dto.publisher.PublishersDto;

@Service
public class PublisherXmlParser extends XmlParser<PublisherXml> {

    @Override
    protected Class[] xmlClasses() {
        return new Class[]{PublisherXml.class, PublishersDto.class, PublisherDto.class};
    }

}
