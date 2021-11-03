package employee_system;

import java.util.ArrayList;
import java.util.List;

public class Manager extends Employee {
    List<Employee> reports;

    public Manager(String name, int id, int level) {
        super(name, id, level);
        reports = new ArrayList<>();
    }

    @Override
    public void printInfo() {
        System.out.println("Manager Name: " + name + ", ID: " + id);
        for (Employee e : reports) {
            System.out.print("  " + name + "'s report: ");
            e.printInfo();
        }
    }

    public void addReport(Employee e) {
        reports.add(e);
        e.setMgr(this);
    }


    public boolean approvePto(int days, Employee e) {
        return true;
    }
}
