package mines_weeper;

import java.util.ArrayDeque;
import java.util.Queue;

public class Board {
    private static final int[][] DIRS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, 1}, {-1, -1}, {1, -1}, {-1, 1}};
    private final int rows;
    private final int cols;
    private int[][] board;
    private boolean[][] positionOfMines;
    private boolean[][] reveal;

    public Board(int rows, int cols, int mines) {
        if (rows <= 0 || cols <= 0 || mines <= 0 || mines > rows * cols) {
            // TODO:  what is the max num of mines
            throw new IllegalArgumentException();
        }
        this.rows = rows;
        this.cols = cols;
        board = new int[rows][cols];
        positionOfMines = new boolean[rows][cols];
        reveal = new boolean[rows][cols];
        placeMines(mines);
    }

    public void reveal(int x, int y) {
        if (!inBounds(x, y)) {
            throw new IllegalArgumentException("Invalid position");
        }
        reveal[x][y] = true;
        if (!isMine(x, y) && board[x][y] == 0) {
            updateReveal(x, y);
        }

        for (int i = 0; i < rows; i++) {
            if (i != 0) {
                System.out.println();
            }
            for (int j = 0; j < cols; j++) {
                if (i == x && j == y) {
                    if (isMine(i, j)) {
                        System.out.print("* ");
                    } else {
                        System.out.print(board[i][j] + " ");
                    }
                } else {
                    if (reveal[i][j]) {
                        System.out.println(board[i][j]);
                    } else {
                        System.out.print("- ");
                    }
                }
            }
        }
        System.out.println("\n");
    }

//    public void reveal(int x, int y) {
//        for (int i = 0; i < rows; i++) {
//            if (i != 0) {
//                System.out.println();
//            }
//            for (int j = 0; j < cols; j++) {
//                if (i == x && j == y) {
//                    if (positionOfMines[i][j]) {
//                        System.out.print("* ");
//                    } else {
//                        System.out.print(board[i][j] + " ");
//                    }
//                } else {
//                    System.out.print("- ");
//                }
//            }
//        }
//        System.out.println("\n");
//    }

    protected void print() {
        for (int i = 0; i < rows; i++) {
            if (i != 0) {
                System.out.println();
            }
            for (int j = 0; j < cols; j++) {
                if (isMine(i, j)) {
                    System.out.print("* ");
                } else {
                    System.out.print(board[i][j] + " ");
                }
            }
        }
        System.out.println("\n");
    }

    private void updateReveal(int x, int y) {
        // the cur position is not boom, is in bound, is revealed
        Queue<Cell> q = new ArrayDeque<>();
        q.offer(new Cell(x, y));
        while (!q.isEmpty()) {
            Cell cur = q.poll();
            for (int[] dir : DIRS) {
                int neiR = cur.r + dir[0];
                int neiC = cur.c + dir[1];
                if (inBounds(neiR,neiC) && !isMine(neiR, neiC) && !reveal[neiR][neiC]) {
                    reveal[neiR][neiC] = true;
                    q.offer(new Cell(neiR, neiC));
                }
            }
        }
    }

    private void updateCount() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (isMine(i, j)) {
                    for (int[] dir : DIRS) {
                        int neiI = i + dir[0];
                        int neiJ = j + dir[1];
                        if (inBounds(neiI, neiJ) && !isMine(neiI, neiJ)) {
                            board[neiI][neiJ]++;
                        }
                    }
                }
            }
        }
    }

    private void placeMines(int numMines) {
        while (numMines != 0) {
            int x = (int) (Math.random() * rows); // x -> [0, rows)
            int y = (int) (Math.random() * cols); // y -> [0, cols)
            if (!isMine(x, y)) {
                positionOfMines[x][y] = true;
                numMines--;
            }
        }
        updateCount();
    }

    private boolean inBounds(int r, int c) {
        return r >= 0 && r < rows && c >= 0 && c < cols;
    }

    private boolean isMine(int r, int c) {
        return positionOfMines[r][c];
    }
    static class Cell {
        int r;
        int c;

        Cell(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
