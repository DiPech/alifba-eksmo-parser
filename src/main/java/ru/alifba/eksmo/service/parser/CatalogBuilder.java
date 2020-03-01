package ru.alifba.eksmo.service.parser;

import org.springframework.stereotype.Service;
import ru.alifba.eksmo.model.Catalog;
import ru.alifba.eksmo.model.Category;
import ru.alifba.eksmo.model.Product;
import ru.alifba.eksmo.model.Publisher;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import static ru.alifba.eksmo.util.CollectionUtils.buildParentChildrenRelations;

@Service
public class CatalogBuilder {

    public Catalog build(
        Map<String, Category> categories,
        Map<String, Product> products,
        Map<String, Publisher> publishers) {
        products = removeProductsWithBadCategories(products, categories);
        fillParentCategories(categories);
        fillChildrenCategories(categories);
        fillEntitiesWithManyToOne(products, Product::getCategoryGuid, categories, Category::setProducts);
        fillProductsCategories(products, categories);
        fillEntitiesWithManyToOne(products, Product::getPublisherGuid, publishers, Publisher::setProducts);
        fillProductsPublishers(products, publishers);
        return new Catalog(categories, products, publishers);
    }

    private Map<String, Product> removeProductsWithBadCategories(Map<String, Product> products,
                                                                 Map<String, Category> categories) {
        return products.entrySet().stream()
            .filter(entry -> categories.containsKey(entry.getValue().getCategoryGuid()))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
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

    private <M,O> void fillEntitiesWithManyToOne(
        Map<String, M> entitiesMany,
        Function<M, String> entitiesManyGetter,
        Map<String, O> entitiesOne,
        BiConsumer<O, List<M>> entitiesOneSetter) {
        entitiesOne.forEach((guid, entityOne) -> {
            List<M> entitiesManyToOne = entitiesMany.values().stream()
                .filter(product -> entitiesManyGetter.apply(product).equals(guid))
                .collect(Collectors.toList());
            entitiesOneSetter.accept(entityOne, entitiesManyToOne);
        });
    }

    private void fillProductsCategories(Map<String, Product> products, Map<String, Category> categories) {
        products.values().forEach(product -> {
            Optional<Category> categoryOptional = categories.values().stream()
                .filter(category -> category.getGuid().equals(product.getCategoryGuid()))
                .findFirst();
            if (!categoryOptional.isPresent()) {
                throw new RuntimeException("Category with GUID [" + product.getCategoryGuid() + "] not found");
            }
            product.setCategory(categoryOptional.get());
        });
    }

    private void fillProductsPublishers(Map<String, Product> products, Map<String, Publisher> publishers) {
        products.values().forEach(product -> {
            Optional<Publisher> publisherOptional = publishers.values().stream()
                .filter(publisher -> publisher.getGuid().equals(product.getPublisherGuid()))
                .findFirst();
            publisherOptional.ifPresent(product::setPublisher);
        });
    }

}
