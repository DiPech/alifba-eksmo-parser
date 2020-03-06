package ru.alifba.eksmo.model.dto.yml;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@Getter
@RequiredArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class ShopYml {

    @XmlElement(name = "name")
    private final String name;

    @XmlElement(name = "company")
    private final String company;

    @XmlElement(name = "url")
    private final String url;

    @XmlElement(name = "currencies")
    private final CurrenciesYml currencies;

    @XmlElement(name = "categories")
    private final CategoriesYml categories;

    @XmlElement(name = "offers")
    private final OffersYml offers;

}
