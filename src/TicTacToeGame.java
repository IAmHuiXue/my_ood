
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TicTacToeGame {
    private Player one;
    private Player two;
    private GameBoard board;
    private Map<Player, List<Move>> record;

    public TicTacToeGame(Player p1, Player p2, int size) {
        this.one = p1;
        this.two = p2;
        board = new TicTacToeBoard(one, two, size);
        record = new HashMap<>();
        record.put(one, new ArrayList<>());
        record.put(two, new ArrayList<>());
    }

    public boolean isValidMove(Move move) {
        return board.isValidMove(move);
    }

    /* return int 1 -> someone wins, 0 -> tie, -1 -> no one wins yet */
    public int makeMove(Move move, Player player) {
        int status = board.placeMove(move, player);
        record.get(player).add(move);
        return status;
    }

    public Player getWinner() {
        return board.getWinner();
    }

    public boolean anyWinner() {
        return board.getWinner() != null;
    }
}

interface GameBoard {
    Player getWinner();
    int placeMove(Move move, Player player) throws IllegalArgumentException;
    boolean isValidMove(Move move);
}

class Move {
    int row;
    int col;
}

class Player {
    private int id;
    private int score;

    public int addScore(int score) {
        this.score += score;
        return this.score;
    }
}

class TicTacToeBoard implements GameBoard {
    private final static int[] SIGN = new int[]{1, -1};
    private Map<Player, Integer> hands;
    private final int[][] board;
    private int[] cols;
    private int[] rows;
    private int diag;
    private int antiDiag;
    private Player winner;
    private int occupied;

    public TicTacToeBoard(Player p1, Player p2, int size) {
        hands = new HashMap<>();
        hands.put(p1, SIGN[0]);
        hands.put(p2, SIGN[1]);
        board = new int[size][size];
        rows = new int[size];
        cols = new int[size];
    }

    @Override
    public Player getWinner() {
        return winner;
    }

    @Override
    /* return int 1 -> someone wins, 0 -> tie, -1 -> no one wins yet */
    public int placeMove(Move move, Player player) throws IllegalArgumentException {
        if (!isValidMove(move)) {
            throw new IllegalArgumentException("invalid, move");
        }
        int sign = hands.get(player);
        board[move.row][move.col] = sign;
        if (move.row == move.col) {
            diag += sign;
        }
        if (move.row + move.col == board.length - 1) {
            antiDiag += sign;
        }
        cols[move.col] += sign;
        rows[move.row] += sign;
        occupied++;
        int sum = board.length;
        if (cols[move.col] == Math.abs(sum)
                || rows[move.row] == Math.abs(sum)
                || antiDiag == Math.abs(sum)
                || diag == Math.abs(sum)) {
            winner = player;
            return 1;
        }
        if (occupied == sum * sum) {
            return 0; // tied
        }
        return -1;
    }

    @Override
    public boolean isValidMove(Move move) {
        if (move.row < 0 || move.row > board.length || move.col < 0 || move.col > board.length) {
            return false;
        }
        return board[move.row][move.col] == 0;
//        return board[move.row][move.col] != SIGN[0] && board[move.row][move.col] != SIGN[1];
    }
}
