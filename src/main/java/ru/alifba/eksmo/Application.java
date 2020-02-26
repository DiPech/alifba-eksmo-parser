package ru.alifba.eksmo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import ru.alifba.eksmo.model.Config;
import ru.alifba.eksmo.service.Step;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import static java.lang.System.exit;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
public class Application implements CommandLineRunner {

    private final Environment env;

    @Qualifier("download")
    private final Step downloadStep;

    @Qualifier("statistics")
    private final Step statisticsStep;

    @Qualifier("convert")
    private final Step convertStep;

    public static void main(String[] args) {
        try {
            SpringApplication app = new SpringApplication(Application.class);
            app.setBannerMode(Banner.Mode.OFF);
            app.run(args);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            exit(1);
        }
        exit(0);
    }

    @Override
    public void run(String... args) {
        String step = env.getProperty("step");
        Objects.requireNonNull(step, "step cannot be null");
        String ppxStr = env.getProperty("count");
        Integer productsPerXml = ppxStr != null ? Integer.parseInt(ppxStr) : null;
        Path projectDir = Paths.get(System.getProperty("user.dir"));
        Path inputDir = projectDir.resolve("input");
        Path outputDir = projectDir.resolve("output");
        Config config = new Config(inputDir, outputDir, productsPerXml);
        switch (step) {
            case "download":
                log.info("Step: Download files");
                Objects.requireNonNull(productsPerXml, "productsPerXml cannot be null");
                downloadStep.execute(config);
                break;
            case "statistics":
                log.info("Step: Calc statistics");
                statisticsStep.execute(config);
                break;
            case "convert":
                log.info("Step: Convert many XMLs to one YML");
                convertStep.execute(config);
                break;
        }
    }

}
