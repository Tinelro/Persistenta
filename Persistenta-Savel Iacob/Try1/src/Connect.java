import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {

    static Connection conn;

    static Connection connect() throws SQLException {
        conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/employees?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                "root","root");
        System.out.println("\nConnection established");
        return conn;
    }

    static void close() throws SQLException {
        conn.close();
        System.out.println("Connection closed\n");
    }

}
