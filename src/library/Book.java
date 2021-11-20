package library;

public abstract class Book {
    private String ISBN;
    private final String title;
    private String subject;
    private String publisher;
    private int numOfPages;
//    private List<Author> authors;

    public Book(String title) {
        this(null, title, null, null, 0);
    }

    public Book(String ISBN, String title, String subject, String publisher, int numOfPages) {
        this.ISBN = ISBN;
        this.title = title;
        this.subject = subject;
        this.publisher = publisher;
        this.numOfPages = numOfPages;
    }

    public String getTitle() {
        return this.title;
    }


}
