package ru.alifba.eksmo.model.dto.xml.product;

import lombok.Getter;
import lombok.Setter;
import ru.alifba.eksmo.model.dto.xml.EntityXml;
import ru.alifba.eksmo.service.adapter.XmlLocalDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductXml extends EntityXml {

    @XmlElement(name = "sbjct")
    private ReferenceEntityXml sbjct;

    @XmlElement(name = "niche")
    private ReferenceEntityXml niche;

    @XmlElement(name = "sgmnt")
    private ReferenceEntityXml sgmnt;

    @XmlElement(name = "publi")
    private ReferenceEntityXml publi;

    @XmlElement(name = "isbnn")
    private String isbnn;

    @XmlElement(name = "prodcode")
    private String prodcode;

    @XmlElement(name = "detail_text")
    private String detailText;

    @XmlElement(name = "price_authors")
    private String priceAuthors;

    @XmlElement(name = "brgew")
    private Float brgew;

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

    @XmlElement(name = "ldate_d")
    @XmlJavaTypeAdapter(value = XmlLocalDateAdapter.class)
    private LocalDate lDateD;

}
