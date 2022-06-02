package library;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Catalog {
    private Map<String, List<BookItem>> records;  // title, -> bookItems
    private Map<String, BookItem> recordsByCode;  // barcode -> bookItem

    public Catalog(List<BookItem> books) {
        this.records = new HashMap<>();
        this.recordsByCode = new HashMap<>();
        for (BookItem bookItem : books) {
            String title = bookItem.getTitle();
            this.records.computeIfAbsent(title, k -> new ArrayList<>()).add(bookItem);
            this.recordsByCode.put(bookItem.getBarCode(), bookItem);
        }
    }

    public List<BookItem> search(String title) {
        return this.records.get(title);
    }

    public BookItem getBookByCode(String barCode) {
        return this.recordsByCode.get(barCode);
    }
}
