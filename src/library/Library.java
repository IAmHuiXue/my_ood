package library;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
reserve a book (membership) - returns reservation record or raise exception
        borrow a book (membership) - returns transaction record or raise exception
        search a book by title (String title) - List<BookItem>
        create membership (user) - returns membership record. with default expiry date
        get membership by user - return membership record
 */

public class Library {
    private Map<Integer, MemberShip> members;  // user id - > membership
    private Map<String, List<MemberShip>> records; // user name -> membership
    private Map<BookItem, Transaction> lendHistory;
    private Map<BookItem, Reservation> reserveHistory;
    private final Catalog catalog;

    public Library(List<BookItem> books) {
        this.catalog = new Catalog(books);
        this.members = new HashMap<>();
        this.records = new HashMap<>();
        this.lendHistory = new HashMap<>();
        this.reserveHistory = new HashMap<>();
    }

    public Transaction lend(BookItem bookItem, MemberShip memberShip) {
        Transaction transaction = memberShip.borrow(bookItem);
        if (transaction != null) {
            lendHistory.put(bookItem, transaction);
        }
        return transaction;
    }

    public List<BookItem> searchByTitle(String title) {
        return this.catalog.search(title);
    }

    public BookItem getBook(String barCode) {
        return this.catalog.getBookByCode(barCode);
    }

    public void returnBack(BookItem bookItem, MemberShip memberShip) throws FineRequiredException {
        memberShip.returnBack(bookItem);
        lendHistory.remove(bookItem);
    }

    public MemberShip join(User user) {
        if (getMember(user) != null) {
            return getMember(user);
        }
        MemberShip memberShip = new SilverMember(user);
        records.computeIfAbsent(user.getName(), k -> new ArrayList<>()).add(memberShip);
        members.put(user.getId(), memberShip);
        return memberShip;
    }

    public MemberShip getMember(User user) {
        return members.get(user.getId());
    }
}
