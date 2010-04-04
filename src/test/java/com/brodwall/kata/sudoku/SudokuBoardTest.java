package com.brodwall.kata.sudoku;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class SudokuBoardTest {
    private SudokuBoard board = new SudokuBoard();

    @Test
    public void shouldStartOutUnfilled() {
        assertThat(board.isFilled(0,0)).isFalse();
        assertThat(board.isFilled(8,8)).isFalse();
    }

    @Test
    public void shouldFillCell() throws Exception {
        board.setCellValue(3,4, 9);
        assertThat(board.isFilled(3,4)).isTrue();
        assertThat(board.getCellValue(3, 4)).isEqualTo(9);
    }

    @Test
    public void shouldClearCell() throws Exception {
        board.setCellValue(3,4, 9); board.clearCell(3,4);
        assertThat(board.isFilled(3,4)).isFalse();
    }

    @Test
    public void shouldReturnAllOptionsOnEmptyBoard() throws Exception {
        assertThat(board.getOptionsForCell(0,0)).contains(1,2,3,4,5,6,7,8,9);
    }

    @Test
    public void shouldRemoveOptionsUsedInRow() throws Exception {
        int row = 3;
        board.setCellValue(row, 0, 1);
        board.setCellValue(row, 8, 2);
        assertThat(board.getOptionsForCell(row, 3)).excludes(1,2);
        assertThat(board.getOptionsForCell(row+1, 3)).contains(1,2);
    }

    @Test
    public void shouldRemoveOptionsUsedInColumn() throws Exception {
        int column = 3;
        board.setCellValue(0, column, 9);
        board.setCellValue(8, column, 8);
        assertThat(board.getOptionsForCell(3, column)).excludes(8,9);
        assertThat(board.getOptionsForCell(3, column+1)).contains(8,9);
    }

    @Test
    public void shouldRemoveOptionsUsedInBox() throws Exception {
        board.setCellValue(3,3, 1);
        board.setCellValue(5,5, 2);
        board.setCellValue(3,5, 3);

        assertThat(board.getOptionsForCell(4,4)).excludes(1,2,3);
    }

    @Test
    public void shouldDumpBoard() throws Exception {
        board.setCellValue(0,0, 1);
        board.setCellValue(0,8, 2);
        board.setCellValue(8,8, 9);

        String boardAsString = board.asString();
        String[] lines = boardAsString.split("\n");
        assertThat(lines).hasSize(9);
        assertThat(lines[0]).isEqualTo("1.......2");
        assertThat(lines[1]).isEqualTo(".........");
        assertThat(lines[8]).isEqualTo("........9");
    }

    @Test
    public void shouldReadBoard() throws Exception {
        StringBuilder boardAsString = repeat('.', 9*9);
        boardAsString.setCharAt(0*9+0, '1');
        boardAsString.setCharAt(0*9+8, '2');
        boardAsString.setCharAt(8*9+8, '9');
        board.readBoard(boardAsString.toString());

        assertThat(board.getCellValue(0,0)).isEqualTo(1);
        assertThat(board.isFilled(0,1)).isFalse();
        assertThat(board.getCellValue(0,8)).isEqualTo(2);
        assertThat(board.getCellValue(8,8)).isEqualTo(9);
    }

    private StringBuilder repeat(char c, int length) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) builder.append(c);
        return builder;
    }
}
