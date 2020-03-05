package ru.alifba.eksmo.service.provider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Timer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.alifba.eksmo.model.Catalog;
import ru.alifba.eksmo.model.Config;
import ru.alifba.eksmo.util.FileUtils;
import ru.alifba.eksmo.util.ThrowingSupplier;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static ru.alifba.eksmo.util.ThrowingRunnable.throwingRunnable;

@Slf4j
@Service
@Qualifier("cached")
@RequiredArgsConstructor
public class CachedCatalogProviderImpl implements CatalogProvider {

    private final CatalogProvider catalogProvider;
    private Config config;

    public Catalog provide(Config config) {
        this.config = config;
        Timer timer = new Timer("timer");
        if (hasCache()) {
            log.info("Loading data from cache");
            timer.start();
            Catalog catalog = getCache();
            timer.stop();
            log.info("Catalog from cache loaded with time: " + timer.getElapsedTime());
            return catalog;
        }
        log.info("Cache not found, loading from XMLs");

        log.info("Loading data from cache");
        timer.start();
        Catalog catalog = catalogProvider.provide(config);
        timer.stop();
        log.info("Catalog from XMLs loaded with time: " + timer.getElapsedTime());
        log.info("Save data to cache");
        setCache(catalog);
        return catalog;
    }

    private void setCache(Catalog catalog) {
        FileUtils.ensureDirExists(getCacheDirPath());
        throwingRunnable(() -> {
            try (
                FileOutputStream fos = new FileOutputStream(getCachePath().toFile());
                ObjectOutputStream oos = new ObjectOutputStream(fos)
            ) {
                oos.writeObject(catalog);
            }
        });
    }

    private Catalog getCache() {
        return ThrowingSupplier.throwingSupplier(() -> {
            try (
                FileInputStream fis = new FileInputStream(getCachePath().toFile());
                ObjectInputStream ois = new ObjectInputStream(fis)
            ) {
                return (Catalog) ois.readObject();
            }
        });
    }

    private boolean hasCache() {
        return Files.exists(getCachePath());
    }

    private Path getCacheDirPath() {
        return config.getTemporaryDir();
    }

    private Path getCachePath() {
        return getCacheDirPath().resolve("catalog.dat");
    }

}
