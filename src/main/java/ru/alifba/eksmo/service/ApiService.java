package ru.alifba.eksmo.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
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
public class ApiService {

    private static final String API_KEY = "2f3968e97de861abdb3ca5ba048e6c43";
    private static final String API_URL = "https://api.eksmo.ru/v2";
    private static final String COMMON_URL = API_URL + "?key=" + API_KEY;
    private static final CloseableHttpClient HTTP_CLIENT = HttpClients.createDefault();

    private Config config;

    public void download(Config config, Path xmlPath, String action) {
        this.config = config;
        log.info("Detect how much XMLs to download");
        int itemsCount = getItemsCount(action);
        log.info("Items count: " + itemsCount);
        int pagesCount = getPagesCount(itemsCount);
        log.info("Pages count: " + pagesCount);
        log.info("Start downloading XMLs");
        FileUtils.ensureDirExists(xmlPath);
        for (int page = 1; page <= pagesCount; page++) {
            log.info("Processing page [ " + page + " / " + pagesCount + " ]");
            Path xmlFilePath = xmlPath.resolve(page + ".xml");
            writeToFile(xmlFilePath, getContent(action, page, config.getItemsPerXml()));
        }
        log.info("Finish downloading XMLs");
    }

    private int getPagesCount(int itemsCount) {
        return (int) Math.ceil((itemsCount * 1.0) / config.getItemsPerXml());
    }

    private int getItemsCount(String action) {
        Pattern pattern = Pattern.compile("<items>(\\d+)</items>", Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(getContent(action, 1, 1));
        if (!matcher.find()) {
            throw new RuntimeException("Can't find items count");
        }
        return Integer.parseInt(matcher.group(1));
    }

    private String getContent(String action, int page, int itemsPerPage) {
        HttpGet request = new HttpGet(getUrl(action, page, itemsPerPage));
        request.setHeader("Content-Type", "text/html; charset=UTF-8");
        request.setHeader("Accept-Language", "en-US,en;q=0.9,ru-RU;q=0.8,ru;q=0.7");
        return ThrowingSupplier.throwingSupplier(() -> {
            try (CloseableHttpResponse response = HTTP_CLIENT.execute(request)) {
                HttpEntity entity = response.getEntity();
                return EntityUtils.toString(entity, StandardCharsets.UTF_8);
            }
        });
    }

    private String getUrl(String action, int page, int itemsPerPage) {
        return COMMON_URL + "&page=" + page + "&ItemsPerPage=" + itemsPerPage + "&action=" + action;
    }

}
