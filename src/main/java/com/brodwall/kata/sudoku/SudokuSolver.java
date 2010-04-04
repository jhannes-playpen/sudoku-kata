package com.brodwall.kata.sudoku;

public class SudokuSolver {
    private static final int SIZE = 9;

    public boolean findSolution(SudokuBoard board) {
        return findSolution(board, 0);
    }

    private boolean findSolution(SudokuBoard board, int cell) {
        if (cell == SIZE*SIZE) return true;
        int row = cell/SIZE, column = cell % SIZE;
        if (!board.isFilled(row, column)) {
            for (Integer option : board.getOptionsForCell(row, column)) {
                board.setCellValue(row, column, option);
                if (findSolution(board, cell+1)) return true;
            }
            board.clearCellValue(row, column);
            return false;
        } else {
            return findSolution(board, cell+1);
        }
    }
}
