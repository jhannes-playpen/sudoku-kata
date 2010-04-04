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
            for (Integer value : board.getOptionsForCell(row, column)) {
                board.setCellValue(row, column, value);
                if (findSolution(board, index+1)) return true;
            }
            board.clearCell(row, column);
            return false;
        }
        return findSolution(board, index+1);
    }
}
