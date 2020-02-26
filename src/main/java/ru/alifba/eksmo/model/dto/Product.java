package ru.alifba.eksmo.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class Product {

    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "guid")
    private String guid;

    @XmlElement(name = "isbnn")
    private String isbn;

    @XmlElement(name = "brgew")
    private String weight;

    @XmlElement(name = "price")
    private Float price;

    @XmlElement(name = "qtypg")
    private Float pagesCount;

    @XmlElement(name = "width")
    private Float width;

    @XmlElement(name = "height")
    private Float height;

    @XmlElement(name = "depth")
    private Float depth;

    @XmlElement(name = "source_picture")
    private String img1;

    @XmlElement(name = "source_cover4")
    private String img2;

}
