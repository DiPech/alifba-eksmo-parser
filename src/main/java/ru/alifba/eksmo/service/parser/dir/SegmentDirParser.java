package ru.alifba.eksmo.service.parser.dir;

import org.springframework.stereotype.Service;
import ru.alifba.eksmo.model.dto.xml.segment.SegmentXml;
import ru.alifba.eksmo.model.dto.xml.segment.SegmentsFileXml;
import ru.alifba.eksmo.model.dto.xml.segment.SegmentsXml;

@Service
public class SegmentDirParser extends DirParser<SegmentsFileXml> {

    @Override
    protected Class[] xmlClasses() {
        return new Class[]{SegmentsFileXml.class, SegmentsXml.class, SegmentXml.class};
    }

}
