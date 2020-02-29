package ru.alifba.eksmo.service.parser;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.alifba.eksmo.model.Catalog;
import ru.alifba.eksmo.model.Category;
import ru.alifba.eksmo.model.Config;
import ru.alifba.eksmo.model.Product;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CatalogParser {

    private final ProductParser productParser;
    private final CategoryParser categoryParser;

    public Catalog parse(Config config) {
        Map<String, Product> products = productParser.parse(config);
        Map<String, Category> categories = categoryParser.parse(config);
        fillCategoriesWithProducts(categories, products);
        return new Catalog(categories, products);
    }

    private void fillCategoriesWithProducts(Map<String, Category> categoryMap, Map<String, Product> productMap) {
        categoryMap.forEach((guid, category) -> {
            List<Product> categoryProducts = productMap.values().stream()
                .filter(product -> product.getCategoryGuid().equals(guid))
                .collect(Collectors.toList());
            category.setProducts(categoryProducts);
        });
    }

}
