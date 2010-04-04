package com.brodwall.kata.sudoku;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SudokuSolverTest {
    private SudokuBoard board = mock(SudokuBoard.class);
    private SudokuSolver solver = new SudokuSolver();

    @Test
    public void shouldFindSolutionToFilledBoard() {
        when(board.isFilled(anyInt(), anyInt())).thenReturn(true);
        assertThat(solver.findSolution(board)).isTrue();
    }

    @Test
    public void shouldNotFindSolutionWhenCellHasNoOptions() throws Exception {
        when(board.isFilled(anyInt(), anyInt())).thenReturn(true);
        when(board.isFilled(8, 8)).thenReturn(false);
        when(board.getSolutionsFor(8,8)).thenReturn(noOptions());
        assertThat(solver.findSolution(board)).isFalse();
    }

    private List<Integer> noOptions() {
        return new ArrayList<Integer>();
    }
}
