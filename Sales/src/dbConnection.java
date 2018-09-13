import java.sql.*;

public class dbConnection {

    //JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/marketplace";


    //Database credentials
    static final String USER = "root";
    static final String PASS = "";

    public static Connection createDatabaseConnection() {

        Connection conn = null;

        try {
            //Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //Create a connection
            System.out.println("Connecting to datebase...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //Create the statement object
            System.out.println("Creating statement...");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }

    public static void closeDatabaseConnection(Connection conn) {
        try {
            conn.close();
        }
        catch (java.sql.SQLException r) {
            System.out.println("SQLException in dbConnection class");
        }
    }
}
