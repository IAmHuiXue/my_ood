package sudoku_solver;

/**
 * Sudoku (数独, sūdoku, digit-single) (/suːˈdoʊkuː/, /-ˈdɒk-/, /sə-/, originally called Number Place)
 * is a logic-based, combinatorial number-placement puzzle.
 * In classic sudoku, the objective is to fill a 9×9 grid with digits so that each column,
 * each row, and each of the nine 3×3 subgrids that compose the grid (also called "boxes", "blocks", or "regions")
 * contain all the digits from 1 to 9. The puzzle setter provides a partially completed grid,
 * which for a well-posed puzzle has a single solution.
 */

public class SudokuSolver2 {
    private static final byte BOARD_SIZE = 9;
    private static final int[] NUMBERS = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    private int[][] board; // -> should I make it final?

    public SudokuSolver2(int[][] board) {
        if (board == null || board.length != BOARD_SIZE || board[0].length != BOARD_SIZE) {
            throw new IllegalArgumentException("Invalid board input, please try 9x9 grid");
        }
        this.board = board;
    }

    public SudokuSolver2(int[]values) {
        if (values == null || values.length != BOARD_SIZE * BOARD_SIZE) {
            throw new IllegalArgumentException("Invalid values input, please try 81 numbers");
        }
        board = new int[BOARD_SIZE][BOARD_SIZE];
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                board[row][col] = values[row * BOARD_SIZE + col];
            }
        }
    }

    public void play() {
        System.out.println("Input per below:");
        System.out.println();
        printBoard();
        if (solveBoard(0, 0)) {
            System.out.println();
            System.out.println("Solved per below:");
            System.out.println();
            printBoard();
        } else {
            System.out.println();
            System.out.println("Error, invalid input");
        }
    }

    private boolean solveBoard(int row, int col) {
        if (row >= BOARD_SIZE) {
            return true;
        }

        // design logic of moving on board
        boolean nextLine = (col + 1) >= BOARD_SIZE;
        int nextRow = nextLine ? row + 1 : row;
        int nextCol = nextLine ? 0 : col + 1;

        if (board[row][col] == 0) {
            for (int numberToTry : NUMBERS) {
                if (isValidPlacement(numberToTry, row, col)) {
                    board[row][col] = numberToTry;
                    if (solveBoard(nextRow, nextCol)) {
                        return true;
                    }
                    board[row][col] = 0;
                }
            }
            return false;
        }
        return solveBoard(nextRow, nextCol);

    }

    public void printBoard() {
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

    private boolean isNumberInRow(int num, int row) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[row][i] == num) {
                return true;
            }
        }
        return false;
    }

    private boolean isNumberInColumn(int num, int col) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[i][col] == num) {
                return true;
            }
        }
        return false;
    }

    private boolean isNumberInBox(int num, int row, int col) {
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

    private boolean isValidPlacement(int num, int row, int col) {
        return !isNumberInRow(num, row)
                && !isNumberInColumn(num, col) && !isNumberInBox(num, row, col);
    }
}
