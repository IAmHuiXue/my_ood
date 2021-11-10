package file_system.simple_version;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

abstract class Entry {
    String name;
    Directory parent;

    public Entry(String name, Directory parent) {
        this.name = name;
        this.parent = parent;
    }

    public boolean delete() {
        if (parent == null) { // root does not have parents
            return false;
        }
        return parent.deleteEntry(this);
    }

    public abstract int size();

    public String getFullPath() {
        if (parent == null) { // root does not have parents
            return name;
        }
        return parent.getFullPath() + "/" + name;
    }

    public void changeName(String n) {
        name = n;
    }

    public String getName() {
        return name;
    }
}

class File extends Entry {
    int size;
    String content;

    public File(String name, Directory parent, int size) {
        super(name, parent);
        this.size = size;
    }

    public int size() {
        return size;
    }

    public String getContents() {
        return content;
    }

    public void setContents(String c) {
        content = c;
    }
}

class Directory extends Entry {
    // using a map help faster index the actual entry by their name
    Map<String, Entry> entries;

    public Directory(String name, Directory parent) {
        super(name, parent);
        entries = new HashMap<>();
    }

    public List<Entry> getContents() {
        return new ArrayList<>(entries.values());
    }

    public int size() {
        int size = 0;
        for (Entry e : entries.values()) {
            size += e.size();
        }
        return size;
    }

    public Entry getChild(String name) {
        return entries.get(name);
    }

    public int numberOfFiles() {
        int count = 0;
        for (Entry e : entries.values()) {
            if (e instanceof Directory) {
                count += ((Directory) e).numberOfFiles(); // count the sub-dir itself
            }
            count++; // either e is a file or e is a dir, needs to add itself
        }
        return count;
    }

    public boolean deleteEntry(Entry entry) {
        if (entry == null) {
            return false;
        }
        Entry e = entries.remove(entry.name);
        return e != null;
    }

    public void addEntry(Entry entry) {
        if (entry != null) {
            entries.put(entry.name, entry);
        }
    }
}

public class FileSystem {
    Directory root;

    public FileSystem() {
        root = new Directory("/", null);
    }

    public void mkdir(String path) {
        Directory e = root;
        String[] dirs = path.split("/");
        for (int i = 1; i < dirs.length; i++) {
            e.entries.putIfAbsent(dirs[i], new Directory(dirs[i], e));
            e = (Directory) e.entries.get(dirs[i]);
        }
    }

    public void createFile(String path) {
        Directory e = root;
        String[] dirs = path.split("/");
        for (int i = 1; i < dirs.length - 1; i++) {
            e.entries.putIfAbsent(dirs[i], new Directory(dirs[i], e));
            e = (Directory) e.entries.get(dirs[i]);
        }
        e.addEntry(new File(dirs[dirs.length - 1], e, 0));
    }

    public void delete(String path) {
        Directory e = root;
        String[] dirs = path.split("/");
        for (int i = 1; i < dirs.length; i++) {
            e.entries.putIfAbsent(dirs[i], new Directory(dirs[i], e));
            e = (Directory) e.entries.get(dirs[i]);
        }
        e.delete();
    }

    public List<Entry> list(String path) {
        Directory e = root;
        String[] dirs = path.split("/");
        if (!path.equals("/")) {
            for (int i = 1; i < dirs.length; i++) {
                e = (Directory) e.getChild(dirs[i]);
            }
        }
        return e.getContents();
    }

    public int count() {
        return root.numberOfFiles();
    }
}
