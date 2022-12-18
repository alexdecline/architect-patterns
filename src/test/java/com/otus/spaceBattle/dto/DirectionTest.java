package com.otus.spaceBattle.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DirectionTest {

    @Test
    @DisplayName("Rotation by 10 degrees from the zero position")
    void next() {
        var direction = new Direction(0);
        assertEquals(direction.next(1), new Direction(1));
    }

    @Test
    @DisplayName("Rotation by 370 degrees from the zero position")
    void next2() {
        var direction = new Direction(0);
        assertEquals(direction.next(37), new Direction(1));
    }
}