package ru.alifba.eksmo.model.dto.xml.segment;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class SegmentsXml {

    @XmlElement(name = "segment")
    private List<SegmentXml> segments;

}
