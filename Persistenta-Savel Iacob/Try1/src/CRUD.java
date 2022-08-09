import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CRUD {

    static void insertEmployee(Connection conn, Employee employee) throws SQLException, NullPointerException {

        String query = "insert into employees (firstname, age, address, salary) values(?, ?, ?, ?)";

        PreparedStatement st = conn.prepareStatement(query);

        st.setString(1, employee.getFirstName());
        st.setString(2, String.valueOf(employee.getAge()));
        st.setString(3, employee.getAddress());
        st.setString(4, String.valueOf(employee.getSalary()));

        st.execute();
        System.out.println("new employee registered: " + employee.getFirstName());
        Connect.close();

    }

    static void updateField(Connection conn, int id) throws SQLException {

        Statement st = conn.createStatement();
        String query = "update employees set "+ GUI.field + " where id = " + id;
        st.execute(query);
        System.out.println("updated: " + GUI.field);
        Connect.close();
    }

    static void deleteEmployee(Connection conn, int id) throws SQLException, NullPointerException {

        String query = "delete from employees where id=?";
        PreparedStatement st = conn.prepareStatement(query);
        st.setInt(1, id);
        st.execute();
        System.out.println("employee with id = " + id + " deleted");
        Connect.close();
    }

    public static void readEmployee(Connection conn) throws SQLException, NullPointerException {

        List<Employee> employeeList = new ArrayList();

        Statement st = conn.createStatement();

        String query = "select * from employees";

        st.executeQuery(query);

        ResultSet rs = st.getResultSet();

        while (rs.next()) {
            Employee employee = new Employee(rs.getString("firstname"),
                    rs.getString("age"),
                    rs.getString("address"),
                    rs.getString("salary"),
                    rs.getInt("id"));
            employeeList.add(employee);
        }
        System.out.println("\n   All entries:\n");
        for (Employee p : employeeList) {

            System.out.println(p);
        }


        System.out.println("\n");
        Connect.close();
    }


    public static void readFilteredEmployees(Connection conn) throws SQLException, NullPointerException {

        List<Employee> employeeList = new ArrayList();

        Statement st = conn.createStatement();

        String query = "select * from employees where " + GUI.plus;

        st.executeQuery(query);
        ResultSet rs = st.getResultSet();

        while (rs.next()) {
            Employee employee = new Employee(rs.getString("firstname"),
                    rs.getString("age"),
                    rs.getString("address"),
                    rs.getString("salary"),
                    rs.getInt("id"));
            employeeList.add(employee);
        }

        for (Employee p : employeeList) {
            System.out.println(p);


        }
        Connect.close();
    }
}
