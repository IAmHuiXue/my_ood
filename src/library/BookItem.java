package library;

import java.util.Calendar;

public class BookItem extends Book {
    private final String barCode;
    private Status status;
    private Calendar borrowedTime;
    private Calendar reservedTime;

    public BookItem(String title, String barCode) {
        super(title);
        this.barCode = barCode;
        this.status = Status.AVAILABLE;
    }

    public boolean borrow() {
        if (!canBorrow()) {
            return false;
        }
        this.borrowedTime = Calendar.getInstance();
        this.status = Status.BORROWED;
        return true;
    }

    public boolean reserve() {
        if (!canReserve()) {
            return false;
        }
        this.reservedTime = Calendar.getInstance();
        this.status = Status.RESERVED;
        return true;
    }

    public boolean canBorrow() {
        return this.status.equals(Status.AVAILABLE);
    }

    public boolean canReserve() {
        return canBorrow();
    }

    public String getBarCode() {
        return this.barCode;
    }

    public void makeAvailable() {
        this.status = Status.AVAILABLE;
        this.borrowedTime = null;
        this.reservedTime = null;
    }

    public static void main(String[] args) {
        Book book = new BookItem("<Hello World>", "S980291Q");
        System.out.println(book.getTitle());
        System.out.println(Calendar.getInstance());
    }

}
