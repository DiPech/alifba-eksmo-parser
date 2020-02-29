package ru.alifba.eksmo.service.parser.xml;

import org.springframework.stereotype.Service;
import ru.alifba.eksmo.model.dto.product.ProductDto;
import ru.alifba.eksmo.model.dto.product.ProductXml;
import ru.alifba.eksmo.model.dto.product.ProductsDto;

@Service
public class ProductXmlParser extends XmlParser<ProductXml> {

    @Override
    protected Class[] xmlClasses() {
        return new Class[]{ProductXml.class, ProductsDto.class, ProductDto.class};
    }
}
