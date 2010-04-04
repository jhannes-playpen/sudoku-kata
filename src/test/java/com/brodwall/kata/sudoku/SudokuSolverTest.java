package com.brodwall.kata.sudoku;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

public class SudokuSolverTest {
    private SudokuBoard board = mock(SudokuBoard.class);
    private SudokuSolver solver = new SudokuSolver();

    @Test
    public void shouldFindSolutionToFilledBoard() {
        assertThat(solver.findSolution(board)).isTrue();
    }

    @Test
    public void shouldNotFindSolutionWhenCellHasNoOptions() throws Exception {
        when(board.isFilled(8, 8)).thenReturn(false);
        when(board.getOptionsForCell(8, 8)).thenReturn(noOptions());
        assertThat(solver.findSolution(board)).isFalse();
    }

    @Test
    public void shouldFindSolutionWhenCellHasSingleOption() throws Exception {
        when(board.isFilled(8,8)).thenReturn(false);
        when(board.getOptionsForCell(8,8)).thenReturn(singleOption(3));
        assertThat(solver.findSolution(board)).isTrue();
        verify(board).setCellValue(8,8, 3);
    }

    @Test
    public void shouldBacktrackWhenFutureCellHasNoOptions() throws Exception {
        when(board.isFilled(8,8)).thenReturn(false);
        when(board.isFilled(7,8)).thenReturn(false);
        when(board.getOptionsForCell(7,8)).thenReturn(options(1,2,3));
        when(board.getOptionsForCell(8,8))
                .thenReturn(noOptions())
                .thenReturn(singleOption(1));

        assertThat(solver.findSolution(board)).isTrue();

        verify(board).setCellValue(7,8, 2);
        verify(board, never()).setCellValue(7,8, 3);
    }

    @Test
    public void shouldClearCellWhenDeadEndReached() throws Exception {
        when(board.isFilled(8,8)).thenReturn(false);
        when(board.isFilled(7,8)).thenReturn(false);
        when(board.getOptionsForCell(7,8)).thenReturn(options(1));
        when(board.getOptionsForCell(8,8)).thenReturn(noOptions());

        assertThat(solver.findSolution(board)).isFalse();

        InOrder order = inOrder(board);
        order.verify(board).setCellValue(7,8, 1);
        order.verify(board).clearCell(7,8);
    }

    @Before
    public void allCellsAreFilled() {
        when(board.isFilled(anyInt(), anyInt())).thenReturn(true);
    }

    private List<Integer> options(Integer... options) {
        return Arrays.asList(options);
    }

    private List<Integer> singleOption(int option) {
        return Arrays.asList(option);
    }

    private List<Integer> noOptions() {
        return new ArrayList<Integer>();
    }
}
