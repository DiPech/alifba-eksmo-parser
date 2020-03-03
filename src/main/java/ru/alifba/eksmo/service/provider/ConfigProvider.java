package ru.alifba.eksmo.service.provider;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.alifba.eksmo.model.Config;

import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class ConfigProvider {

    public static final String STEP_DOWNLOAD = "download";
    public static final String STEP_STATISTICS = "statistics";
    public static final String STEP_CONVERT = "convert";

    private final InputProvider inputProvider;

    public Config provide() {
        String inputDirName = inputProvider.getString("input-dir", "alifba-input");
        String temporaryDirName = inputProvider.getString("temporary-dir", "alifba-temporary");
        String outputDirName = inputProvider.getString("output-dir", "alifba-output");
        String downloadMethod = null;
        Integer itemsPerXml = null;
        if (inputProvider.getString("step").equals(STEP_DOWNLOAD)) {
            downloadMethod = inputProvider.getString("method");
            itemsPerXml = inputProvider.getInteger("count");
        }
        Path homeDir = Paths.get(System.getProperty("user.home"));
        Path desktopDir = homeDir.resolve("Desktop");
        Path inputDir = desktopDir.resolve(inputDirName);
        Path temporaryDir = desktopDir.resolve(temporaryDirName);
        Path outputDir = desktopDir.resolve(outputDirName);
        return new Config(inputDir, temporaryDir, outputDir, downloadMethod, itemsPerXml);
    }

}
