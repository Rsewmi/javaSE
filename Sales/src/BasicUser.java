import javax.swing.*;
import java.sql.*;

public class BasicUser {

    private int id;
    private String userName;
    private String fullName;
    private String password;
    private int age;
    private String gender;
    // if the user type is 'seller': receive 's'
    // if the user type is 'customer': receive 'c'
    private Character type;

    public BasicUser(String userName, String password, Character type) {

        this.userName = userName;
        //this.fullName = fullName;
        this.password = password;
        //this.age = age;
        //this.gender = gender;
        this.type = type;

        //Create database connection
        Connection conn = dbConnection.createDatabaseConnection();

        try {
            String sql = "INSERT INTO user (name, password, userType) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            try {
                pstmt.setString(1, userName);
                pstmt.setString(2, password);
                pstmt.setString(3, type.toString());
                pstmt.executeUpdate();
            } finally {
                pstmt.close();
            }
        }
        catch (java.sql.SQLException e1) {
            System.out.println("e1: "+e1);
        }
        //Close database connection
        dbConnection.closeDatabaseConnection(conn);
    }

    public static String checkUserNameDuplication(String userName) {

        String password = null;
        //Create database connection
        Connection conn = dbConnection.createDatabaseConnection();

        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT password FROM user WHERE name = ?");
            stmt.setString(1, userName);
            ResultSet rs = stmt.executeQuery();
            rs.next();          //moving the cursor to the next position
            password = rs.getString(1);
            return password;

        }
        catch (java.sql.SQLException e2) {
            System.out.println("e2: "+e2);
        }
        //Close database connection
        dbConnection.closeDatabaseConnection(conn);
        return password;
    }

    public static boolean login(String userName, String password) {

        boolean allowLogin = false;

        if (checkUserNameDuplication(userName) == null){
            JOptionPane.showMessageDialog(null, "User does not exist");
        }
        else {
            String dbPassword = checkUserNameDuplication(userName);
            if (password.equals(dbPassword)){
                allowLogin = true;
                JOptionPane.showMessageDialog(null, "User logged in");
            }
            else {
                JOptionPane.showMessageDialog(null, "Incorrect password");
            }
        }
        return allowLogin;
    }

    public static void loadWindow(String userName) {

        //Create database connection
        Connection conn = dbConnection.createDatabaseConnection();

        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT userType FROM user WHERE name = ?");
            stmt.setString(1, userName);
            ResultSet rs = stmt.executeQuery();
            rs.next();          //moving the cursor to the next position
            String userType = rs.getString(1);
            System.out.println(userType);

            if (userType.equals("c")) {
            // load customer window
                System.out.println("c");
            }
            else if (userType.equals("s")) {
            // load seller window
                System.out.println("s");
            }
            else {
                System.out.println("Invalid user Type received.");
            }
        }
        catch (java.sql.SQLException e3) {
            System.out.println("e3: "+ e3);
        }
        //Close database connection
        dbConnection.closeDatabaseConnection(conn);

    }

}
