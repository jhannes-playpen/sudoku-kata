package com.brodwall.kata.sudoku;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

public class SudokuSolverTest {
    private SudokuSolver solver = new SudokuSolver();
    private SudokuBoard board = Mockito.mock(SudokuBoard.class);

    @Before
    public void allCellsAreFilled() {
        when(board.isFilled(anyInt(), anyInt())).thenReturn(true);
    }

    @Test
    public void shouldFindSolutionToSolvedBoard() {
        assertThat(solver.findSolution(board)).isTrue();
    }

    @Test
    public void shouldNotFindSolutionWhenCellHasNoOption() throws Exception {
        when(board.isFilled(8, 8)).thenReturn(false);
        when(board.getOptionsForCell(8,8)).thenReturn(noOption());
        assertThat(solver.findSolution(board)).isFalse();
    }

    @Test
    public void shouldFindSolutionToBoardWithOneOptionInOnCell() throws Exception {
        when(board.isFilled(8, 8)).thenReturn(false);
        when(board.getOptionsForCell(8,8)).thenReturn(singleOption(1));
        assertThat(solver.findSolution(board)).isTrue();
        verify(board).setCellValue(8,8, 1);
    }

    @Test
    public void shouldTryAllOptionsAndClearCellOnDeadend() throws Exception {
        when(board.isFilled(8, 8)).thenReturn(false);
        when(board.isFilled(7, 8)).thenReturn(false);
        when(board.getOptionsForCell(7,8)).thenReturn(options(1,2,3));
        when(board.getOptionsForCell(8,8)).thenReturn(noOption());

        assertThat(solver.findSolution(board)).isFalse();

        InOrder order = inOrder(board);
        order.verify(board).setCellValue(7,8, 1);
        order.verify(board).setCellValue(7,8, 2);
        order.verify(board).setCellValue(7,8, 3);
        order.verify(board).clearCellValue(7,8);
    }

    @Test
    public void shouldBacktrackWhenNoOptions() throws Exception {
        when(board.isFilled(8, 8)).thenReturn(false);
        when(board.isFilled(7, 8)).thenReturn(false);
        when(board.getOptionsForCell(7,8)).thenReturn(options(1,2,3));
        when(board.getOptionsForCell(8,8)).thenReturn(noOption()).thenReturn(singleOption(3));

        assertThat(solver.findSolution(board)).isTrue();

        InOrder order = inOrder(board);
        order.verify(board).setCellValue(7,8, 1);
        order.verify(board).setCellValue(7,8, 2);
        order.verify(board).setCellValue(8,8, 3);
        verify(board, never()).setCellValue(7,8,3);
    }

    private List<Integer> options(Integer... options) {
        return Arrays.asList(options);
    }

    private List<Integer> noOption() {
        return new ArrayList<Integer>();
    }

    private List<Integer> singleOption(int option) {
        return Arrays.asList(option);
    }
}
