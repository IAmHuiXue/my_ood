package library;

import java.util.Calendar;

public class BookItem extends Book {
    private final String barCode;
    private boolean borrowed;
    private Calendar borrowedTime;
    private boolean reserved;
    private Calendar reservedTime;

    public BookItem(String title, String barCode) {
        super(title);
        this.barCode = barCode;
    }

    public boolean borrow() {
        if (!canBorrow()) {
            return false;
        }
        this.borrowedTime = Calendar.getInstance();
        this.borrowed = true;
        return true;
    }

    public boolean reserve() {
        if (!canReserve()) {
            return false;
        }
        this.reservedTime = Calendar.getInstance();
        this.reserved = true;
        return true;
    }

    public boolean canBorrow() {
        return !this.borrowed;
    }

    public boolean canReserve() {
        return !this.reserved;
    }

    public String getBarCode() {
        return this.barCode;
    }

    public void makeAvailable() {
        this.borrowed = false;
        this.borrowedTime = null;
        this.reserved = false;
        this.reservedTime = null;
    }

    public static void main(String[] args) {
        Book book = new BookItem("<Hello World>", "S980291Q");
        System.out.println(book.getTitle());
        System.out.println(Calendar.getInstance());
    }

}
