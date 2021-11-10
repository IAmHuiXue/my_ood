package file_system;

public class File extends Entry {
    private String content; // or byte[]
    private int size;

    // when a file is created, we need to specify the creation location, file size, and the name
    public File(String name, Directory parent, int size) {
        super(name, parent);
        this.size = size;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isFile() {
        return true;
    }
}
