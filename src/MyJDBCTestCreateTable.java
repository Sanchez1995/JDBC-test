import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MyJDBCTestCreateTable {
    public static void main(String[] args) throws Exception {

        Connection myConn = null;
        Statement myStmt = null;

        String dbURL = "jdbc:mysql:// localhost:3306/employees?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=GMT";
        String user = "student";
        String pass = "student";

        try {
            //1. Get a Connection to the database
            myConn = DriverManager.getConnection(dbURL, user, pass);
            System.out.println("Database connection was successful!");

            //2. Create a statement
            myStmt = myConn.createStatement();

            //3. Create a table
            System.out.println("Creating a new table");
            String createTable = "create table if not exists employee" +
                    " (" +
                    "first_name varchar(255) not null," +
                    "last_name varchar(255) not null," +
                    "emp_id int not null," +
                    "salary int not null," +
                    "primary key(emp_id)" +
                    ") engine=innodb";
            myStmt.executeUpdate(createTable);
            System.out.println("Table created successfully ...");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (myStmt != null) {
                myStmt.close();
            }
            if (myConn != null) {
                myConn.close();
            }
        }
    }
}
