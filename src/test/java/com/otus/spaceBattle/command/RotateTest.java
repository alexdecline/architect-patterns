package com.otus.spaceBattle.command;

import com.otus.spaceBattle.action.Rotatable;
import com.otus.spaceBattle.dto.Direction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RotateTest {
    @Mock
    private Rotatable rotatable;
    @Mock
    private Direction direction;

    @InjectMocks
    private Rotate rotate;

    @BeforeEach
    void beforeEach() {
        when(rotatable.getDirection()).thenReturn(direction);
    }

    @Test
    @DisplayName("Npe check test")
    void test1() {
        assertThrows(NullPointerException.class, () -> new Rotate(null));
    }

    @Test
    @DisplayName("Execute check")
    void test2() {
        rotate.execute();
        verify(rotatable.getDirection(), times(1));
    }
}