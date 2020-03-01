package ru.alifba.eksmo.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public abstract class Entity implements GuidIdentifiable {

    private final String guid;

}
