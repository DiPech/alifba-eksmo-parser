package ru.alifba.eksmo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.alifba.eksmo.model.Config;

import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class ConfigService {

    public static final String STEP_DOWNLOAD = "download";
    public static final String STEP_STATISTICS = "statistics";
    public static final String STEP_CONVERT = "convert";

    private final InputService inputService;

    public Config getConfig() {
        String inputDirName = inputService.getString("input-dir", "alifba-input");
        String temporaryDirName = inputService.getString("temporary-dir", "alifba-temporary");
        String outputDirName = inputService.getString("output-dir", "alifba-output");
        String downloadMethod = null;
        Integer itemsPerXml = null;
        if (inputService.getString("step").equals(STEP_DOWNLOAD)) {
            downloadMethod = inputService.getString("method");
            itemsPerXml = inputService.getInteger("count");
        }
        Path homeDir = Paths.get(System.getProperty("user.home"));
        Path desktopDir = homeDir.resolve("Desktop");
        Path inputDir = desktopDir.resolve(inputDirName);
        Path temporaryDir = desktopDir.resolve(temporaryDirName);
        Path outputDir = desktopDir.resolve(outputDirName);
        return new Config(inputDir, temporaryDir, outputDir, downloadMethod, itemsPerXml);
    }

}
