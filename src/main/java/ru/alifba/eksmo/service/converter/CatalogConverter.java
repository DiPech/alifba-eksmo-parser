package ru.alifba.eksmo.service.converter;

import org.springframework.stereotype.Service;
import ru.alifba.eksmo.model.Catalog;
import ru.alifba.eksmo.model.Category;
import ru.alifba.eksmo.model.dto.yml.*;
import ru.alifba.eksmo.util.CatalogUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CatalogConverter {

    private Catalog catalog;

    public CatalogYml convert(Catalog catalog) {
        this.catalog = catalog;
        return new CatalogYml(buildShopYml(), LocalDateTime.now());
    }

    private ShopYml buildShopYml() {
        return new ShopYml("Eksmo", "OOO Eksmo", "https://eksmo.ru",
            buildCurrenciesYml(), new CategoriesYml(buildCategoryYmlList()));
    }

    private CurrenciesYml buildCurrenciesYml() {
        return new CurrenciesYml(Collections.singletonList(new CurrencyYml("RUR", "1")));
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

}
