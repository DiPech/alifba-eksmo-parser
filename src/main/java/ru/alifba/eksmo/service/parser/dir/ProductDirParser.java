package ru.alifba.eksmo.service.parser.dir;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.alifba.eksmo.model.dto.xml.product.ProductXml;
import ru.alifba.eksmo.model.dto.xml.product.ProductsFileXml;
import ru.alifba.eksmo.model.dto.xml.product.ProductsXml;
import ru.alifba.eksmo.service.cleaner.ProductXmlCleaner;

@Service
@RequiredArgsConstructor
public class ProductDirParser extends DirParser<ProductsFileXml> {

    private final ProductXmlCleaner xmlCleaner;

    @Override
    protected Class[] xmlClasses() {
        return new Class[]{ProductsFileXml.class, ProductsXml.class, ProductXml.class};
    }

    @Override
    protected ProductsFileXml preprocess(ProductsFileXml fileXml) {
        return xmlCleaner.clean(fileXml);
    }

}
