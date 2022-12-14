import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class XMLHandler extends DefaultHandler {
    private Connection connection;
    private int bufferSize;

    private DateTimeFormatter birthDayFormat = DateTimeFormatter.ofPattern("yyyy.MM.dd");
    private StringBuilder builder;
    private String sqlStart =  "INSERT INTO voter_count(name, birthDate, `count`) VALUES";
    private String sqlEnd = "ON DUPLICATE KEY UPDATE `count` = `count` + 1";

    public XMLHandler(Connection connection, int bufferSize) {
        this.connection = connection;
        this.bufferSize = bufferSize;
        builder = new StringBuilder(sqlStart);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        try {
            if (qName.equals("voter")) {
                LocalDate birthDay = LocalDate.parse(attributes.getValue("birthDay"), birthDayFormat);
                String name = attributes.getValue("name");
                builder.append("('" + name + "', '" + birthDay + "', 1), ");
                String sql = "INSERT INTO voter_count(name, birthDate, `count`) " +
                        "VALUES('" + name + "', '" + birthDay + "', 1) " +
                        "ON DUPLICATE KEY UPDATE `count` = `count` + 1";
                if (builder.length() > bufferSize) {
                    flash();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void flash() throws SQLException {
        builder.deleteCharAt(builder.length() - 2);
        builder.append(sqlEnd);
        connection.createStatement().execute(builder.toString());
        builder = new StringBuilder(sqlStart);
    }
}
