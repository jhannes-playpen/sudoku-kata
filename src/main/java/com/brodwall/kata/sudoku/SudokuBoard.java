package com.brodwall.kata.sudoku;

import java.util.List;

public class SudokuBoard {
    private Integer[][] board = new Integer[SIZE][SIZE];
    private static final int SIZE = 9;

    public boolean isFilled(int row, int column) {
        return board[row][column] != null;
    }

    public List<Integer> getOptionsForCell(int row, int column) {
        return null;
    }

    public void setCellValue(int row, int column, int value) {
        board[row][column] = value;
    }

    public void clearCell(int row, int column) {
    }

    public int getCellValue(int row, int column) {
        return board[row][column];
    }
}
