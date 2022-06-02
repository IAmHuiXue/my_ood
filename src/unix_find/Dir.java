package unix_find;

import java.util.ArrayList;
import java.util.List;

public class Dir extends Entry {
    List<Entry> contents;

    Dir(String name) {
        super(name);
        contents = new ArrayList<>();
    }

    public List<Entry> getContents() {
        return contents;
    }
}
