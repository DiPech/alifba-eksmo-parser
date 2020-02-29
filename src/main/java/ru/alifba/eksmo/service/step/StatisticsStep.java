package ru.alifba.eksmo.service.step;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.alifba.eksmo.model.Catalog;
import ru.alifba.eksmo.model.Category;
import ru.alifba.eksmo.model.Config;
import ru.alifba.eksmo.service.CatalogService;

import java.util.List;

import static java.lang.System.out;

@Service
@Qualifier("statistics")
@RequiredArgsConstructor
public class StatisticsStep implements Step {

    private final CatalogService catalogService;

    public void execute(Config config) {
        Catalog catalog = catalogService.parse(config);
        List<Category> rootCategories = catalogService.getRootCategories(catalog);
        out.println("--- [ CATEGORIES INFO ] ------------------------------");
        out.println();
        printData(rootCategories, 0);
        out.println();
        out.println("--- [ COMMON INFO ] ------------------------------");
        out.println();
        out.println("Categories count: " + catalog.getCategories().size());
        out.println("Root categories count: " + rootCategories.size());
        out.println("Products count: " + catalog.getProducts().size());
        out.println();
    }

    private void printData(List<Category> categories, int level) {
        categories.forEach(category -> {
            int childrenCount = category.getChildren().size();
            int productsCount = category.getProducts().size();
            out.print("[ " + category.getGuid() + " ] ");
            out.print(getPrefix(level));
            out.print(category.getName());
            out.print(" ( " + productsCount + " )");
            out.println();
            if (childrenCount > 0) {
                printData(category.getChildren(), level + 1);
            }
        });
    }

    private String getPrefix(int level) {
        return StringUtils.repeat("|-- ", level);
    }

}
