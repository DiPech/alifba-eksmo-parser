package ru.alifba.eksmo.model.dto.xml.publisher;

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
public class PublishersFileXml {

    @XmlElement(name = "publishers")
    private PublishersXml publishers;

}
