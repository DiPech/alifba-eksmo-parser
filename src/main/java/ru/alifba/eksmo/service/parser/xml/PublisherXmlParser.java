package ru.alifba.eksmo.service.parser.xml;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.alifba.eksmo.model.Config;
import ru.alifba.eksmo.model.Publisher;
import ru.alifba.eksmo.model.dto.publisher.PublisherDto;
import ru.alifba.eksmo.model.dto.publisher.PublisherXml;
import ru.alifba.eksmo.service.parser.dir.DirParser;
import ru.alifba.eksmo.service.parser.dir.PublisherDirParser;

import java.nio.file.Path;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PublisherXmlParser extends AbstractXmlParser<PublisherXml, PublisherDto, Publisher> {

    private final PublisherDirParser publisherDirParser;

    @Override
    protected DirParser<PublisherXml> getDirParser() {
        return publisherDirParser;
    }

    @Override
    protected Path getDirPath(Config config) {
        return config.getPublisherXmlsPath();
    }

    @Override
    protected List<PublisherDto> getDtoList(PublisherXml xml) {
        return xml.getPublishers().getPublishers();
    }

    @Override
    protected Publisher convertDtoToEntity(PublisherDto dto) {
        return new Publisher(dto.getGuid(), dto.getName());
    }

}
