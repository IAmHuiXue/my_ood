package sudoku_solver;

public class Test {
    public static void main(String[] args) {
        int[][] board = {
                {0,2,0,0,0,0,0,3,1},
                {7,0,0,0,0,3,0,0,0},
                {0,0,0,1,4,0,2,9,0},
                {0,5,2,7,6,4,0,1,8},
                {0,6,3,0,1,2,7,5,9},
                {0,7,8,0,0,0,4,0,0},
                {2,0,0,3,7,0,0,0,5},
                {0,1,0,0,0,0,9,0,0},
                {5,4,0,0,8,1,0,0,0}
        };
        int[] values = {0,2,0,0,0,0,0,3,1,7,0,0,0,0,3,0,0,0, 0,0,0,1,4,0,2,9,0, 0,5,2,7,6,4,0,1,8, 0,6,3,0,1,2,7,5,9,
                0,7,8,0,0,0,4,0,0, 2,0,0,3,7,0,0,0,5, 0,1,0,0,0,0,9,0,0, 5,4,0,0,8,1,0,0,0};

//        // Test for SudokuSolver1
//        SudokuSolver1.play(board);
//        // has a static method to convert the 1D array format to 2D board
//        SudokuSolver1.play(SudokuSolver1.makeBoard(values));
//
//        // Test for SudokuSolver2
//        SudokuSolver2 solver = new SudokuSolver2(board);
//        solver.play();
        // accepts another input format:
        SudokuSolver2 solver2 = new SudokuSolver2(values);
        solver2.play();
    }
}
