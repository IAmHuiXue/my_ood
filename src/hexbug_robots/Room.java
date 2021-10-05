package hexbug_robots;

import java.util.Arrays;
import java.util.Random;

public class Room {
    private static final Random RANDOM = new Random();
    private final int[][] floor;
    private final int rows;
    private final int cols;
    private State state; // to represent the status of the robot

    // each instance of class 'Room' should have its own state
    // therefore class 'State' is an inner class
    private static class State { //
        HexBug robot;
        Dir direction;
        int row;
        int col;

        State(HexBug robot, Dir direction, int x, int y) {
            this.robot = robot;
            this.direction = direction;
            this.row = x;
            this.col = y;
        }
    }

    public Room(int x, int y) {
        if (x <= 0 || y <= 0) {
            throw new IllegalArgumentException("Invalid room size");
        }
        floor = new int[x][y];
        rows = x;
        cols = y;
    }

    public boolean placeHexBug(HexBug robot, int x, int y, Dir direction) {
        if (robot == null || isInvalidPosition(x, y)) {
            return false;
        }
        state = new State(robot, direction, x, y);
        return true;
    }

    public void roomState() {
        System.out.print("[");
        for (int i = 0; i < rows; i++) {
            System.out.print("[");
            for (int j = 0; j < cols; j++) {
                if (state != null && i == state.row && j == state.col) {
                    System.out.print(state.direction);
                } else {
                    System.out.print(floor[i][j]);
                }
                if (j + 1 < cols) {
                    System.out.print(", ");
                }
            }
            if (i + 1 < rows) {
                System.out.println("]");
            } else {
                System.out.println("]]");
            }
        }
        System.out.println();
    }

    public void pushHexbugButton() {
        if (state == null) {
            throw new IllegalArgumentException("No robot");
        }
        int steps = state.robot.getMoveDistance();
        while (steps != 0) {
            int neiX = state.row + state.direction.getX();
            int neiY = state.col + state.direction.getY();
            if (isInvalidPosition(neiX, neiY)) {
                int pick = RANDOM.nextInt(Dir.values().length);
                while (Dir.values()[pick] == state.direction) {
                    pick = RANDOM.nextInt(Dir.values().length);
                }
                state.direction = Dir.values()[pick];
            } else {
                state.row = neiX;
                state.col = neiY;
                steps--;
            }
        }
    }

    private boolean isInvalidPosition(int x, int y) {
        return x < 0 || x >= rows || y < 0 || y >= cols;
    }
}

