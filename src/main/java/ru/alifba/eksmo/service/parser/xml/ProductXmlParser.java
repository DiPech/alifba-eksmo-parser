package ru.alifba.eksmo.service.parser.xml;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.alifba.eksmo.model.Config;
import ru.alifba.eksmo.model.Product;
import ru.alifba.eksmo.model.dto.xml.product.ProductXml;
import ru.alifba.eksmo.model.dto.xml.product.ProductsFileXml;
import ru.alifba.eksmo.service.parser.dir.DirParser;
import ru.alifba.eksmo.service.parser.dir.ProductDirParser;

import java.nio.file.Path;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductXmlParser extends AbstractXmlParser<ProductsFileXml, ProductXml, Product> {

    private final ProductDirParser productDirParser;

    @Override
    protected DirParser<ProductsFileXml> getDirParser() {
        return productDirParser;
    }

    @Override
    protected Path getDirPath(Config config) {
        return config.getProductXmlsPath();
    }

    @Override
    protected List<ProductXml> getEntityXmlList(ProductsFileXml fileXml) {
        return fileXml.getProducts().getProducts();
    }

    @Override
    protected Product convertEntityXmlToEntity(ProductXml entityXml) {
        return Product.builder()
            .guid(entityXml.getGuid())
            .categoryGuid(entityXml.getSbjct().getGuid())
            .publisherGuid(entityXml.getPubli().getGuid())
            .name(entityXml.getName())
            .price(entityXml.getPrice())
            .weight(entityXml.getBrgew())
            .width(entityXml.getWidth())
            .height(entityXml.getHeight())
            .depth(entityXml.getDepth())
            .image(entityXml.getSourcePicture())
            .image2(entityXml.getSourceCover4())
            .pagesCount(entityXml.getQtypg())
            .lastEditionDate(entityXml.getLDateD())
            .build();
    }

}
