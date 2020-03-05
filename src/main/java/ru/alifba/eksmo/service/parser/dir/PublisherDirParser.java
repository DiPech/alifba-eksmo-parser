package ru.alifba.eksmo.service.parser.dir;

import org.springframework.stereotype.Service;
import ru.alifba.eksmo.model.dto.xml.publisher.PublisherXml;
import ru.alifba.eksmo.model.dto.xml.publisher.PublishersFileXml;
import ru.alifba.eksmo.model.dto.xml.publisher.PublishersXml;

@Service
public class PublisherDirParser extends DirParser<PublishersFileXml> {

    @Override
    protected Class[] xmlClasses() {
        return new Class[]{PublishersFileXml.class, PublishersXml.class, PublisherXml.class};
    }

}
