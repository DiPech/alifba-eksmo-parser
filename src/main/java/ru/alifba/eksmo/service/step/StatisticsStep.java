package ru.alifba.eksmo.service.step;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.alifba.eksmo.model.Catalog;
import ru.alifba.eksmo.model.Category;
import ru.alifba.eksmo.model.Config;
import ru.alifba.eksmo.service.CatalogHelper;
import ru.alifba.eksmo.service.parser.CatalogParser;

import java.util.List;

import static java.lang.System.out;

@Service
@Qualifier("statistics")
@RequiredArgsConstructor
public class StatisticsStep implements Step {

    private final CatalogParser catalogParser;
    private final CatalogHelper catalogHelper;

    public void execute(Config config) {
        Catalog catalog = catalogParser.parse(config);
        List<Category> rootCategories = catalogHelper.getRootCategories(catalog);
        out.println("--- [ COMMON INFO ] ------------------------------");
        out.println();
        out.println("Categories count: " + catalog.getCategories().size());
        out.println("Root categories count: " + rootCategories.size());
        out.println("Total products count: " + catalog.getProducts().size());
        out.println("Inside categories products count: " + calculateProductsCount(catalog));
        out.println();
        out.println("--- [ CATEGORIES INFO ] ------------------------------");
        out.println();
        printData(rootCategories, 0);
    }

    private void printData(List<Category> categories, int level) {
        categories.forEach(category -> {
            int childrenCount = category.getChildren().size();
            int productsCount = category.getProducts().size();
            if (childrenCount == 0 && productsCount > 0) {
                out.print(getPrefix(level));
                out.print(category.getName());
                out.print(" [ " + productsCount + " ]");
                out.println();
            }
            if (childrenCount > 0) {
                printData(category.getChildren(), level + 1);
            }
        });
    }

    private String getPrefix(int level) {
        return StringUtils.repeat("|-- ", level);
    }

    private int calculateProductsCount(Catalog catalog) {
        return catalog.getCategories().values().stream()
            .map(category -> category.getProducts().size())
            .reduce(0, Integer::sum);
    }

}
