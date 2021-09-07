package sudoku_solver;

/**
 * Sudoku (数独, sūdoku, digit-single) (/suːˈdoʊkuː/, /-ˈdɒk-/, /sə-/, originally called Number Place)
 * is a logic-based, combinatorial number-placement puzzle.
 * In classic sudoku, the objective is to fill a 9×9 grid with digits so that each column,
 * each row, and each of the nine 3×3 subgrids that compose the grid (also called "boxes", "blocks", or "regions")
 * contain all the digits from 1 to 9. The puzzle setter provides a partially completed grid,
 * which for a well-posed puzzle has a single solution.
 */

public class SudokuSolver1 {
    private static final byte BOARD_SIZE = 9;
    private static final int[] NUMBERS = {1, 2, 3, 4, 5, 6, 7, 8, 9};

    public static int[][] makeBoard(int[] values) {
        if (values == null || values.length != BOARD_SIZE * BOARD_SIZE) {
            throw new IllegalArgumentException("Invalid values input, please try 81 numbers");
        }
        int[][] board = new int[BOARD_SIZE][BOARD_SIZE];
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                board[row][col] = values[row * BOARD_SIZE + col];
            }
        }
        return board;
    }

    public static void play(int[][] board) {
        if (board == null || board.length != BOARD_SIZE || board[0].length != BOARD_SIZE) {
            throw new IllegalArgumentException("Invalid board passed, please try 9x9 grid");
        }
        System.out.println("Input per below:");
        System.out.println();
        printBoard(board);
        if (solveBoard(board)) {
            System.out.println();
            System.out.println("Solved per below:");
            System.out.println();
            printBoard(board);
        } else {
            System.out.println();
            System.out.println("Error, invalid input on board");
        }
    }

    private static boolean solveBoard(int[][] board) {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (board[row][col] == 0) {
                    for (int numberToTry : NUMBERS) {
                        if (isValidPlacement(board, numberToTry, row, col)) {
                            board[row][col] = numberToTry;
                            if (solveBoard(board)) {
                                return true;
                            }
                            board[row][col] = 0;
                        }
                    }
                    // at this position, none of the num from 1 to 9 can solve the problem
                    return false;
                }
            }
        }
        return true;
    }

    public static void printBoard(int[][] board) {
        for (int row = 0; row < BOARD_SIZE; row++) {
            if (row != 0 && row % 3 == 0) {
                System.out.println("-----------");
            }
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (col != 0 & col % 3 == 0) {
                    System.out.print("|");
                }
                System.out.print(board[row][col]);
            }
            System.out.println();
        }
    }

    private static boolean isNumberInRow(int[][] board, int num, int row) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[row][i] == num) {
                return true;
            }
        }
        return false;
    }

    private static boolean isNumberInColumn(int[][] board, int num, int col) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[i][col] == num) {
                return true;
            }
        }
        return false;
    }

    private static boolean isNumberInBox(int[][] board, int num, int row, int col) {
        int localBoxRow = row - row % 3;
        int localBoxCol = col - col % 3;
        for (int i = localBoxRow; i < localBoxRow + 3; i++) {
            for (int j = localBoxCol; j < localBoxCol + 3; j++) {
                if (board[i][j] == num) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isValidPlacement(int[][] board, int num, int row, int col) {
        return !isNumberInRow(board, num, row)
                && !isNumberInColumn(board, num, col) && !isNumberInBox(board, num, row, col);
    }
}