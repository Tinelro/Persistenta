import javax.swing.*;
import java.io.Serializable;

public class Employee extends JFrame implements Serializable {

     private final String firstName;
     private final String age;
     private final String address;
     private final String salary;
     private int id;

    public Employee(String firstName, String age, String address, String salary) {
        this.firstName = firstName;
        this.age = age;
        this.address = address;
        this.salary = salary;
    }

    public Employee(String firstName, String age, String address, String salary, int id) {
        this.firstName = firstName;
        this.age = age;
        this.address = address;
        this.salary = salary;
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }
    public String getAge() {
        return age;
    }
    public String getAddress() {
        return address;
    }
    public String getSalary() {
        return salary;
    }
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return getId() + "\t" +
                getFirstName() + "\t" +
                getAge() + "\t" +
                getAddress() + "\t" +
                getSalary();
    }
}

