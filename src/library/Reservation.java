package library;

public class Reservation extends Record {
    private static int COUNT = 0;
    private static final int DEFAULT_DAYS = 10;
    private int id;

    public Reservation(BookItem bookItem, MemberShip memberShip) {
        super(bookItem, memberShip, DEFAULT_DAYS);
        this.id = COUNT++;
    }
}
