public class TopManager implements Employee {

    private double monthSalary;

    private Company company;


    public TopManager(double monthSalary, Company company) {
        this.company = company;
        this.monthSalary = monthSalary + getBonus();
    }

    private double getBonus() {
        double bonus = 0;
        if (company.getInCome() > 10_000_000) {
            bonus = 1.5 * company.getFixMonthSalary();
            return bonus;
        }
        return bonus;
    }

    @Override
    public double getMonthSalary() {
        return monthSalary;
    }


    @Override
    public int compareTo(Object o) {
        Employee employee = (Employee) o;
        return Double.compare(monthSalary, employee.getMonthSalary());
    }

    @Override
    public String toString() {
        return monthSalary + " Руб.";
    }
}
