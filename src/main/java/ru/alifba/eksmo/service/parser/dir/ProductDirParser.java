package ru.alifba.eksmo.service.parser.dir;

import org.springframework.stereotype.Service;
import ru.alifba.eksmo.model.dto.product.ProductDto;
import ru.alifba.eksmo.model.dto.product.ProductXml;
import ru.alifba.eksmo.model.dto.product.ProductsDto;

@Service
public class ProductDirParser extends DirParser<ProductXml> {

    @Override
    protected Class[] xmlClasses() {
        return new Class[]{ProductXml.class, ProductsDto.class, ProductDto.class};
    }
}
