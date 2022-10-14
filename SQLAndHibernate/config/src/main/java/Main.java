import java.sql.*;

public class Main {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/skillbox";
        String user = "root";
        String pass = "369852147Alexey";

        try {
            Connection connection = DriverManager.getConnection(url, user, pass);
            Statement statement = connection.createStatement();
            String selectQuery = "SELECT \n" +
                    "course_name,\n" +
                    "round(count(*) / (max(month(subscription_date)) - min(month(subscription_date)) + 1), 2)average_sale\n" +
                    "FROM PurchaseList \n" +
                    "where subscription_date between '2018-01-01' and '2018-12-31'\n" +
                    "group by course_name\n";
            ResultSet resultSet = statement.executeQuery(selectQuery);
            StringBuilder builder = new StringBuilder();
            while (resultSet.next()) {
               String courseName =  resultSet.getString("course_name");
               double prise = resultSet.getDouble("average_sale");
               builder.append(courseName + " - " + prise + "\n");
            }
            System.out.println(builder);
            connection.close();
            statement.close();
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
