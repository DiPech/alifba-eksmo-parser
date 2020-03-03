package ru.alifba.eksmo.step;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.alifba.eksmo.model.Config;
import ru.alifba.eksmo.service.ApiService;
import ru.alifba.eksmo.util.FileUtils;

@Slf4j
@Service
@Qualifier("download")
@RequiredArgsConstructor
public class DownloadStep implements Step {

    private final ApiService apiService;

    public void execute(Config config) {
        FileUtils.cleanDir(config.getInputDir());
        log.info("Downloading segments");
        apiService.download(config, config.getSegmentXmlsPath(), "sgmnt_full");
        log.info("Downloading niches");
        apiService.download(config, config.getNicheXmlsPath(), "niche_full");
        log.info("Downloading subjects");
        apiService.download(config, config.getSubjectXmlsPath(), "sbjct_full");
        log.info("Downloading publishers");
        apiService.download(config, config.getPublisherXmlsPath(), "publi_full");
        log.info("Downloading products");
        apiService.download(config, config.getProductXmlsPath(), config.getDownloadMethod());
    }

}
