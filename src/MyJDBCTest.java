import java.sql.*;

public class MyJDBCTest {
    public static void main(String[] args) throws Exception {
        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;


        try {
            //1. Get a Connection to the database
            myConn = DriverManager.getConnection("jdbc:mysql:// localhost:3306/sakila?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC", "student", "student");
            System.out.println("Database connection was successful!");

            //2. Create a statement
            myStmt = myConn.createStatement();

            //3. Execute SQL query
            myRs = myStmt.executeQuery("SELECT*FROM ACTOR order by first_name");

            //4. Process the result set
            while(myRs.next()) {
                System.out.println(myRs.getString("first_name") + " " + myRs.getString("last_name"));
            }

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
}

