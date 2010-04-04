package com.brodwall.kata.sudoku;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class SudokuBoardTest {
    private SudokuBoard board = new SudokuBoard();

    @Test
    public void emptyBoardShouldHaveAllUnfilled() {
        assertThat(board.isFilled(1,4)).isFalse();
    }

    @Test
    public void shouldSetCellValue() throws Exception {
        board.setCellValue(3, 1, 8);
        assertThat(board.getCellValue(3,1)).isEqualTo(8);
        assertThat(board.isFilled(3,1)).isTrue();
    }

    @Test
    public void shouldClearCell() throws Exception {
        board.setCellValue(1,2, 3);
        board.clearCell(1,2);
        assertThat(board.isFilled(1,2)).isFalse();
    }

    @Test
    public void shouldReturnAllOptionsForEmptyCell() throws Exception {
        assertThat(board.getOptionsForCell(0,0)).contains(1,2,3,4,5,6,7,8,9);
    }

    @Test
    public void shouldNotAllowValuesUsedInSameRow() throws Exception {
        int row = 3;
        board.setCellValue(row, 0, 1);
        board.setCellValue(row, 8, 2);
        assertThat(board.getOptionsForCell(row, 4)).excludes(1,2);
        assertThat(board.getOptionsForCell(row+1, 4)).contains(1,2);
    }
}
