import java.sql.*;

public class BasicUser {

    private int id;
    private String userName;
    private String fullName;
    private String password;
    private int age;
    private String gender;
    private boolean type;

    // if the user type is 'seller': receive true
    // if the user type is 'customer': receive false

    //JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/marketplace";


    //Database credentials
    static final String USER = "root";
    static final String PASS = "";


    public BasicUser(String userName, String password, boolean type) {

        this.userName = userName;
        //this.fullName = fullName;
        this.password = password;
        //this.age = age;
        //this.gender = gender;
        this.type = type;

        Connection conn = null;
        Statement stmt = null;

        try {
            //Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //Create a connection
            System.out.println("Connecting to datebase...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //Create the statement object
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "INSERT INTO `user` (`name`, `password`, `userType`) VALUES ('Kamal', '123', '1');";

            //execute the query
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                int age = rs.getInt("age");
                String first = rs.getString("first");
                String last = rs.getString("last");

                System.out.println("ID:" + id);
                System.out.println(", Age:" + age);
                System.out.println(", First:" + first);
                System.out.println(", Last:" + last);
            }

            rs.close();
            stmt.close();
            conn.close();

            //finally block used to close resources

            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void login(String userName, String password) {


    }

}
