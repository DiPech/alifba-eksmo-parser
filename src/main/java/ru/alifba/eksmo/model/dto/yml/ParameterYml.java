package ru.alifba.eksmo.model.dto.yml;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

@Getter
@XmlAccessorType(XmlAccessType.FIELD)
@RequiredArgsConstructor
public class ParameterYml {

    @XmlAttribute(name = "name")
    private final String name;

    @XmlValue
    private final String value;

}
