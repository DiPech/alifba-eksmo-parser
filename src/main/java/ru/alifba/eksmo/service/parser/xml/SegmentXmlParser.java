package ru.alifba.eksmo.service.parser.xml;

import org.springframework.stereotype.Service;
import ru.alifba.eksmo.model.dto.segment.SegmentDto;
import ru.alifba.eksmo.model.dto.segment.SegmentXml;
import ru.alifba.eksmo.model.dto.segment.SegmentsDto;

@Service
public class SegmentXmlParser extends XmlParser<SegmentXml> {

    @Override
    protected Class[] xmlClasses() {
        return new Class[]{SegmentXml.class, SegmentsDto.class, SegmentDto.class};
    }

}
