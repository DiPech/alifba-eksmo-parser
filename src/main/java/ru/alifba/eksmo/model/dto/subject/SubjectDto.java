package ru.alifba.eksmo.model.dto.subject;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class SubjectDto {

    @XmlElement(name = "guid")
    private String guid;

    @XmlElement(name = "parent_guid")
    private String parentGuid;

    @XmlElement(name = "name")
    private String name;

}
