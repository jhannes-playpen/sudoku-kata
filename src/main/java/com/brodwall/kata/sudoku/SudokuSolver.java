package com.brodwall.kata.sudoku;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class SudokuSolver {
    private static final int SIZE = 9;

    public boolean findSolution(SudokuBoard board) {
        return findSolution(board, 0);
    }

    private boolean findSolution(SudokuBoard board, int cellIndex) {
        if (cellIndex == SIZE*SIZE) return true;
        int row = cellIndex /SIZE, column = cellIndex %SIZE;

        if (board.isFilled(row, column))  return findSolution(board, cellIndex+1);

        List<Integer> optionsForCell = board.getOptionsForCell(row, column);
        for (Integer option : optionsForCell) {
            board.setCellValue(row, column, option);
            if (findSolution(board, cellIndex+1)) return true;
        }
        board.clearCell(row, column);
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        String line;
        while ((line = reader.readLine()) != null) {
            SudokuBoard board = new SudokuBoard();
            board.readBoard(line);
            System.out.println("Solving: \n" + board.asString());
            SudokuSolver solver = new SudokuSolver();
            solver.findSolution(board);
            System.out.println("Solved: \n" + board.asString());
        }
    }
}
