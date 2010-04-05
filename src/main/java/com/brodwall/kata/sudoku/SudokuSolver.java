package com.brodwall.kata.sudoku;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SudokuSolver {
    private static final int SIZE = 9;
    private SudokuBoard board = new SudokuBoard();

    public SudokuSolver(String puzzle) {
        board.readBoard(puzzle);        
    }

    public SudokuSolver(SudokuBoard board) {
        this.board = board;
    }

    private boolean findSolution(SudokuBoard board, int index) {
        int row = index/SIZE, column = index%SIZE;

        if (index == SIZE*SIZE) return true;
        if (board.isFilled(row, column))  return findSolution(board, index+1);

        for (Integer value : board.getOptionsForCell(row, column)) {
            board.setCellValue(row, column, value);
            if (findSolution(board, index+1)) return true;
        }
        board.clearCell(row, column);
        return false;
    }

    public boolean solve() {
        return findSolution(board, 0);
    }

    public String dumpBoard() {
        return board.dumpBoard();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        String line;
        while ((line=reader.readLine()) != null) {
            SudokuSolver solver = new SudokuSolver(line);
            System.out.println("Solving: \n" + solver.dumpBoard());
            solver.solve();
            System.out.println("Solved: \n" + solver.dumpBoard());
        }
    }
}
