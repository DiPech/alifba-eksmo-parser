package ru.alifba.eksmo.service.parser;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.alifba.eksmo.model.*;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class CatalogParser {

    private final ProductParser productParser;
    private final CategoryParser categoryParser;
    private final PublisherParser publisherParser;
    private final CatalogBuilder catalogBuilder;

    public Catalog parse(Config config) {
        Map<String, Category> categories = categoryParser.parse(config);
        Map<String, Product> products = productParser.parse(config);
        Map<String, Publisher> publishers = publisherParser.parse(config);
        return catalogBuilder.build(categories, products, publishers);
    }

}
