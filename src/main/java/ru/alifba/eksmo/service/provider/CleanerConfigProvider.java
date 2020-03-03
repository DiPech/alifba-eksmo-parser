package ru.alifba.eksmo.service.provider;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.alifba.eksmo.model.CleanerConfig;
import ru.alifba.eksmo.util.CollectionUtils;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class CleanerConfigProvider {

    public CleanerConfig provide() {
        return new CleanerConfig(getPublishersWhitelist(), 2017);
    }

    private Set<String> getPublishersWhitelist() {
        return CollectionUtils.setOf(
            // АСТ
            "f89a7636-1c58-11e2-818f-5ef3fc5021a7",
            // Дрофа
            "f5f63826-525f-11e2-9cd3-5ef3fc5021fb",
            // Эксмо
            "2a734e3d-a107-11de-8bc8-00265518a912",
            // Вентана граф
            "6ff492dc-c92b-11e3-a44f-5ef3fc5021a7"
        );
    }

}
