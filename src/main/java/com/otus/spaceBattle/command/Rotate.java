package com.otus.spaceBattle.command;

import com.otus.spaceBattle.action.Rotatable;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Rotate {
    private final @NonNull Rotatable rotatable;

    public void execute() {
        rotatable.setDirection(rotatable.getDirection().next(rotatable.getAngularVelocity()));
    }
}
