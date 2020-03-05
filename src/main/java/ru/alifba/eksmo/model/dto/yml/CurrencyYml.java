package ru.alifba.eksmo.model.dto.yml;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@Getter
@RequiredArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class CurrencyYml {

    @XmlAttribute(name = "id")
    private final String id;

    @XmlAttribute(name = "rate")
    private final String rate;

}
