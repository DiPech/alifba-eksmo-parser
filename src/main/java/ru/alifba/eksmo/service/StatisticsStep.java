package ru.alifba.eksmo.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.alifba.eksmo.model.Config;

@Service
@Qualifier("statistics")
public class StatisticsStep implements Step {

    public void execute(Config config) {

    }

}
