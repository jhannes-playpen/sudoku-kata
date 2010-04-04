package com.brodwall.kata.sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SudokuBoard {
    private Integer[][] board = new Integer[SIZE][SIZE];
    private static final int SIZE = 9;

    public boolean isFilled(int row, int column) {
        return board[row][column] != null;
    }

    public List<Integer> getOptionsForCell(int row, int column) {
        List<Integer> options = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        removeOptionsUsedInRow(options, row);
        removeOptionsUsedInColumn(options, column);
        removeOptionsUsedInBox(options, row, column);
        return options;
    }

    private void removeOptionsUsedInBox(List<Integer> options, int row, int column) {
        int boxRow = row - row%3;
        int boxColumn = column- column%3;
        for (int rowIndex=0; rowIndex<3; rowIndex++) {
            for (int columnIndex = 0; columnIndex < 3; columnIndex++) {
                options.remove(board[boxRow + rowIndex][boxColumn + columnIndex]);
            }
        }
    }

    private void removeOptionsUsedInColumn(List<Integer> options, int column) {
        for (int row=0; row<SIZE; row++) options.remove(board[row][column]);
    }

    private void removeOptionsUsedInRow(List<Integer> options, int row) {
        for (int column=0; column<SIZE; column++) options.remove(board[row][column]);
    }

    public void setCellValue(int row, int column, int value) {
        this.board[row][column] = value;
    }

    public void clearCell(int row, int column) {
        this.board[row][column] = null;
    }

    public int getCellValue(int row, int column) {
        return board[row][column];
    }

    public String asString() {
        StringBuilder result = new StringBuilder();
        for (Integer[] rowValues : board) {
            for (Integer value : rowValues) {
                result.append(value != null ? value.toString() : ".");
            }
            result.append("\n");
        }
        return result.toString();
    }

    public void readBoard(String boardAsString) {
        int index = 0;
        for (int row=0; row<SIZE; row++) {
            for (int column=0; column<SIZE; column++) {
                char cellValue = boardAsString.charAt(index++);
                if (cellValue == '.') clearCell(row, column);
                else setCellValue(row, column, Integer.parseInt(cellValue+""));
            }
        }
    }
}
