package ru.alifba.eksmo.service.cleaner;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.alifba.eksmo.model.Catalog;
import ru.alifba.eksmo.model.Category;
import ru.alifba.eksmo.model.Product;
import ru.alifba.eksmo.model.Publisher;
import ru.alifba.eksmo.service.parser.CatalogComposer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CatalogCleaner implements Cleaner<Catalog> {

    private final CatalogComposer catalogComposer;

    public Catalog clean(Catalog catalog) {
        Map<String, Category> categories = new HashMap<>();
        Map<String, Product> products = new HashMap<>();
        Map<String, Publisher> publishers = new HashMap<>();
        getLeafCategoriesWithProducts(catalog).forEach(category -> {
            addCategoryWithParents(categories, category);
            category.getProducts().forEach(product -> products.putIfAbsent(product.getGuid(), product));
        });
        products.values().forEach(prod -> publishers.putIfAbsent(prod.getPublisher().getGuid(), prod.getPublisher()));
        return catalogComposer.compose(categories, products, publishers);
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
