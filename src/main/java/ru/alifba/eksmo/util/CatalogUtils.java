package ru.alifba.eksmo.util;

import lombok.experimental.UtilityClass;
import ru.alifba.eksmo.model.Catalog;
import ru.alifba.eksmo.model.Category;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class CatalogUtils {

    public static List<Category> getRootCategories(Catalog catalog) {
        return catalog.getCategories().values().stream()
            .filter(category -> category.getParent() == null)
            .collect(Collectors.toList());
    }

}
