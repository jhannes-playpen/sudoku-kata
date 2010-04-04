package com.brodwall.kata.sudoku;

public class SudokuSolver {
    private static final int SIZE = 9;

    public boolean findSolution(SudokuBoard board) {
        for (int index=0; index<SIZE*SIZE; index++) {
            int row = index/SIZE, column = index%SIZE;
            if (!board.isFilled(row, column)) {
                if (board.getSolutionsFor(row, column).isEmpty()) return false;
            }
        }
        return true;
    }
}
