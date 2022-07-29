import java.util.*;

public class Company {

    private final double fixMonthSalary;


    private double companyProfit = 10_000_001;

    private List<Employee> listOfEmployees = new ArrayList<>();


    public Company(double fixMonthSalary) {
        this.fixMonthSalary = fixMonthSalary;
    }


    public void hire(Employee employee) {
        listOfEmployees.add(employee);
    }

    public void hireALl(Collection<Employee> employees) {
        listOfEmployees.addAll(employees);
    }

    public void fire(Employee employee) {
        listOfEmployees.remove(employee);
    }

    public List<Employee> getTopSalaryStaff(int count) {
        if (count < 0 || count > listOfEmployees.size()) {
            System.out.println("Error ;(");
            return new ArrayList<>();
        }
        Collections.sort(listOfEmployees, (o1, o2) -> Double.compare(o2.getMonthSalary(), o1.getMonthSalary()));
        ArrayList<Employee> topSalaryStaff = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            topSalaryStaff.add(listOfEmployees.get(i));
        }
        return topSalaryStaff;
    }

    public List<Employee> getLowestSalaryStaff(int count) {
        if (count < 0 || count > listOfEmployees.size()) {
            System.out.println("Error ;(");
            return new ArrayList<>();
        }
        Collections.sort(listOfEmployees);
        ArrayList<Employee> lowestSalaryStaff = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lowestSalaryStaff.add(listOfEmployees.get(i));
        }
        return lowestSalaryStaff;
    }

    public double getFixMonthSalary() {
        return fixMonthSalary;
    }

    public double getInCome() {
        return companyProfit;
    }

    public Company getCopyCompany(double fixMonthSalary) {
        return new Company(fixMonthSalary);
    }

    public List<Employee> getListOfEmployees() {
        return listOfEmployees;
    }
}
