
import java.util.*;

public class FileSystem {
    private final Directory root;
    private Directory current;

    public FileSystem() {
        this.root = new Directory("/", null);
        current = root;
    }

    /**
     * resolve a path like /foo/bar
     */
    private List<Entry> resolve(String path) {
        String[] names = path.split("/");
        if (path.charAt(0) == '/') {
            // go from root
            return search(root, names);
        }
        // go from current
        return search(current, names);
    }

    private List<Entry> search(Entry entry, String[] names) {
        List<Entry> res = new ArrayList<>();
        res.add(entry);
        for (String component : names) {
            if (!(entry instanceof Directory)) {
                throw new IllegalArgumentException("Invalid path");
            }
            if (!component.isEmpty()) {
                entry = ((Directory) entry).get(component);
                res.add(entry);
            }
        }
        return res;
    }

    public void mkdir(String path) {
        List<Entry> entries = resolve(path);
        if (entries.get(entries.size() - 1) != null) {
            throw new IllegalArgumentException("Directory already exists: " + path);
        }
        // String[] names = path.split("/");
        String name = path.substring(path.lastIndexOf('/') + 1);
        final Directory parent = (Directory) entries.get(entries.size() - 2);
        Directory newD = new Directory(name,parent);
        parent.addEntry(newD);
    }

    public Directory cd() {
        current = current.getParent();
        return current;
    }

    public Directory cd(String path) {
        List<Entry> entries = resolve(path);
        Entry last = entries.get(entries.size() - 1);
        if (!(last instanceof Directory)) {
            throw new IllegalArgumentException("No such directory");
        }
        current = (Directory) last;
        return current;
    }

    public void createFile(String path) {

    }

    public void delete(String path) {
        assert !path.endsWith("/");
        List<Entry> entries = resolve(path);
        if (entries.get(entries.size() - 1) != null) {
            throw new IllegalArgumentException("File already exists " + path);
        }
        final String fileName = path.substring(path.lastIndexOf("/") + 1);
        final Directory parent = (Directory) entries.get(entries.size() - 2);
        File file = new File(fileName, parent, 0);
        parent.addEntry(file);
    }

    public Entry[] list(String path) {
        List<Entry> entries = resolve(path);
        Entry last = entries.get(entries.size() - 1);
        if (!(last instanceof Directory)) {
            throw new IllegalArgumentException("No such directory");
        }
        return ((Directory) last).getContents();
    }

    public int count() {
        return 0;
    }

    public String currentPath() {
        StringBuilder path = new StringBuilder();
        Deque<Entry> entries = new ArrayDeque<>();

        Entry curr = current;
        while (curr != root) {
            entries.offerFirst(curr);
            curr = curr.getParent();
        }

        while (entries.size() > 0) {
            path.append('/');
            path.append(entries.pollFirst().getName());
        }
        return path.toString();
    }
}

class File extends Entry {
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

abstract class Entry {
    private String name;
    private final long created;
    private long lastUpdated;
    private long lastAccessed;
    private Directory parent; // so that when we create a new entry, we know where (aka parent) to create

    public Entry(String name, Directory parent) {
        this.name = name;
        this.parent = parent;
        created = System.currentTimeMillis();
    }

    public String getFullPath() {
        if (parent == null) {
            return name;
        }
        return parent.getFullPath() + "/" + name;
    }

    public String info() {
        return name + ", " + "created_at: " + created + ".";
    }

    public boolean delete() {
        if (parent == null) { // the root cannot be deleted
            return false;
        }
        return parent.deleteEntry(this);
    }

    public abstract int size();

    public abstract boolean isFile();

    public String getName() {
        return name;
    }

    public long getCreationTime() {
        return created;
    }

    public long getLastUpdatedTime() {
        return lastUpdated;
    }

    public long getLastAccessedTime() {
        return lastAccessed;
    }

    public void rename(String name) {
        this.name = name;
    }

    public Directory getParent() {
        return parent;
    }
}

class Directory extends Entry {
    private Map<String, Entry> contents;

    public Directory(String name, Directory parent) {
        super(name, parent);
        contents = new HashMap<>();
    }

    public Entry[] getContents() {
        Entry[] entries = new Entry[contents.size()];
        int i = 0;
        for (Entry e : contents.values()) {
            entries[i++] = e;
        }
        return entries;
    }

    @Override
    public int size() {
        int total = 0;
        for (Entry entry : contents.values()) {
            total += entry.size();
        }
        return total;
    }

    @Override
    public boolean isFile() {
        return false;
    }

    public Entry get(String name) {
        return contents.get(name);
    }

    public boolean deleteEntry(Entry entry) {
        return contents.remove(entry.getName()) != null;
    }

    public void addEntry(Entry entry) {
        contents.put(entry.getName(), entry);
    }

    public int numberOfFiles() {
        int num = 0;
        for (Entry entry : contents.values()) {
            if (entry instanceof File) {
                num++;
            } else if (entry instanceof Directory) {
                num++; // a dir counts as a file
                Directory dir = (Directory) entry;
                num += dir.numberOfFiles();
            }
        }
        return num;
    }
}
