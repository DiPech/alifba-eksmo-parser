package ru.alifba.eksmo.model.dto;

import lombok.Getter;
import ru.alifba.eksmo.model.GuidIdentifiable;

import javax.xml.bind.annotation.XmlElement;

@Getter
public abstract class Dto implements GuidIdentifiable {

    @XmlElement(name = "guid")
    private String guid;

}
