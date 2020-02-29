package ru.alifba.eksmo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.alifba.eksmo.model.Catalog;
import ru.alifba.eksmo.model.Category;
import ru.alifba.eksmo.model.Config;
import ru.alifba.eksmo.service.parser.CatalogParser;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CatalogService {

    private final CatalogParser catalogParser;
    private final CatalogCleaner catalogCleaner;

    public List<Category> getRootCategories(Catalog catalog) {
        return catalog.getCategories().values().stream()
            .filter(category -> category.getParent() == null)
            .collect(Collectors.toList());
    }

    public Catalog parse(Config config) {
        Catalog catalog = catalogParser.parse(config);
        if (config.getIsNeedToClean()) {
            catalog = catalogCleaner.clean(catalog);
        }
        return catalog;
    }

}
