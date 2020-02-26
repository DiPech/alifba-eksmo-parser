package ru.alifba.eksmo.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.alifba.eksmo.model.Config;
import ru.alifba.eksmo.util.FileUtils;
import ru.alifba.eksmo.util.ThrowingSupplier;

import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ru.alifba.eksmo.util.FileUtils.writeToFile;

@Slf4j
@Service
@Qualifier("download")
public class DownloadStep implements Step {

    private static final String API_URL = "https://api.eksmo.ru/v2";
    private static final String COMMON_URL = API_URL + "?action=products&key=2f3968e97de861abdb3ca5ba048e6c43";
    private static final CloseableHttpClient HTTP_CLIENT = HttpClients.createDefault();

    private Config config;

    public void execute(Config config) {
        this.config = config;
        FileUtils.cleanDir(config.getInputDir());
        log.info("Detect how much products to download");
        int productsCount = getProductsCount();
        log.info("Products count: " + productsCount);
        int pagesCount = getPagesCount(productsCount);
        log.info("Pages count: " + pagesCount);
        log.info("Start downloading XMLs");
        for (int page = 1; page <= pagesCount; page++) {
            log.info("Processing page [ " + page + " / " + pagesCount + " ]");
            Path xmlPath = config.getInputDir().resolve(page + ".xml");
            writeToFile(xmlPath, getContent(page, config.getProductsPerXml()));
        }
        log.info("Finish downloading XMLs");
    }

    private int getPagesCount(int productsCount) {
        return (int) Math.ceil((productsCount * 1.0) / config.getProductsPerXml());
    }

    private int getProductsCount() {
        Pattern pattern = Pattern.compile("<items>(\\d+)</items>", Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(getContent(1, 1));
        if (!matcher.find()) {
            throw new RuntimeException("Can't find items count");
        }
        return Integer.parseInt(matcher.group(1));
    }

    private String getContent(int page, int itemsPerPage) {
        HttpGet request = new HttpGet(getUrl(page, itemsPerPage));
        request.setHeader("Content-Type", "text/html; charset=UTF-8");
        request.setHeader("Accept-Language", "en-US,en;q=0.9,ru-RU;q=0.8,ru;q=0.7");
        return ThrowingSupplier.throwingSupplier(() -> {
            try (CloseableHttpResponse response = HTTP_CLIENT.execute(request)) {
                HttpEntity entity = response.getEntity();
                return EntityUtils.toString(entity, StandardCharsets.UTF_8);
            }
        });
    }

    private String getUrl(int page, int itemsPerPage) {
        return COMMON_URL + "&page=" + page + "&ItemsPerPage=" + itemsPerPage;
    }

}
