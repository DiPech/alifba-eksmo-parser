package ru.alifba.eksmo.service.provider;

import ru.alifba.eksmo.model.Catalog;
import ru.alifba.eksmo.model.Config;

public interface CatalogProvider {

    Catalog provide(Config config);

}
