package com.otus.spaceBattle.command;

import com.otus.spaceBattle.action.RotationSpeedChangeable;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RotationSpeedChange {

    private final @NonNull RotationSpeedChangeable rotationSpeedChangeable;

    public void execute() {
        int angularVelocity = rotationSpeedChangeable.getAngularSpeed();
        int angularVelocityChange = rotationSpeedChangeable.getAngularSpeedChange();
        rotationSpeedChangeable.setAngularVelocity(angularVelocity + angularVelocityChange);
    }
}
