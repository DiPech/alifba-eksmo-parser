package ru.alifba.eksmo.model.dto.product;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductDto {

    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "guid")
    private String guid;

    @XmlElement(name = "sbjct")
    private SubjectDto sbjct;

    @XmlElement(name = "isbnn")
    private String isbnn;

    @XmlElement(name = "brgew")
    private String brgew;

    @XmlElement(name = "price")
    private Float price;

    @XmlElement(name = "qtypg")
    private Integer qtypg;

    @XmlElement(name = "width")
    private Float width;

    @XmlElement(name = "height")
    private Float height;

    @XmlElement(name = "depth")
    private Float depth;

    @XmlElement(name = "source_picture")
    private String sourcePicture;

    @XmlElement(name = "source_cover4")
    private String sourceCover4;

}
