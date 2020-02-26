package ru.alifba.eksmo.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement(name = "result")
@XmlAccessorType(XmlAccessType.FIELD)
public class Xml {

    @XmlElement(name = "products")
    private Products products;

}
