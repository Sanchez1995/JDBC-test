import java.sql.*;

public class MyJDBCTestUpdate {

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

            //3. Update an Employee
            System.out.println("Updating an existing employee record");
            //myRs = myStmt.executeQuery("SELECT*FROM ACTOR order by first_name");
            int rowsAffected = myStmt.executeUpdate("update actor " + "set first_name = 'Boris'" + "where actor_id = 208");


            //Verify this row insertion by getting a list of actors
            myRs = myStmt.executeQuery("select*from actor order by actor_id desc");

            //4. Process the result set
            while(myRs.next()) {
                System.out.println(myRs.getString("actor_id") + " " + myRs.getString("first_name") + " " + myRs.getString("last_name"));
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
