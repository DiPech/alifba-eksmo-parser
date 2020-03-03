package ru.alifba.eksmo.step;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.alifba.eksmo.model.Config;
import ru.alifba.eksmo.util.FileUtils;

@Service
@Qualifier("convert")
public class ConvertStep implements Step {

    public void execute(Config config) {
        FileUtils.cleanDir(config.getOutputDir());
    }

}
