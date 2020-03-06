package ru.alifba.eksmo.model.dto.yml;

import lombok.Builder;
import lombok.Getter;

import javax.xml.bind.annotation.*;
import java.util.List;

@Getter
@XmlAccessorType(XmlAccessType.FIELD)
public class OfferYml {

    @XmlAttribute(name = "id")
    private final String id;

    @XmlElement(name = "currencyId")
    private final String currencyId;

    @XmlElement(name = "categoryId")
    private final String categoryId;

    @XmlElement(name = "name")
    private final String name;

    @XmlElement(name = "description")
    private final String description;

    @XmlElement(name = "vendor")
    private final String vendor;

    @XmlElement(name = "price")
    private final Float price;

    @XmlElement(name = "weight")
    private final Float weight;

    @XmlElement(name = "available")
    private final Integer available;

    @XmlElement(name = "picture")
    private final List<String> pictures;

    @XmlElement(name = "param")
    private final List<ParameterYml> parameters;

    @Builder
    public OfferYml(String id, String currencyId, String categoryId, String name, String description, String vendor,
                    Float price, Float weight, Integer available, List<String> pictures,
                    List<ParameterYml> parameters) {
        this.id = id;
        this.currencyId = currencyId;
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
        this.vendor = vendor;
        this.price = price;
        this.weight = weight;
        this.available = available;
        this.pictures = pictures;
        this.parameters = parameters;
    }
}
