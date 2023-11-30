import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class Employee {
    private String name;
    private int id;
    private double salary;

    public Employee(String name, int id, double salary) {
        this.name = name;
        this.id = id;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{id=" + id + ", name='" + name + "', salary=" + salary + "}";
    }
}

class EmployeeManagementSystem {
    private ArrayList<Employee> employees;

    public EmployeeManagementSystem() {
        this.employees = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public Employee getEmployeeById(int id) {
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                return employee;
            }
        }
        return null;
    }

    public ArrayList<Employee> getAllEmployees() {
        return employees;
    }
}

public class EmployeeManagementApp extends JFrame {
    private EmployeeManagementSystem employeeManagementSystem;

    private JTextField nameField, idField, salaryField;
    private JTextArea displayArea;

    public EmployeeManagementApp() {
        employeeManagementSystem = new EmployeeManagementSystem();

        JLabel nameLabel = new JLabel("Name:");
        JLabel idLabel = new JLabel("ID:");
        JLabel salaryLabel = new JLabel("Salary:");

        nameField = new JTextField(20);
        idField = new JTextField(10);
        salaryField = new JTextField(10);

        JButton addButton = new JButton("Add Employee");
        JButton retrieveButton = new JButton("Retrieve Employee");
        JButton displayButton = new JButton("Display All Employees");

        displayArea = new JTextArea(15, 40);
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        // Layout
        JPanel panel = new JPanel();
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(idLabel);
        panel.add(idField);
        panel.add(salaryLabel);
        panel.add(salaryField);
        panel.add(addButton);
        panel.add(retrieveButton);
        panel.add(displayButton);

        add(panel);
        add(scrollPane);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addEmployee();
            }
        });

        retrieveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                retrieveEmployee();
            }
        });

        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayAllEmployees();
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setTitle("Employee Management System");
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setVisible(true);
    }

    private void addEmployee() {
        String name = nameField.getText();
        int id = Integer.parseInt(idField.getText());
        double salary = Double.parseDouble(salaryField.getText());

        Employee newEmployee = new Employee(name, id, salary);
        employeeManagementSystem.addEmployee(newEmployee);

        displayArea.append("Employee added successfully: " + newEmployee + "\n");

        // Clear input fields
        nameField.setText("");
        idField.setText("");
        salaryField.setText("");
    }

    private void retrieveEmployee() {
        int id = Integer.parseInt(idField.getText());
        Employee retrievedEmployee = employeeManagementSystem.getEmployeeById(id);

        if (retrievedEmployee != null) {
            displayArea.append("Employee found: " + retrievedEmployee + "\n");
        } else {
            displayArea.append("Employee not found!\n");
        }

        // Clear input fields
        nameField.setText("");
        idField.setText("");
        salaryField.setText("");
    }

    private void displayAllEmployees() {
        ArrayList<Employee> allEmployees = employeeManagementSystem.getAllEmployees();

        if (!allEmployees.isEmpty()) {
            displayArea.append("All Employees:\n");
            for (Employee employee : allEmployees) {
                displayArea.append(employee.toString() + "\n");
            }
        } else {
            displayArea.append("No employees found!\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new EmployeeManagementApp();
            }
        });
    }
}
