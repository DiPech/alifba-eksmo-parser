package ru.alifba.eksmo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.alifba.eksmo.model.Catalog;
import ru.alifba.eksmo.model.Category;
import ru.alifba.eksmo.model.Product;
import ru.alifba.eksmo.service.parser.CatalogBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CatalogCleaner {

    private final CatalogBuilder catalogBuilder;

    public Catalog clean(Catalog catalog) {
        Map<String, Category> categories = new HashMap<>();
        Map<String, Product> products = new HashMap<>();
        getLeafCategoriesWithProducts(catalog).forEach(category -> {
            addCategoryWithParents(categories, category);
            category.getProducts().forEach(product -> products.putIfAbsent(product.getGuid(), product));
        });
        return catalogBuilder.build(categories, products);
    }

    private List<Category> getLeafCategoriesWithProducts(Catalog catalog) {
        return catalog.getCategories().values().stream()
            .filter(category -> category.getChildren().size() == 0)
            .filter(category -> category.getProducts().size() > 0)
            .collect(Collectors.toList());
    }

    private void addCategoryWithParents(Map<String, Category> categories, Category category) {
        categories.putIfAbsent(category.getGuid(), category);
        if (category.getParent() != null) {
            addCategoryWithParents(categories, category.getParent());
        }
    }

}
