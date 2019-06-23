import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;

public class Employees {

    private String firstName;
    private String lastName;
    private int empId;
    private int salary;

    private Connection myConn = null;
    private PreparedStatement myStmt = null;
    private ResultSet myRs = null;

    private String dbURL = "jdbc:mysql:// localhost:3306/employees?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=GMT";
    private String user = "student";
    private String pass = "student";

    public void readData() throws SQLException {
        try {
            Scanner input = new Scanner(new File("resources\\TestData.txt"));
            while (input.hasNextLine()) {
                String line = input.nextLine();
                System.out.println(line);
                try (Scanner data = new Scanner(line)) {
                    if (data.hasNext()) {
                        firstName = data.next();
                    }
                    if (data.hasNext()) {
                        lastName = data.next();
                    }
                    if (data.hasNextInt()) {
                        empId = data.nextInt();
                    }
                    if (data.hasNextInt()) {
                        salary = data.nextInt();
                    }
                }
                System.out.println(firstName + " " + lastName + " " + empId + " " + salary);
                insertData(firstName, lastName, empId, salary);
            }
        } catch (FileNotFoundException | SQLException e) {
            e.printStackTrace();
        /*} catch (SQLException e) {
            e.printStackTrace();
        }*/
        }
    }

        public void connect () throws SQLException {

            //1. Get a Connection to the database
            myConn = DriverManager.getConnection(dbURL, user, pass);
            System.out.println("Database connection was successful!");


        }

        public void insertData (String firstName, String lastName,int empId, int salary) throws SQLException {

            try {
                //1. Connect
                connect();
                //2. Create a statement
                myStmt = myConn.prepareStatement("insert into employee" + "(first_name, last_name, emp_id, salary)" + "values " + "(?, ?, ?, ?)");

                //3. Set the parameters
                for (int i = 1; i <= 4; i++) {
                    if (i == 1) {
                        myStmt.setString(i, firstName);
                    }
                    if (i == 2) {
                        myStmt.setString(i, lastName);
                    }
                    if (i == 3) {
                        myStmt.setInt(i, empId);
                    }
                    if (i == 4) {
                        myStmt.setInt(i, salary);
                    }
                }
            /*myStmt.setString(1, firstName);
            myStmt.setString(2, lastName);
            myStmt.setInt(3, empId);
            myStmt.setInt(4, salary);*/

            /*//4. Insert new employees from file
            System.out.println("Inserting a new employee record");
            myRs = myStmt.executeQuery("select*from employee");
            int rowAffected = myStmt.executeUpdate("insert into employee" + "(first_name, last_name, emp_id, salary)" + "values " + );
*/

                //5. Execute SQL query
                int rowAffected = myStmt.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (myRs != null) {
                    myRs.close();
                }
                if (myStmt != null) {
                    myStmt.close();
                }
                if (myConn != null) {
                    myConn.close();
                }
            }


        }

    /*private static void display(ResultSet myRs) throws Exception {
        while (myRs.next()) {

            String firstName = myRs.getString("first_name");
            String lastName = myRs.getString("last_name");
            int empId = myRs.getInt("emp_id");
            int salary = myRs.getInt("salary");

            System.out.printf("%d, %d, %s, %s, %d, %d\n", firstName, lastName, empId, salary);
        }
    }*/
    }

    class EmployeesDemo {
        public static void main(String[] args) {
            Employees emp = new Employees();
            try {
                emp.readData();

            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
