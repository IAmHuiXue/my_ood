package library;

public class User {
    private static int COUNT;
    private final String name;
    private int age;
    private int id;

    public User(int age, String name) {
        this.age = age;
        this.name = name;
        this.id = COUNT++;
    }

    public int getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
}
