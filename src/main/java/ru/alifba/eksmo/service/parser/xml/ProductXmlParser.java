package ru.alifba.eksmo.service.parser.xml;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.alifba.eksmo.model.Config;
import ru.alifba.eksmo.model.Product;
import ru.alifba.eksmo.model.dto.product.ProductDto;
import ru.alifba.eksmo.model.dto.product.ProductXml;
import ru.alifba.eksmo.service.parser.dir.DirParser;
import ru.alifba.eksmo.service.parser.dir.ProductDirParser;

import java.nio.file.Path;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductXmlParser extends AbstractXmlParser<ProductXml, ProductDto, Product> {

    private final ProductDirParser productDirParser;

    @Override
    protected DirParser<ProductXml> getDirParser() {
        return productDirParser;
    }

    @Override
    protected Path getDirPath(Config config) {
        return config.getProductXmlsPath();
    }

    @Override
    protected List<ProductDto> getDtoList(ProductXml xml) {
        return xml.getProducts().getProducts();
    }

    @Override
    protected Product convertDtoToEntity(ProductDto dto) {
        return Product.builder()
            .guid(dto.getGuid())
            .categoryGuid(dto.getSbjct().getGuid())
            .publisherGuid(dto.getPubli().getGuid())
            .name(dto.getName())
            .price(dto.getPrice())
            .weight(dto.getBrgew())
            .width(dto.getWidth())
            .height(dto.getHeight())
            .depth(dto.getDepth())
            .image(dto.getSourcePicture())
            .image2(dto.getSourceCover4())
            .pagesCount(dto.getQtypg())
            .lastEditionDate(dto.getLDateD())
            .build();
    }

}
