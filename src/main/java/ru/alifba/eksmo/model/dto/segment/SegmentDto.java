package ru.alifba.eksmo.model.dto.segment;

import lombok.Getter;
import lombok.Setter;
import ru.alifba.eksmo.model.dto.Dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class SegmentDto extends Dto {

    @XmlElement(name = "parent_guid")
    private String parentGuid;

    @XmlElement(name = "name")
    private String name;

}
