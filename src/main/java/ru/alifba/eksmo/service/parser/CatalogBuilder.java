package ru.alifba.eksmo.service.parser;

import org.springframework.stereotype.Service;
import ru.alifba.eksmo.model.Catalog;
import ru.alifba.eksmo.model.Category;
import ru.alifba.eksmo.model.Product;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static ru.alifba.eksmo.util.CollectionUtils.buildParentChildrenRelations;

@Service
public class CatalogBuilder {

    public Catalog build(Map<String, Category> categories, Map<String, Product> products) {
        fillParentCategories(categories);
        fillChildrenCategories(categories);
        fillCategoriesWithProducts(categories, products);
        return new Catalog(categories, products);
    }

    private void fillParentCategories(Map<String, Category> categoryMap) {
        categoryMap.forEach((guid, category) -> {
            if (categoryMap.containsKey(category.getParentGuid())) {
                category.setParent(categoryMap.get(category.getParentGuid()));
            }
        });
    }

    private void fillChildrenCategories(Map<String, Category> categoryMap) {
        Map<String, List<Category>> parentChildren = buildParentChildrenRelations(categoryMap);
        categoryMap.forEach((guid, category) -> category.setChildren(parentChildren.get(guid)));
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
