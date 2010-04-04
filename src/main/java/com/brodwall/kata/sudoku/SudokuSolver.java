package com.brodwall.kata.sudoku;

public class SudokuSolver {
    private static final int SIZE = 9;

    public boolean findSolution(SudokuBoard solver) {
        for (int index=0; index<SIZE*SIZE; index++) {
            int row = index/SIZE, column = index%SIZE;
            if (!solver.isFilled(row, column)) return false;
        }
        return true;
    }
}
