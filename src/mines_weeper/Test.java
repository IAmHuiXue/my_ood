package mines_weeper;

public class Test {
    public static void main(String[] args) {
        Board board = new Board(4, 5, 3);
        board.print();
        board.reveal(1, 2);
    }
}
