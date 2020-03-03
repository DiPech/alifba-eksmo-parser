package ru.alifba.eksmo.step;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.alifba.eksmo.model.Config;
import ru.alifba.eksmo.service.provider.XmlProvider;
import ru.alifba.eksmo.util.FileUtils;

@Slf4j
@Service
@Qualifier("download")
@RequiredArgsConstructor
public class DownloadStep implements Step {

    private final XmlProvider xmlProvider;

    public void execute(Config config) {
        FileUtils.cleanDir(config.getInputDir());
        log.info("Downloading segments");
        xmlProvider.provide(config, config.getSegmentXmlsPath(), "sgmnt_full");
        log.info("Downloading niches");
        xmlProvider.provide(config, config.getNicheXmlsPath(), "niche_full");
        log.info("Downloading subjects");
        xmlProvider.provide(config, config.getSubjectXmlsPath(), "sbjct_full");
        log.info("Downloading publishers");
        xmlProvider.provide(config, config.getPublisherXmlsPath(), "publi_full");
        log.info("Downloading products");
        xmlProvider.provide(config, config.getProductXmlsPath(), config.getDownloadMethod());
    }

}
