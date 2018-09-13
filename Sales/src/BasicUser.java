import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

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
        catch (java.sql.SQLException ee) {
            System.out.println("SQLException in BasicUser class, in BasicUser constructor method");
        }
        //Close database connection
        dbConnection.closeDatabaseConnection(conn);
    }

    public static String checkUserNameDuplication(String userName) {

//        boolean duplicated = false;
        String password = null;
        //Create database connection
        Connection conn = dbConnection.createDatabaseConnection();

        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT name FROM user";
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {
                String dbName = rs.getString("name");

                if (userName.equals(dbName)) {
//                    duplicated = true;
                     try {
                        String sql1 = "SELECT * FROM user WHERE name = ?";
                        PreparedStatement pstmt = conn.prepareStatement(sql);
                        try {
                            pstmt.setString(1, userName);
                            ResultSet rs1 = pstmt.executeQuery();
                            System.out.println("rsl="+ rs1);
                            password = rs1.getString("password");
                            System.out.println("password="+ password);

                        } finally {
                            pstmt.close();
                        }
                    }
                    catch (java.sql.SQLException ee) {
                        System.out.println("SQLException in BasicUser class, in BasicUser constructor method");
                    }
                    break;
                }
            }
        }
        catch (java.sql.SQLException e) {
            System.out.println("SQLException in BasicUser class, in checkUserNameDuplication method");
        }
        //Close database connection
        dbConnection.closeDatabaseConnection(conn);
//        return  duplicated;
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
            //Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM user WHERE userName = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery(sql);
            String userType = rs.getString("userType");

            if (userType.equals('c')) {
            // load customer window
            }
            else if (userType.equals('s')) {
            // load seller window
            }
            else {
                System.out.println("Invalid user Type received.");
            }
        }
        catch (java.sql.SQLException e) {
            System.out.println("SQLException in BasicUser class, in laodWindow(username) method");
            System.out.println(e);
        }
        //Close database connection
        dbConnection.closeDatabaseConnection(conn);

    }

}
