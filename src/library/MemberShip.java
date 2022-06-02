package library;

public interface MemberShip {
    Transaction borrow(BookItem book);

    Reservation reserve(BookItem book);

    void returnBack(BookItem book) throws FineRequiredException;

    void unReserve(BookItem book) throws FineRequiredException;

    boolean payFine();
}
