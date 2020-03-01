package ru.alifba.eksmo.model.dto.publisher;

import lombok.Getter;
import lombok.Setter;
import ru.alifba.eksmo.model.dto.Dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class PublisherDto extends Dto {

    @XmlElement(name = "name")
    private String name;

}
