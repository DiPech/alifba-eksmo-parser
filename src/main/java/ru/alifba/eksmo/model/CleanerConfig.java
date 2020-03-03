package ru.alifba.eksmo.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Getter
@RequiredArgsConstructor
public class CleanerConfig {

    private final Set<String> publishersWhitelist;
    private final Integer minPublishYear;

}
