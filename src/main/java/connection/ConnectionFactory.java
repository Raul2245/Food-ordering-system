package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ConnectionFactory {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String USER = "root";
    private static final String PASS = "root";

    private static ConnectionFactory singleInstance = new ConnectionFactory();

    public static ConnectionFactory getSingleInstance() {
        return singleInstance;
    }
    public ConnectionFactory() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection makeConnection() {
        Connection connection = null;

        String DB_NAME = "sistem_livrare";
        String CONNECTION_LINK = "jdbc:mysql://localhost:3306/";

        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(CONNECTION_LINK + DB_NAME, USER, PASS);

            Statement sta = connection.createStatement();
            sta.execute("SET FOREIGN_KEY_CHECKS = 0;");
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return connection;
    }
}
