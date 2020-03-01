package ru.alifba.eksmo.service.parser;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.alifba.eksmo.model.*;
import ru.alifba.eksmo.service.parser.xml.CategoryXmlParser;
import ru.alifba.eksmo.service.parser.xml.ProductXmlParser;
import ru.alifba.eksmo.service.parser.xml.PublisherXmlParser;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class CatalogParser {

    private final ProductXmlParser productXmlParser;
    private final CategoryXmlParser categoryXmlParser;
    private final PublisherXmlParser publisherXmlParser;
    private final CatalogComposer catalogComposer;

    public Catalog parse(Config config) {
        Map<String, Category> categories = categoryXmlParser.parse(config);
        Map<String, Product> products = productXmlParser.parse(config);
        Map<String, Publisher> publishers = publisherXmlParser.parse(config);
        return catalogComposer.compose(categories, products, publishers);
    }

}
