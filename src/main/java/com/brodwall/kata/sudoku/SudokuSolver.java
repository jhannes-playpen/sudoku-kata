package com.brodwall.kata.sudoku;

public class SudokuSolver {
    private static final int SIZE = 9;
    private SudokuBoard board = new SudokuBoard();

    public SudokuSolver(String puzzle) {
        board.readBoard(puzzle);        
    }

    public SudokuSolver(SudokuBoard board) {
        this.board = board;
    }

    public boolean findSolution(SudokuBoard board) {
        return findSolution(board, 0);
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

    public void solve() {
        findSolution(board);
    }

    public SudokuBoard getBoard() {
        return board;
    }
}
