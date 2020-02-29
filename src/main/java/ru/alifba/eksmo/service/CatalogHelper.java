package ru.alifba.eksmo.service;

import org.springframework.stereotype.Service;
import ru.alifba.eksmo.model.Catalog;
import ru.alifba.eksmo.model.Category;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CatalogHelper {

    public List<Category> getRootCategories(Catalog catalog) {
        return catalog.getCategories().values().stream()
            .filter(category -> category.getParent() == null)
            .collect(Collectors.toList());
    }

}
