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
}
