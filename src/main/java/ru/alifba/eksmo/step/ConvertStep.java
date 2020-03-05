package ru.alifba.eksmo.step;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.alifba.eksmo.model.Catalog;
import ru.alifba.eksmo.model.Config;
import ru.alifba.eksmo.model.dto.yml.*;
import ru.alifba.eksmo.service.converter.CatalogConverter;
import ru.alifba.eksmo.service.provider.CatalogProvider;
import ru.alifba.eksmo.util.FileUtils;
import ru.alifba.eksmo.util.XmlUtils;

import java.nio.file.Path;

@Service
@Qualifier("convert")
@RequiredArgsConstructor
public class ConvertStep implements Step {

    @Qualifier("cached")
    private final CatalogProvider catalogProvider;

    private final CatalogConverter catalogConverter;

    public void execute(Config config) {
        FileUtils.cleanDir(config.getOutputDir());
        Catalog catalog = catalogProvider.provide(config);
        CatalogYml catalogYml = catalogConverter.convert(catalog);
        XmlUtils.write(catalogYml, getFilePath(config), getXmlClasses());
        System.out.println(catalogYml.getShop().getName());
    }

    private Path getFilePath(Config config) {
        return config.getOutputDir().resolve("eksmo-yml.xml");
    }

    private Class[] getXmlClasses() {
        return new Class[]{
            CatalogYml.class, ShopYml.class,
            CurrenciesYml.class, CurrencyYml.class,
            CategoriesYml.class, CategoryYml.class
        };
    }

}
