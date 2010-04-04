package com.brodwall.kata.sudoku;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class SudokuBoardTest {

    @Test
    public void emptyBoardShouldHaveAllUnfilled() {
        SudokuBoard board = new SudokuBoard();
        assertThat(board.isFilled(1,4)).isFalse();
    }

}
