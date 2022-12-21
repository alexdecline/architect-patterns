package com.otus.spaceBattle.command;

import com.otus.spaceBattle.action.SpeedChangeable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.otus.spaceBattle.TestData.*;

@ExtendWith(MockitoExtension.class)
public class ChangeSpeedTest {

    @Mock
    private SpeedChangeable speedChangeable;

    private SpeedChanger speedChanger;

    @BeforeEach
    public void setup() {
        speedChanger = new SpeedChanger(speedChangeable);
    }

    @Test
    void changeSpeed() {
        Mockito.when(speedChangeable.getSpeed()).thenReturn(STARTED_SPEED);
        Mockito.when(speedChangeable.getSpeedChange()).thenReturn(CHANGED_SPEED);

        speedChanger.execute();

        Mockito.verify(speedChangeable).getSpeed();
        Mockito.verify(speedChangeable).getSpeedChange();

        Mockito.verify(speedChangeable).setSpeed(Mockito.eq(FINAL_SPEED));
    }

}