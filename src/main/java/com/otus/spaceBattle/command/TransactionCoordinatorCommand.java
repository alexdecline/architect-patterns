package com.otus.spaceBattle.command;

import lombok.RequiredArgsConstructor;

import java.util.Queue;

@RequiredArgsConstructor
public class TransactionCoordinatorCommand implements Command {
    private final Queue<TransactionParticipant> queue;

    @Override
    public void execute() {

    }
}
