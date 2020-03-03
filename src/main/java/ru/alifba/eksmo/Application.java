package ru.alifba.eksmo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.alifba.eksmo.model.Config;
import ru.alifba.eksmo.service.provider.ConfigProvider;
import ru.alifba.eksmo.service.provider.InputProvider;
import ru.alifba.eksmo.step.Step;

import static java.lang.System.exit;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
public class Application implements CommandLineRunner {

    private final InputProvider inputProvider;
    private final ConfigProvider configProvider;

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
        Config config = configProvider.provide();
        switch (inputProvider.getString("step")) {
            case ConfigProvider.STEP_DOWNLOAD:
                log.info("Step: Download files");
                downloadStep.execute(config);
                break;
            case ConfigProvider.STEP_STATISTICS:
                log.info("Step: Calc statistics");
                statisticsStep.execute(config);
                break;
            case ConfigProvider.STEP_CONVERT:
                log.info("Step: Convert many XMLs to one YML");
                convertStep.execute(config);
                break;
        }
    }

}
