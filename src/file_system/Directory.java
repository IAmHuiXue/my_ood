package file_system;

import java.util.HashMap;
import java.util.Map;

public class Directory extends Entry {
    // using a map help faster index the actual entry by their name
    private Map<String, Entry> contents;

    public Directory(String name, Directory parent) {
        super(name, parent);
        contents = new HashMap<>();
    }

    public Entry[] getContents() {
        return contents.values().toArray(new Entry[0]);
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

    public Entry getEntry(String name) {
        return contents.get(name);
    }

    public boolean deleteEntry(Entry entry) {
        return contents.remove(entry.getName()) != null;
    }

    public void addEntry(Entry entry) {
        if (entry != null) {
            contents.put(entry.getName(), entry);
        }
    }

    public int numberOfFiles() {
        int num = 0;
        for (Entry entry : contents.values()) {
            if (entry instanceof Directory) {
                num += ((Directory) entry).numberOfFiles(); // count the sub-dir itself
            }
            num++; // either entry is a file or entry is a dir, needs to add itself
        }
        return num;
    }
}
