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
    Map<String, Entry> contents;

    public Directory(String name, Directory parent) {
        super(name, parent);
        contents = new HashMap<>();
    }

    public List<Entry> getContents() {
        return new ArrayList<>(contents.values());
    }

    public int size() {
        int size = 0;
        for (Entry e : contents.values()) {
            size += e.size();
        }
        return size;
    }

    public Entry getChild(String name) {
        return contents.get(name);
    }

    public int numberOfFiles() {
        int count = 0;
        for (Entry e : contents.values()) {
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
        Entry e = contents.remove(entry.name);
        return e != null;
    }

    public void addEntry(Entry entry) {
        if (entry != null) {
            contents.put(entry.name, entry);
        }
    }
}

public class FileSystem {
    Directory root;

    public FileSystem() {
        root = new Directory("/", null);
    }

    private String[] resolvePath(String path) {
        return path.substring(1).split("/"); // exclude the root level
    }

    public void mkdir(String path) {
        Directory e = root;
        String[] dirs = resolvePath(path);
        for (String dir : dirs) {
            e.contents.putIfAbsent(dir, new Directory(dir, e)); // create one if not found along the way
            e = (Directory) e.contents.get(dir);
        }
    }

    public void createFile(String path) { // a valid path should be: /dir1/dir2/dir3/fileName
        Directory e = root;
        String[] dirs = resolvePath(path);
        for (int i = 0; i < dirs.length - 1; i++) {
            e.contents.putIfAbsent(dirs[i], new Directory(dirs[i], e));
            e = (Directory) e.contents.get(dirs[i]);
        }
        // now e is the last dir, which is going to be the parent of the new file
        e.addEntry(new File(dirs[dirs.length - 1], e, 0));
    }

    public void delete(String path) {
        Directory e = root;
        String[] dirs = resolvePath(path);
        for (String dir : dirs) {
            e = (Directory) e.getChild(dir);
            if (e == null) {
                throw new IllegalArgumentException("Invalid path.");
            }
        }
        e.delete();
    }

    public List<Entry> list(String path) {
        Directory e = root;
        String[] dirs = resolvePath(path);
        if (dirs.length != 0) {
            for (String dir : dirs) {
                e = (Directory) e.getChild(dir);
                if (e == null) {
                    throw new IllegalArgumentException("Invalid path.");
                }
            }
        }
        return e.getContents();
    }

    public int count() {
        return root.numberOfFiles();
    }
}
