package ru.alifba.eksmo.util;

import lombok.experimental.UtilityClass;
import org.springframework.util.FileSystemUtils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Consumer;

import static ru.alifba.eksmo.util.ThrowingRunnable.throwingRunnable;

@UtilityClass
public class FileUtils {

    public static void cleanDir(Path dir) {
        throwingRunnable(() -> {
            FileSystemUtils.deleteRecursively(dir);
            Files.createDirectories(dir);
        });
    }

    public static void foreachFile(Path dir, Consumer<Path> consumer) {
        throwingRunnable(() -> Files.list(dir).forEach(consumer));
    }

    public static void writeToFile(Path path, String content) {
        throwingRunnable(() -> Files.write(path, content.getBytes()));
    }

    public static void ensureDirExists(Path dir) {
        throwingRunnable(() -> Files.createDirectories(dir));
    }
}
