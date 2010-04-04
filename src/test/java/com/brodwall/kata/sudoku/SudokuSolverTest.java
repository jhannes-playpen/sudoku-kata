package com.brodwall.kata.sudoku;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SudokuSolverTest {
    private SudokuBoard board = mock(SudokuBoard.class);
    private SudokuSolver solver = new SudokuSolver();

    @Before
    public void allCellsAreFilled() {
        when(board.isFilled(anyInt(), anyInt())).thenReturn(true);
    }

    @Test
    public void shouldFindSolutionToFilledBoard() {
        assertThat(solver.findSolution(board)).isTrue();
    }

    @Test
    public void shouldNotFindSolutionWhenCellHasNoOptions() throws Exception {
        when(board.isFilled(8, 8)).thenReturn(false);
        when(board.getOptionsForCell(8,8)).thenReturn(noOptions());
        assertThat(solver.findSolution(board)).isFalse();
    }

    @Test
    public void shouldFindSolutionWhenCellHasOneOption() throws Exception {
        when(board.isFilled(8, 8)).thenReturn(false);
        when(board.getOptionsForCell(8,8)).thenReturn(oneOption(3));
        assertThat(solver.findSolution(board)).isTrue();
        
    }

    private List<Integer> oneOption(int option) {
        return Arrays.asList(option);
    }

    private List<Integer> noOptions() {
        return new ArrayList<Integer>();
    }
}
