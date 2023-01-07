package com.otus.spaceBattle.exceptionHandler;

import com.otus.spaceBattle.command.Command;

import java.util.*;
import java.util.AbstractMap.SimpleEntry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MainExceptionHandler {

    @NonNull
    private final Map<
            SimpleEntry<Class<? extends Exception>, Class<? extends Command>>,
            CommandExceptionHandler
            > exceptionAndCommand2Handler;


    public void handle(Exception exception, Command executedCommand, Queue<Command> queue) {
        Class<? extends Exception> eClass = exception.getClass();
        Class<? extends Command> cClass = executedCommand.getClass();

        SimpleEntry<Class<? extends Exception>, Class<? extends Command>> byExceptionAndCommandKey =
                new SimpleEntry<>(eClass, cClass);
        SimpleEntry<Class<? extends Exception>, Class<? extends Command>> byCommandKey =
                new SimpleEntry<>(null, cClass);
        SimpleEntry<Class<? extends Exception>, Class<? extends Command>> byExceptionKey =
                new SimpleEntry<>(eClass, null);

        List<CommandExceptionHandler> existHandlers = Stream.of(
                        exceptionAndCommand2Handler.get(byExceptionAndCommandKey),
                        exceptionAndCommand2Handler.get(byCommandKey),
                        exceptionAndCommand2Handler.get(byExceptionKey)
                )
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        if (existHandlers.isEmpty()) {
            exception.printStackTrace();
        } else {
            existHandlers.forEach(h -> h.handle(exception, executedCommand, queue));
        }
    }

}
