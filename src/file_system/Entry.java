package file_system;

public abstract class Entry {
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
        if (parent == null) { // root does not have parents
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
