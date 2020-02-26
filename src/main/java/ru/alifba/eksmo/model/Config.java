package ru.alifba.eksmo.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.nio.file.Path;

@Getter
@RequiredArgsConstructor
public class Config {

    private final Path inputDir;
    private final Path outputDir;
    private final Integer productsPerXml;

}
