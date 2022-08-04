import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class Main {

    private static final String STAFF_TXT = "data/staff.txt";

    public static void main(String[] args) {
        List<Employee> staff = Employee.loadStaffFromFile(STAFF_TXT);
        Employee employeeMaxSalary = findEmployeeWithHighestSalary(staff, 2017);
        System.out.println(employeeMaxSalary);
    }

    public static Employee findEmployeeWithHighestSalary(List<Employee> staff, int year) {
        Optional<Employee> optional = staff.stream()
                .filter(e -> getYearFromDate(e.getWorkStart()) == year)
                .max(Comparator.comparing(Employee::getSalary));
        return optional.orElseThrow();
    }

    private static int getYearFromDate(Date date) {
        String dateStr = new SimpleDateFormat("dd.MM.yyyy").format(date);
        String regex = "[.]";
        String[] parsedDate = dateStr.split(regex);
        String yearStr = parsedDate[parsedDate.length - 1];
        int year = Integer.parseInt(yearStr);
        return year;
    }
}