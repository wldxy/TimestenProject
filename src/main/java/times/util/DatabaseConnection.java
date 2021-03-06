package times.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by ocean on 16-12-31.
 */
public class DatabaseConnection {
    public static final String oracle_driver = "oracle.jdbc.driver.OracleDriver";
    public static final String oracle_connection = "jdbc:oracle:thin:@10.60.42.203:1521:orcl";
    private static final String DB_USER = "tthr";
    private static final String DB_PASSWORD = "tthr";

    public static final String tt_driver = "com.timesten.jdbc.TimesTenDriver";
    public static final String tt_connection = "jdbc:timesten:direct";

    public static Connection getOracleConnection() {
        Connection connection = null;

        try {
            Class.forName(oracle_driver);
            connection = DriverManager.getConnection(oracle_connection, DB_USER, DB_PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    public static Connection getTimstenConnection() throws ClassNotFoundException, SQLException {
        Connection connection = null;

        Class.forName(tt_driver);
        connection = DriverManager.getConnection(tt_connection);

        return connection;
    }

}
