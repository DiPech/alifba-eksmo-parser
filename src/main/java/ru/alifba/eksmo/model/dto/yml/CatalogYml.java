package ru.alifba.eksmo.model.dto.yml;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.alifba.eksmo.service.adapter.YmlLocalDateAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;

@Getter
@Setter
@XmlRootElement(name = "yml_catalog")
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
@AllArgsConstructor
public class CatalogYml {

    @XmlElement(name = "shop")
    private ShopYml shop;

    @XmlAttribute(name = "date")
    @XmlJavaTypeAdapter(value = YmlLocalDateAdapter.class)
    private LocalDateTime datetime;

}
