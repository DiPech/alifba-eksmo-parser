package ru.alifba.eksmo.service.converter;

import org.springframework.stereotype.Service;
import ru.alifba.eksmo.model.Catalog;
import ru.alifba.eksmo.model.Category;
import ru.alifba.eksmo.model.Product;
import ru.alifba.eksmo.model.dto.yml.*;
import ru.alifba.eksmo.util.CatalogUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CatalogConverter {

    private static final String CURRENCY = "RUR";

    private Catalog catalog;

    public CatalogYml convert(Catalog catalog) {
        this.catalog = catalog;
        return new CatalogYml(buildShopYml(), LocalDateTime.now());
    }

    private ShopYml buildShopYml() {
        return new ShopYml("Eksmo", "OOO Eksmo", "https://eksmo.ru",
            buildCurrenciesYml(), new CategoriesYml(buildCategoryYmlList()), new OffersYml(buildOfferYmlList()));
    }

    private CurrenciesYml buildCurrenciesYml() {
        return new CurrenciesYml(Collections.singletonList(new CurrencyYml(CURRENCY, "1")));
    }

    private List<CategoryYml> buildCategoryYmlList() {
        List<CategoryYml> categoriesYml = new ArrayList<>();
        fillCategoryYmlList(categoriesYml, CatalogUtils.getRootCategories(catalog));
        return categoriesYml;
    }

    private void fillCategoryYmlList(List<CategoryYml> categoriesYml, List<Category> categories) {
        categories.forEach(category -> {
            int childrenCount = category.getChildren().size();
            categoriesYml.add(new CategoryYml(category.getGuid(), category.getParentGuid(), category.getName()));
            if (childrenCount > 0) {
                fillCategoryYmlList(categoriesYml, category.getChildren());
            }
        });
    }

    private List<OfferYml> buildOfferYmlList() {
        List<OfferYml> offers = new ArrayList<>();
        catalog.getProducts().values().forEach(product -> offers.add(toEntityYml(product)));
        return offers;
    }

    private OfferYml toEntityYml(Product product) {
        return OfferYml.builder()
            .id(product.getGuid())
            .categoryId(product.getCategory().getGuid())
            .currencyId(CURRENCY)
            .vendor(product.getPublisher().getName())
            .name(product.getName())
            .price(getProductPrice(product))
            .weight(product.getWeight())
            .available(999999) // Hardcoded, because of no needed data in input XMLs
            .pictures(buildPicturesList(product))
            .parameters(buildParametersList(product))
            .build();
    }

    private Float getProductPrice(Product product) {
        return (float) Math.ceil(product.getPrice());
    }

    private List<String> buildPicturesList(Product product) {
        List<String> images = new ArrayList<>();
        if (product.getImage() != null && product.getImage().length() > 0) {
            images.add(product.getImage());
        }
        if (product.getImage2() != null && product.getImage2().length() > 0) {
            images.add(product.getImage2());
        }
        return images;
    }

    private List<ParameterYml> buildParametersList(Product product) {
        List<ParameterYml> parameters = new ArrayList<>();
        if (product.getWidth() != null && product.getWidth() > 0) {
            parameters.add(new ParameterYml("width", product.getWidth().toString()));
        }
        if (product.getHeight() != null && product.getHeight() > 0) {
            parameters.add(new ParameterYml("height", product.getHeight().toString()));
        }
        if (product.getDepth() != null && product.getDepth() > 0) {
            parameters.add(new ParameterYml("depth", product.getDepth().toString()));
        }
        if (product.getPagesCount() != null && product.getPagesCount() > 0) {
            parameters.add(new ParameterYml("pages_count", product.getPagesCount().toString()));
        }
        return parameters;
    }

}
