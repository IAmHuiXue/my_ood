package library;

import java.util.Calendar;

public abstract class Record {
    private final Calendar dueDate;
    private final BookItem bookItem;
    private final MemberShip memberShip;

    public Record(BookItem bookItem, MemberShip memberShip, int days) {
        this.bookItem = bookItem;
        this.memberShip = memberShip;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, days);
        this.dueDate = calendar;
    }

    public MemberShip getMemberShip() {
        return this.memberShip;
    }

    public Calendar getDueDate() {
        return this.dueDate;
    }
}
