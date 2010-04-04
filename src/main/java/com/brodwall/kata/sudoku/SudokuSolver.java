package com.brodwall.kata.sudoku;

public class SudokuSolver {
    private static final int SIZE = 9;

    public boolean findSolution(SudokuBoard board) {
        return findSolution(board, 0);
    }

    private boolean findSolution(SudokuBoard board, int index) {
        if (index == SIZE*SIZE) return true;
        
        int row = index/SIZE, column = index%SIZE;
        if (!board.isFilled(row, column)) {
            if (board.getOptionsForCell(row, column).isEmpty()) return false;
            int value = board.getOptionsForCell(row, column).get(0);
            board.setCellValue(row, column, value);
        }
        return findSolution(board, index+1);
    }
}
