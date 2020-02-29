package ru.alifba.eksmo.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.nio.file.Path;

@Getter
@RequiredArgsConstructor
public class Config {

    private final Path inputDir;
    private final Path outputDir;
    private final Integer itemsPerXml;
    private final Boolean isNeedToClean;

    public Path getSegmentXmlsPath() {
        return inputDir.resolve("segment");
    }

    public Path getNicheXmlsPath() {
        return inputDir.resolve("niche");
    }

    public Path getSubjectXmlsPath() {
        return inputDir.resolve("subject");
    }

    public Path getProductXmlsPath() {
        return inputDir.resolve("product");
    }

}
