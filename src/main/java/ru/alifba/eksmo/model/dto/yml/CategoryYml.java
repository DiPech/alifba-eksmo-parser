package ru.alifba.eksmo.model.dto.yml;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

@Getter
@RequiredArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryYml {

    @XmlAttribute(name = "id")
    private final String id;

    @XmlAttribute(name = "parentId")
    private final String parentId;

    @XmlValue
    private final String name;

}
