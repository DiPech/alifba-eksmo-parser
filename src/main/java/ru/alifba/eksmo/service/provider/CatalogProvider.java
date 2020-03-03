package ru.alifba.eksmo.service.provider;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.alifba.eksmo.model.Catalog;
import ru.alifba.eksmo.model.Config;
import ru.alifba.eksmo.service.cleaner.CatalogCleaner;
import ru.alifba.eksmo.service.parser.CatalogParser;

@Service
@RequiredArgsConstructor
public class CatalogProvider {

    private final CatalogParser catalogParser;
    private final CatalogCleaner catalogCleaner;

    public Catalog provide(Config config) {
        Catalog catalog = catalogParser.parse(config);
        // Now we always clean the catalog
        return catalogCleaner.clean(catalog);
    }

}
