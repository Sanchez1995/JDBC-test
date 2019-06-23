import java.sql.*;

public class MyJDBCTestInsert {
    public static void main(String[] args) throws Exception {

        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        String dbURL = "jdbc:mysql:// localhost:3306/sakila?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=GMT";
        String user = "student";
        String pass = "student";


        try {
            //1. Get a Connection to the database
            myConn = DriverManager.getConnection(dbURL, user, pass);
            System.out.println("Database connection was successful!");

            //2. Create a statement
            myStmt = myConn.createStatement();

            //3. Insert a new employee
            System.out.println("Inserting a new employee record");
            myRs = myStmt.executeQuery("SELECT*FROM CUSTOMER");
            int rowAffected = myStmt.executeUpdate("insert into actor" + "(first_name, last_name)"+ "values " + "('Sanchez', 'Edwards')");

            //4. Verify this row insertion by getting a list of actors
            myRs = myStmt.executeQuery("select*from customer order by first_name");

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
