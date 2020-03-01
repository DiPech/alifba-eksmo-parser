package ru.alifba.eksmo.service.parser.xml;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.alifba.eksmo.model.Config;
import ru.alifba.eksmo.model.dto.segment.SegmentDto;
import ru.alifba.eksmo.model.dto.segment.SegmentXml;
import ru.alifba.eksmo.service.parser.dir.DirParser;
import ru.alifba.eksmo.service.parser.dir.SegmentDirParser;

import java.nio.file.Path;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SegmentXmlParser extends AbstractTreeEntityXmlParser<SegmentXml, SegmentDto> {

    private final SegmentDirParser segmentDirParser;

    @Override
    protected DirParser<SegmentXml> getDirParser() {
        return segmentDirParser;
    }

    @Override
    protected Path getDirPath(Config config) {
        return config.getSegmentXmlsPath();
    }

    @Override
    protected List<SegmentDto> getDtoList(SegmentXml xml) {
        return xml.getSegments().getSegments();
    }

}
