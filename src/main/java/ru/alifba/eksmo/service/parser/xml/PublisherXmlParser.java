package ru.alifba.eksmo.service.parser.xml;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.alifba.eksmo.model.Config;
import ru.alifba.eksmo.model.Publisher;
import ru.alifba.eksmo.model.dto.xml.publisher.PublisherXml;
import ru.alifba.eksmo.model.dto.xml.publisher.PublishersFileXml;
import ru.alifba.eksmo.service.parser.dir.DirParser;
import ru.alifba.eksmo.service.parser.dir.PublisherDirParser;

import java.nio.file.Path;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PublisherXmlParser extends AbstractXmlParser<PublishersFileXml, PublisherXml, Publisher> {

    private final PublisherDirParser publisherDirParser;

    @Override
    protected DirParser<PublishersFileXml> getDirParser() {
        return publisherDirParser;
    }

    @Override
    protected Path getDirPath(Config config) {
        return config.getPublisherXmlsPath();
    }

    @Override
    protected List<PublisherXml> getEntityXmlList(PublishersFileXml fileXml) {
        return fileXml.getPublishers().getPublishers();
    }

    @Override
    protected Publisher convertEntityXmlToEntity(PublisherXml entityXml) {
        return new Publisher(entityXml.getGuid(), entityXml.getName());
    }

}
