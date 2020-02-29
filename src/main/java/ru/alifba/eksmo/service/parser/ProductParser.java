package ru.alifba.eksmo.service.parser;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.alifba.eksmo.model.Config;
import ru.alifba.eksmo.model.Product;
import ru.alifba.eksmo.model.dto.product.ProductDto;
import ru.alifba.eksmo.model.dto.product.ProductXml;
import ru.alifba.eksmo.service.parser.xml.ProductXmlParser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductParser {

    private final ProductXmlParser productsXmlDirParser;

    public Map<String, Product> parse(Config config) {
        List<ProductXml> productXmls = productsXmlDirParser.parse(config.getProductXmlsPath());
        return getProductMap(productXmls);
    }

    private Map<String, Product> getProductMap(List<ProductXml> xmls) {
        Map<String, Product> hashMap = new HashMap<>();
        xmls.forEach(xml -> xml.getProducts().getProducts().forEach(dto -> {
            hashMap.put(dto.getGuid(), productFromDto(dto));
        }));
        return hashMap;
    }

    private Product productFromDto(ProductDto dto) {
        return Product.builder()
            .guid(dto.getGuid())
            .categoryGuid(dto.getSbjct().getGuid())
            .name(dto.getName())
            .price(dto.getPrice())
            .weight(dto.getBrgew())
            .width(dto.getWidth())
            .height(dto.getHeight())
            .depth(dto.getDepth())
            .image(dto.getSourcePicture())
            .image2(dto.getSourceCover4())
            .pagesCount(dto.getQtypg())
            .build();
    }

}
