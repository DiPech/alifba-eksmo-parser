package ru.alifba.eksmo.service.parser;

import org.springframework.stereotype.Service;
import ru.alifba.eksmo.model.*;

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
        fillEntitiesWithOneToOne(products, Product::getCategoryGuid, Product::setCategory, categories, true);
        fillEntitiesWithManyToOne(products, Product::getPublisherGuid, publishers, Publisher::setProducts);
        fillEntitiesWithOneToOne(products, Product::getPublisherGuid, Product::setPublisher, publishers, false);
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

    /**
     * ManyToOne - Many instances of the current Entity refer to One instance of the referred Entity.
     */
    private <C, R> void fillEntitiesWithManyToOne(
        Map<String, C> currentEntities,
        Function<C, String> currentEntitiesGetter,
        Map<String, R> referredEntities,
        BiConsumer<R, List<C>> referredEntitiesSetter) {
        referredEntities.forEach((guid, referredEntity) -> {
            List<C> currentEntitiesList = currentEntities.values().stream()
                .filter(product -> currentEntitiesGetter.apply(product).equals(guid))
                .collect(Collectors.toList());
            referredEntitiesSetter.accept(referredEntity, currentEntitiesList);
        });
    }

    /**
     * OneToOne - One instance of the current Entity refers to One instance of the referred Entity.
     */
    private <C extends Entity, R> void fillEntitiesWithOneToOne(
        Map<String, R> referredEntities,
        Function<R, String> referredEntitiesGetter,
        BiConsumer<R, C> referredEntitiesSetter,
        Map<String, C> currentEntities,
        boolean isStrongRelation) {
        referredEntities.values().forEach(referredEntity -> {
            String referredEntityGuid = referredEntitiesGetter.apply(referredEntity);
            Optional<C> currentEntityOptional = currentEntities.values().stream()
                .filter(category -> category.getGuid().equals(referredEntityGuid))
                .findFirst();
            if (!currentEntityOptional.isPresent() && isStrongRelation) {
                throw new RuntimeException("Entity with GUID [" + referredEntityGuid + "] not found");
            }
            currentEntityOptional.ifPresent(currentEntity ->
                referredEntitiesSetter.accept(referredEntity, currentEntity));
        });
    }

}
