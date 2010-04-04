package com.brodwall.kata.sudoku;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SudokuSolverTest {
    @Test
    public void shouldFindSolutionToFilledBoard() {
        SudokuBoard board = mock(SudokuBoard.class);
        when(board.isFilled(anyInt(), anyInt())).thenReturn(true);
        SudokuSolver solver = new SudokuSolver();
        assertThat(solver.findSolution(board)).isTrue();
    }
}
