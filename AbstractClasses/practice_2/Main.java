import java.util.List;

public class Main {

    private static Company company = new Company(120_000);


    public static void main(String[] args) {

        for (int i = 0; i < 180; i++) {
            if (i < 80) {
                company.hire(new Manager(company.getFixMonthSalary()));
            }
            if (i < 10) {
                company.hire(new TopManager(company.getFixMonthSalary(), company.getCopyCompany(company.getFixMonthSalary())));
            }
            company.hire(new Operator(company.getFixMonthSalary()));
        }
        print();
        List<Employee> list = company.getListOfEmployees();
        for (int i = 0; i < list.size() - list.size() * 0.5; i++) {
            company.fire(list.get(i));
        }
        print();
    }

    public static void print() {
        System.out.println("15 самых высокооплачиваемых сотрудников:");
        List<Employee> listTopSalaryStaff = company.getTopSalaryStaff(15);
        for (Employee employee : listTopSalaryStaff) {
            System.out.println(employee);
        }
        System.out.println("30 самых низкооплачиваемых сотрудников:");
        List<Employee> listLowestSalaryStaff = company.getLowestSalaryStaff(30);
        for (Employee employee : listLowestSalaryStaff) {
            System.out.println(employee);
        }
    }
}
