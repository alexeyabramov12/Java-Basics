public class Operator implements Employee{

    private double monthSalary;

    public Operator(double monthSalary) {
        this.monthSalary = monthSalary;
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
