package ru.alifba.eksmo.util;

import java.util.function.Consumer;

@FunctionalInterface
public interface ThrowingConsumer<T, E extends Exception> extends Consumer<T> {

    @Override
    default void accept(T t) {
        try {
            acceptThrows(t);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    void acceptThrows(T t) throws E;

    static <T> Consumer<T> throwingConsumer(ThrowingConsumer<T, Exception> consumer) {
        return consumer;
    }
}
