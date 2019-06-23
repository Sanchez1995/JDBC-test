import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MyJDBCTestCreateDatabase {

    public static void main(String[] args) throws Exception {

        Connection myConn = null;
        Statement myStmt = null;

        String dbURL = "jdbc:mysql:// localhost:3306/sakila?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=GMT";
        String user = "student";
        String pass = "student";


        try {
            //1. Get a Connection to the database
            myConn = DriverManager.getConnection(dbURL, user, pass);
            System.out.println("Database connection was successful!");

            //2. Create a statement
            myStmt = myConn.createStatement();

            //3. Drop an existing database
            System.out.println("Dropping an existing database");
            String dropDatabase = "drop database if exists employees";
            myStmt.executeUpdate(dropDatabase);
            System.out.println("Database dropped successfully ...");

            //4. Create a new database
            System.out.println("Creating a new database");
            String createDatabase = "create database if not exists employees";
            myStmt.executeUpdate(createDatabase);
            System.out.println("Database created successfully ...");


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
