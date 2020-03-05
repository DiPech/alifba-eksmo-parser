package ru.alifba.eksmo.model.dto.xml.product;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class ReferenceEntityXml {

    @XmlAttribute(name = "guid")
    private String guid;

}
