package com.otus.spaceBattle.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Queue;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CoordinatorCommandTest {
    @Mock
    private Queue<TransactionParticipant> queue;
    private TransactionCoordinatorCommand command;

    private TransactionParticipant participantMove;
    private TransactionParticipant participantBernFuel;

    @BeforeEach
    void beforeEach() {
        participantMove = Mockito.mock(TransactionParticipant.class);
        participantBernFuel = Mockito.mock(TransactionParticipant.class);
        command = new TransactionCoordinatorCommand(queue);

        when(queue.poll()).thenReturn(participantMove)
                .thenReturn(participantBernFuel);
    }

    @Test
    void execute_successfulExecute() {
        when(participantMove.isReady()).thenReturn(true);
        when(participantBernFuel.isReady()).thenReturn(true);

        command.execute();

        verify(participantMove).execute();
        verify(participantBernFuel).execute();
    }

    @Test
    void execute_notReadyExecute() {
        when(participantMove.isReady()).thenReturn(true);
        when(participantBernFuel.isReady()).thenReturn(false);

        command.execute();

        verify(participantMove, never()).execute();
        verify(participantBernFuel, never()).execute();
    }

    @Test
    void execute_rollBackExecute() {
        when(participantMove.isReady()).thenReturn(true);
        when(participantBernFuel.isReady()).thenReturn(true);
        doThrow(new Exception()).when(participantMove).execute();

        command.execute();

        verify(participantMove).rollback();
        verify(participantBernFuel).rollback();
    }
}