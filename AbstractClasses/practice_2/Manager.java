public class Manager implements Employee{

    private double monthSalary;

    public Manager(double monthSalary) {
        this.monthSalary = monthSalary + generateBonus();
    }

    private double generateBonus() {
        double earnedMoney = ((Math.random() * ((140_000 - 115_000) - 1)) + 115_000);
        double bonus = (int) earnedMoney * 5 / 100;
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
