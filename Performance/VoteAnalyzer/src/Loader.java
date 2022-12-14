import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.sql.Connection;

public class Loader {
    private static int bufferSize = 5_000_000;
    private static String fileName = "res/data-1572M.xml";

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();

        Connection connection = DBConnection.getConnection();
        parseFile(connection);

        System.out.println(System.currentTimeMillis() - start);
    }

    private static void parseFile(Connection connection) throws Exception {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        XMLHandler handler = new XMLHandler(connection, bufferSize);
        parser.parse(new File(fileName), handler);
        handler.flash();
    }
}