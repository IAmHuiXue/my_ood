package unix_find;

public abstract class Filter {
    abstract boolean apply(File file);
}
