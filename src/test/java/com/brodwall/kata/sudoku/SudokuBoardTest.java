package com.brodwall.kata.sudoku;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class SudokuBoardTest {
    private SudokuBoard board = new SudokuBoard();

    @Test
    public void emptyBoardShouldNotBeFilled() {
        assertThat(board.isFilled(3,2)).isFalse();
    }

    @Test
    public void shouldSetCellValue() throws Exception {
        board.setCellValue(2,4, 9);
        assertThat(board.getCellValue(2,4)).isEqualTo(9);
        assertThat(board.isFilled(2,4)).isTrue();
    }

    @Test
    public void shouldClearCell() throws Exception {
        board.setCellValue(2,4, 9);
        board.clearCell(2,4);
        assertThat(board.isFilled(2,4)).isFalse();
    }

    @Test
    public void shouldIncludeAllOptionsOnEmptyBoard() throws Exception {
        assertThat(board.getOptionsForCell(0,0)).containsOnly(1,2,3,4,5,6,7,8,9);
    }

    @Test
    public void shouldRemoveOptionsOnSameRow() throws Exception {
        int row = 3;
        board.setCellValue(row, 0, 1);
        board.setCellValue(row, 8, 2);
        assertThat(board.getOptionsForCell(row, 3)).excludes(1,2);
        assertThat(board.getOptionsForCell(row+1, 3)).contains(1,2);
    }

    @Test
    public void shouldRemoveOptionsOnSameColumn() throws Exception {
        int column = 4;
        board.setCellValue(0, column, 8);
        board.setCellValue(8, column, 9);
        assertThat(board.getOptionsForCell(3, column)).excludes(8,9);
        assertThat(board.getOptionsForCell(3, column+1)).contains(8,9);
    }

    @Test
    public void shouldRemoveOptionsInSameBox() throws Exception {
        board.setCellValue(3,3, 1);
        board.setCellValue(3,5, 2);
        board.setCellValue(5,5, 3);
        assertThat(board.getOptionsForCell(4,4)).excludes(1,2,3);
        assertThat(board.getOptionsForCell(4,5)).excludes(1,2,3);
    }

    @Test
    public void shouldDumpBoardAsString() throws Exception {
        board.setCellValue(0,0, 1);
        board.setCellValue(0,8, 2);
        board.setCellValue(8,8, 9);

        String[] boardAsString = board.dumpBoard().split("\n");
        assertThat(boardAsString).hasSize(9);
        for (String line : boardAsString) {
            assertThat(line).hasSize(9);
        }
        assertThat(boardAsString[0]).startsWith("1").endsWith("2");
        assertThat(boardAsString[1]).matches("\\.{9}");
        assertThat(boardAsString[8]).endsWith("9");
    }

    @Test
    public void shouldReadBoard() throws Exception {
        StringBuilder boardAsString = repeat('.', 9*9);
        boardAsString.setCharAt(0*9 + 0, '1');
        boardAsString.setCharAt(0*9 + 8, '2');
        boardAsString.setCharAt(8*9 + 8, '9');

        board.readBoard(boardAsString.toString());
        assertThat(board.getCellValue(0,0)).isEqualTo(1);
        assertThat(board.getCellValue(0,8)).isEqualTo(2);
        assertThat(board.getCellValue(8,8)).isEqualTo(9);
        assertThat(board.isFilled(0,1)).isFalse();
    }

    private StringBuilder repeat(char c, int times) {
        StringBuilder result = new StringBuilder();
        for (int i=0; i<times; i++) result.append(c);
        return result;
    }
}
