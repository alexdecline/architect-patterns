package com.otus.spaceBattle.exceptionHandler;

import com.otus.spaceBattle.command.Command;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class MainExceptionHandlerTest {

  @Test
  void handle_shouldCall3TypesOfHandlers() {
    //given
    NullPointerException exception = Mockito.spy(NullPointerException.class);
    Command executedCommand = Mockito.mock(Command.class);
    LinkedList<Command> queue = new LinkedList<>();

    Map<AbstractMap.SimpleEntry<Class<? extends Exception>, Class<? extends Command>>, CommandExceptionHandler> map =
            new HashMap<>();
    CommandExceptionHandler h1 = Mockito.mock(CommandExceptionHandler.class);
    map.put(new AbstractMap.SimpleEntry<>(exception.getClass(), executedCommand.getClass()), h1);
    CommandExceptionHandler h2 = Mockito.mock(CommandExceptionHandler.class);
    map.put(new AbstractMap.SimpleEntry<>(null, executedCommand.getClass()), h2);
    CommandExceptionHandler h3 = Mockito.mock(CommandExceptionHandler.class);
    map.put(new AbstractMap.SimpleEntry<>(exception.getClass(), null), h3);
    CommandExceptionHandler extraHandler = Mockito.mock(CommandExceptionHandler.class);
    map.put(new AbstractMap.SimpleEntry<>(IndexOutOfBoundsException.class, executedCommand.getClass()), extraHandler);

    MainExceptionHandler mainHandler = new MainExceptionHandler(map);

    //when
    mainHandler.handle(exception, executedCommand, queue);

    //then
    Mockito.verify(h1).handle(exception, executedCommand, queue);
    Mockito.verify(h2).handle(exception, executedCommand, queue);
    Mockito.verify(h3).handle(exception, executedCommand, queue);
    Mockito.verify(extraHandler, Mockito.never()).handle(any(), any(), any());
    Mockito.verify(exception, Mockito.never()).printStackTrace();
  }

  @Test
  void handle_ifNoHandlers_shouldPrintStackTraceInConsole() {
    //given
    NullPointerException exception = Mockito.spy(NullPointerException.class);
    Command executedCommand = Mockito.mock(Command.class);
    LinkedList<Command> queue = new LinkedList<>();

    Map<AbstractMap.SimpleEntry<Class<? extends Exception>, Class<? extends Command>>, CommandExceptionHandler> map =
            new HashMap<>();
    CommandExceptionHandler h1 = Mockito.mock(CommandExceptionHandler.class);
    map.put(new AbstractMap.SimpleEntry<>(ClassCastException.class, executedCommand.getClass()), h1);
    CommandExceptionHandler h2 = Mockito.mock(CommandExceptionHandler.class);
    map.put(new AbstractMap.SimpleEntry<>(null, Command.class), h2);
    CommandExceptionHandler h3 = Mockito.mock(CommandExceptionHandler.class);
    map.put(new AbstractMap.SimpleEntry<>(ClassCastException.class, null), h3);

    MainExceptionHandler mainHandler = new MainExceptionHandler(map);

    //when
    mainHandler.handle(exception, executedCommand, queue);

    //then
    Mockito.verify(h1, Mockito.never()).handle(any(), any(), any());
    Mockito.verify(h2, Mockito.never()).handle(any(), any(), any());
    Mockito.verify(h3, Mockito.never()).handle(any(), any(), any());
    Mockito.verify(exception).printStackTrace();
  }
}