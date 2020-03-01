package ru.alifba.eksmo.service.parser.dir;

import org.springframework.stereotype.Service;
import ru.alifba.eksmo.model.dto.segment.SegmentDto;
import ru.alifba.eksmo.model.dto.segment.SegmentXml;
import ru.alifba.eksmo.model.dto.segment.SegmentsDto;

@Service
public class SegmentDirParser extends DirParser<SegmentXml> {

    @Override
    protected Class[] xmlClasses() {
        return new Class[]{SegmentXml.class, SegmentsDto.class, SegmentDto.class};
    }

}
