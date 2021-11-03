package employee_system;

public class Employee {
    public static final String COMPANY = "SQUARE";
    public final String name;
    public final int id;
    private int salary;
    private int level;
    private int pto; // paid time off
    private Manager mgr;

    public Employee(String name, int id, int level) {
        this.name = name;
        this.id = id;
        this.level = level;
        pto = 10 * level;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getLevel() {
        return level;
    }

    public Manager getMgr() {
        return mgr;
    }

    public void setMgr(Manager mgr) {
        this.mgr = mgr;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void printInfo() {
        System.out.println("Name: " + name + ", ID: " + id);
    }

    public void takePto(int days) {
        if (days <= pto && mgr.approvePto(days, this)) {
            pto -= days;
        } else {
            System.out.println("Do not day dream it.");
        }
    }
}
