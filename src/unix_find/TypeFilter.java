package unix_find;

public class TypeFilter extends Filter {
    Type type;

    public TypeFilter(Type type) {
        this.type = type;
    }

    @Override
    boolean apply(File file) {
        return type.equals(file.getType());
    }
}
