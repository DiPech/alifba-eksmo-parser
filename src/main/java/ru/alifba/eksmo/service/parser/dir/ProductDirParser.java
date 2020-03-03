package ru.alifba.eksmo.service.parser.dir;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.alifba.eksmo.model.dto.product.ProductDto;
import ru.alifba.eksmo.model.dto.product.ProductXml;
import ru.alifba.eksmo.model.dto.product.ProductsDto;
import ru.alifba.eksmo.service.cleaner.ProductXmlCleaner;

@Service
@RequiredArgsConstructor
public class ProductDirParser extends DirParser<ProductXml> {

    private final ProductXmlCleaner cleaner;

    @Override
    protected Class[] xmlClasses() {
        return new Class[]{ProductXml.class, ProductsDto.class, ProductDto.class};
    }

    @Override
    protected ProductXml preprocess(ProductXml productXml) {
        return cleaner.clean(productXml);
    }
}
