package ru.alifba.eksmo.model.dto;

import lombok.Getter;
import lombok.Setter;
import ru.alifba.eksmo.model.TreeGuidIdentifiable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class TreeDto extends Dto implements TreeGuidIdentifiable {

    @XmlElement(name = "parent_guid")
    private String parentGuid;

}
