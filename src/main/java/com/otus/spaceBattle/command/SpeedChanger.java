package com.otus.spaceBattle.command;

import com.otus.spaceBattle.action.SpeedChangeable;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SpeedChanger {

    private final SpeedChangeable speedChangeable;

    public void execute() {
        int speed = speedChangeable.getSpeed();
        int speedChange = speedChangeable.getSpeedChange();

        speedChangeable.setSpeed(speed + speedChange);
    }

}