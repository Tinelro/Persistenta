import javax.swing.*;
import java.io.OutputStream;
import java.io.PrintStream;
import java.sql.SQLException;

import static javax.swing.JOptionPane.*;

public class GUI extends JFrame {
    private JComboBox editComboBox;
    private JComboBox readComboBox;
    private JPanel mainPanel;
    private JTextArea jta;

    static String plus;
    static String field;




    public GUI() {

        final PrintStream sysOut = System.out;
        System.setOut(new PrintStream(new OutputStream() {
            public void write(int b) {
                jta.append(String.valueOf((char) b));
                sysOut.write(b);
            }
        }));

        this.setContentPane(mainPanel);
        editComboBox.addActionListener(e -> {
            int editChoice = editComboBox.getSelectedIndex();

            try {
                switch (editChoice) {
                    case 1:
                        String firstName = showInputDialog("First Name:");
                        String age = showInputDialog("Age:");
                        String address = showInputDialog("Address:");
                        String salary = showInputDialog("Salary:");

                        try {
                            if (firstName.isEmpty() || age.isEmpty() || address.isEmpty() || salary.isEmpty()) {
                                System.out.println("You must insert data in all fields.");
                            } else {
                                CRUD.insertEmployee(Connect.connect(), new Employee(firstName, age, address, salary));
                            }
                        } catch(NullPointerException ex){
                            System.out.println("No entry registered.");
                        }

                        break;

                    case 2:

                        int id = Integer.parseInt(showInputDialog("ID:"));

                        firstName = showInputDialog("First Name:");

                        if (!firstName.isEmpty()) {
                            field = "firstname='" + firstName + "'";
                            System.out.println(field);
                            CRUD.updateField(Connect.connect(), id);
                        } else {
                            System.out.println("No change in First Name");
                        }

                        age = String.valueOf(showInputDialog("Age:"));
                        if (!age.isEmpty()) {
                            field = "age=" + age;
                            CRUD.updateField(Connect.connect(), id);
                        } else {
                            System.out.println("No change in Age");
                        }

                        address = String.valueOf(showInputDialog("Address:"));
                        if (address.isEmpty()) {
                            System.out.println("No change in Address");
                        } else {
                            field = "address='" + address + "'";
                            CRUD.updateField(Connect.connect(), id);
                        }



                        salary = String.valueOf(showInputDialog("Salary:"));
                        if(salary.isEmpty()) {
                            System.out.println("No change in Salary");
                        } else {
                            field = "salary=" + salary;
                            CRUD.updateField(Connect.connect(), id);
                        }

                        break;

                    case 3:
                        int id1 = Integer.parseInt(showInputDialog("Choose ID of entry to delete"));
                        final int option = showConfirmDialog(
                                null, "DATA WILL BE LOST !!!", "Are You sure?",
                                DEFAULT_OPTION);

                        if (option == YES_OPTION) {
                            CRUD.deleteEmployee(Connect.connect(), id1);

                        } else {
                            System.out.println("Operation canceled.");
                        }

                        break;
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (NumberFormatException ex) {
                System.out.println("NumberFormatException");
            }
        });

        readComboBox.addActionListener(e -> {
            int readChoice = readComboBox.getSelectedIndex();

            try {
                switch (readChoice) {
                    case 1:
                        CRUD.readEmployee(Connect.connect());
                        break;

                    case 2:
                        System.out.println();
                        String fnSearch = showInputDialog(
                                null, "Type first name to search", "Filter by first name");
                        plus = "firstname LIKE " + "'%" + fnSearch + "%'";
                        System.out.println("\n   Filtered by first name:\n");
                        CRUD.readFilteredEmployees(Connect.connect());
                        break;

                    case 3:
                        int ageSearch = Integer.parseInt((showInputDialog(
                                null, "Type age to search:", "Filter by age")));
                        plus = "age < " + (ageSearch + 5) + " AND age > " + (ageSearch - 5);
                        System.out.println("\n   Filtered by age: " + ageSearch + " years (+5, -5):\n");
                        CRUD.readFilteredEmployees(Connect.connect());
                        break;

                    case 4:
                        String adSearch = (showInputDialog(
                                null, "Type address to search", "Filter by address"));
                        plus = "address LIKE " + "'%" + adSearch + "%'";
                        System.out.println("\n   Filtered by address:\n");
                        CRUD.readFilteredEmployees(Connect.connect());
                        break;

                    case 5:
                        String salSearch = ((showInputDialog(
                                null, "Type salary to search", "Filter by salary")));
                        plus = "salary < " + (Integer.parseInt(salSearch) +500) + " AND salary>" + (Integer.parseInt(salSearch)-500);
                        System.out.println("\n   Filtered by salary: " + salSearch + " ron (+500, -500):\n");
                        CRUD.readFilteredEmployees(Connect.connect());
                        break;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (NumberFormatException ex) {
                System.out.println("NumberFormatException");
            }
        });
    }
}
