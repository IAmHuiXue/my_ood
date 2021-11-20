package library;

import java.util.*;

public class SilverMember implements MemberShip {
    private final int MAX_ALLOWED_ITEMS = 5;
    private Map<BookItem, Transaction> borrows;
    private Map<BookItem, Reservation> reserves;
    private List<Record> archives;
    private User user;

    public SilverMember(User user) {
        this.borrows = new HashMap<>();
        this.reserves = new HashMap<>();
        this.user = user;
    }


    @Override
    public Transaction borrow(BookItem book) {
        if (!canBorrow(book)) {
            return null;
        }
        Transaction record = new Transaction(book, this);
        book.borrow();
        borrows.put(book, record);
        return record;
    }

    @Override
    public Reservation reserve(BookItem book) {
        if (!canReserve(book)) {
            return null;
        }
        Reservation record = new Reservation(book, this);
        book.reserve();
        reserves.put(book, record);
        return record;
    }

    @Override
    public void returnBack(BookItem book) throws FineRequiredException { // 逻辑乱
        Transaction record = borrows.remove(book);
        if (record == null) {
            throw new IllegalArgumentException("No such a book borrowed.");
        }
        archives.add(record);
        book.makeAvailable();
        Calendar calendar = Calendar.getInstance();
        if (record.getDueDate().compareTo(calendar) < 0) {
            throw new FineRequiredException();
        }
    }

    @Override
    public boolean payFine() {
        // todo:
        return true;
    }

    private boolean canBorrow(BookItem book) {
        return book.canBorrow() && borrows.size() < MAX_ALLOWED_ITEMS;
    }

    private boolean canReserve(BookItem book) {
        return book.canReserve() && reserves.size() < MAX_ALLOWED_ITEMS;
    }
}
