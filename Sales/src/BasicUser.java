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

    public static boolean checkUserNameDuplication(String userName) {

        boolean duplicated = false;

        //Create database connection
        Connection conn = dbConnection.createDatabaseConnection();

        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT name FROM user";
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {
                String dbName = rs.getString("name");

                if (userName.equals(dbName)) {
                    duplicated = true;
                    break;
                }
            }
        }
        catch (java.sql.SQLException e) {
            System.out.println("SQLException in BasicUser class, in checkUserNameDuplication method");
        }
        //Close database connection
        dbConnection.closeDatabaseConnection(conn);
        return  duplicated;
    }

    public static void login(String userName, String password) {


    }

}
