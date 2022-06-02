package unix_find;

public class File extends Entry {
    int size;
    Type type;

    public File(String name, Type type, int size) {
        super(name);
        this.type = type;
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
