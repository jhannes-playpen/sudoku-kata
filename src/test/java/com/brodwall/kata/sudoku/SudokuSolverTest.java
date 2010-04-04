package com.brodwall.kata.sudoku;

import org.fest.assertions.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

public class SudokuSolverTest {

    @Test
    public void shouldFindSolutionToSolvedBoard() {
        SudokuSolver solver = new SudokuSolver();
        SudokuBoard board = Mockito.mock(SudokuBoard.class);

        Assertions.assertThat(solver.findSolution()).isTrue();

    }

}
