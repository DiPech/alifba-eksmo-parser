package ru.alifba.eksmo.model.dto.yml;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

@Getter
@RequiredArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class OffersYml {

    @XmlElement(name = "offer")
    private final List<OfferYml> offers;

}
