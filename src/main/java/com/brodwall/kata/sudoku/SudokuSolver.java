package com.brodwall.kata.sudoku;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;


public class SudokuSolver {
    private SudokuBoard board;
    private static final int SIZE = 9;

    public SudokuSolver(SudokuBoard board) {
        this.board = board;
    }

    public SudokuSolver(String puzzle) {
        this.board = new SudokuBoard();
        board.readBoard(puzzle);
    }

    public boolean solve() {
        return findSolution(0);
    }

    private boolean findSolution(int index) {
        int row = index/SIZE, column = index%SIZE;

        if (index == SIZE*SIZE) return true;
        if (board.isFilled(row, column)) return findSolution(index+1);

        List<Integer> optionsForCell = board.getOptionsForCell(row, column);
        for (Integer option : optionsForCell) {
            board.setCellValue(row, column, option);
            if (findSolution(index+1)) return true;
        }
        board.clearCell(row, column);
        return false;
    }

    public String dumpBoard() {
        return board.dumpBoard();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        String line;
        while ((line = reader.readLine()) != null) {
            SudokuSolver solver = new SudokuSolver(line);
            System.out.println("Solving:\n" + solver.dumpBoard());
            solver.solve();
            System.out.println("Solved:\n" + solver.dumpBoard());
        }
    }
}
