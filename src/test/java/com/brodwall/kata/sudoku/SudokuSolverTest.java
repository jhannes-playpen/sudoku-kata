package com.brodwall.kata.sudoku;

import org.junit.Test;
import org.mockito.Mockito;

public class SudokuSolverTest {

    @Test
    public void shouldFindSolutionToSolvedBoard() {
        SudokuSolver solver = new SudokuSolver();
        SudokuBoard board = Mockito.mock(SudokuBoard.class);

        assertThat(solver.findSolution()).isTrue();

    }

}
