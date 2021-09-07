package kindle;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Kindle {
    private List<Book> books;
    private EBookReaderFactory ERF;

    public Kindle(List<Book> books, EBookReaderFactory ERF) {
        this.books = books;
        this.ERF = ERF;
    }
    public void uploadBook(Book b){
        books.add(b);
    }

    public void downloadBook(Book b) {
        books.add(b);
    }
    public void deleteBook(Book b) {
        books.remove(b);
    }
    public String readBook(Book book) throws Exception {
        EBookReader reader = ERF.createReader(book);
        if(reader == null) {
            throw  new Exception("cant read this format");
        }
        return "book content is : " + reader.readBook()+" which is using "+reader.displayReaderType();
    }

}
class Book {
    private Format format;
    private String content;

    public Book(Format format, String content) {
        this.format = format;
        this.content = content;
    }

    public Format getFormat() {
        return format;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "Book{" +
                "format=" + format +
                ", content='" + content + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return format == book.format &&
                Objects.equals(content, book.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(format, content);
    }
}


abstract class EBookReader {
    protected Book book;

    public EBookReader(Book book) {
        this.book = book;
    }
    public abstract String readBook();
    public abstract String displayReaderType();
}

class EpubReader extends EBookReader{

    public EpubReader(Book book) {
        super(book);
    }

    @Override
    public String readBook() {
        return book.getContent()+"."+book.getFormat().getContent();
    }

    @Override
    public String displayReaderType() {
        return "Using Epub reader";
    }
}

class PdfReader extends EBookReader{
    public PdfReader(Book book) {
        super(book);
    }

    @Override
    public String readBook() {

        return book.getContent()+"."+book.getFormat().getContent();
    }

    @Override
    public String displayReaderType() {
        return "Using PDF reader";
    }
}
class MobiReader extends EBookReader{


    public MobiReader(Book book) {
        super(book);
    }

    @Override
    public String readBook() {

        return book.getContent()+"."+book.getFormat().getContent();
    }

    @Override
    public String displayReaderType() {
        return "Using Mobi reader";
    }
}
enum Format {
    EPUB("epub"), PDF("pdf"), MOBI("mobi");

    private String content;

    Format(String content) {

        this.content = content;
    }

    public String getContent() {
        return content;
    }
}

class EBookReaderFactory {
    public EBookReader createReader(Book b) {
        if (b.getFormat() == Format.EPUB) {
            return new EpubReader(b);
        } else if (b.getFormat() == Format.MOBI) {
            return new MobiReader(b);
        } else if (b.getFormat() == Format.PDF) {
            return new PdfReader(b);
        }
        return null;
    }
}
class Test {
    public static void main(String[] args) throws Exception {

        List<Book> b = new ArrayList<>();
        EBookReaderFactory ERF = new EBookReaderFactory();

        Book b1 = new Book(Format.PDF, "西游记");
        Book b2 = new Book(Format.EPUB, "三国杀");
        Book b3 = new Book(Format.MOBI, "海贼王");
        Book b4 = new Book(Format.PDF, "灰姑娘");
        b.add(b1);
        b.add(b2);
        System.out.println(b.size());
        Kindle k = new Kindle(b, ERF);
        k.deleteBook(b1);
        System.out.println(b.size());
//        System.out.println(k);
        k.uploadBook(b3);
//        k.deleteBook(b2);
//        System.out.println(b.size());
        k.downloadBook(b4);
        System.out.println(k.readBook(b.get(0)));
        System.out.println(k.readBook(b.get(1)));
        System.out.println(k.readBook(b.get(2)));
        System.out.println(k.readBook(b2));
//        k.deleteBook(b2);
//        System.out.println(k.readBook(b2));
//        System.out.println(k.readBook(b3));
//        System.out.println(k.readBook(b4));
    }
}
