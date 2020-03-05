package ru.alifba.eksmo.step;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.alifba.eksmo.model.Catalog;
import ru.alifba.eksmo.model.Config;
import ru.alifba.eksmo.model.dto.yml.CatalogYml;
import ru.alifba.eksmo.service.converter.CatalogConverter;
import ru.alifba.eksmo.service.provider.CatalogProvider;
import ru.alifba.eksmo.util.FileUtils;

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
    }

}
