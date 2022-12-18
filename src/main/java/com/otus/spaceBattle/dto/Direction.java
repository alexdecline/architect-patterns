package com.otus.spaceBattle.dto;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode
@RequiredArgsConstructor
public class Direction {
    public static final int MAX_VALUE_ROTATE_STEP = 36;

    private final int direction;

    public Direction next(int angularVelocity) {
        return new Direction((this.direction + angularVelocity) % MAX_VALUE_ROTATE_STEP);
    }
}
