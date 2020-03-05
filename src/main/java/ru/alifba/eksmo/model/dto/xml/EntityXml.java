package ru.alifba.eksmo.model.dto.xml;

import lombok.Getter;
import lombok.Setter;
import ru.alifba.eksmo.model.GuidIdentifiable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class EntityXml implements GuidIdentifiable {

    @XmlElement(name = "guid")
    private String guid;

    @XmlElement(name = "name")
    private String name;

}
