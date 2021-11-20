package library;

public class Transaction extends Record {
    private static int COUNT = 0;
    private static final int DEFAULT_DAYS = 30;
    private int id;

    public Transaction(BookItem bookItem, MemberShip memberShip) {
        super(bookItem, memberShip, DEFAULT_DAYS);
        this.id = COUNT++;
    }
}
