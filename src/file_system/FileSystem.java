package file_system;

import java.util.*;

// Main Functionalities:
//        1. files, directories -> a directory contains files/subdirectories
//        2. metadata of files/directories: name, creation time, access time, modification time, etc.
//        3. operation: create, move, delete, update, etc.
//
// Design:
//
//        A file system can be modeled as a tree consisting of files/directories
//        - leaf nodes: files or empty directories
//        - how to count the total number of files/directories?
//
// Type
//        - file: content
//        - directory: can have other files/directories

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
        // the path passed in here should be like:
        //  /a/b/c or /a/b/c/ absolute path
        //  a/b or a/b/       relative path
        // and each component should be a Directory, not a File

        if (path.charAt(0) == '/') { // /a/b
            // go from root
            String[] names = path.substring(1).split("/");
            return resolve(root, names);
        }  // b/a
        // go from current
        String[] names = path.split("/");
        return resolve(current, names);
    }

    private List<Entry> resolve(Entry entry, String[] names) {
        List<Entry> res = new ArrayList<>();
        res.add(entry);
        for (String component : names) {
            if (!(entry instanceof Directory)) {
                throw new IllegalArgumentException("Invalid path");
            }
            if (!component.isEmpty()) { // to take care of paths end with '/'
                entry = ((Directory) entry).getEntry(component);
                res.add(entry);
            }
        }
        return res;
    }

    public void mkdir(String path) {
        List<Entry> entries = resolve(path);
        if (entries.get(entries.size() - 1) != null) {
            throw new IllegalArgumentException("file_system.Directory already exists: " + path);
        }
        // String[] names = path.split("/");
        String name = path.substring(path.lastIndexOf('/') + 1);
        final Directory parent = (Directory) entries.get(entries.size() - 2);
        Directory newD = new Directory(name, parent);
        parent.addEntry(newD);
    }

    public Directory cd() {
        // return to the topper level
        if (current != root) {
            current = current.getParent();
        }
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
        assert !path.endsWith("/");
        List<Entry> entries = resolve(path);
        if (entries.get(entries.size() - 1) != null) {
            throw new IllegalArgumentException("file_system.File already exists " + path);
        }
        final String fileName = path.substring(path.lastIndexOf("/") + 1);
        final Directory parent = (Directory) entries.get(entries.size() - 2);
        parent.addEntry(new File(fileName, parent, 0));
    }

    public void delete(String path) {

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
        return root.numberOfFiles();
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






