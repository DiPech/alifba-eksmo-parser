package ru.alifba.eksmo.service.step;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.alifba.eksmo.model.Catalog;
import ru.alifba.eksmo.model.Category;
import ru.alifba.eksmo.model.Config;
import ru.alifba.eksmo.service.CatalogHelper;
import ru.alifba.eksmo.service.parser.CatalogParser;

import java.util.List;

@Service
@Qualifier("statistics")
@RequiredArgsConstructor
public class StatisticsStep implements Step {

    private final CatalogParser catalogParser;
    private final CatalogHelper catalogHelper;

    public void execute(Config config) {
        Catalog catalog = catalogParser.parse(config);
        List<Category> rootCategories = catalogHelper.getRootCategories(catalog);
        System.out.println("Root categories: " + rootCategories.size());
    }

}
