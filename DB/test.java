package DB;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class test {

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/dfm";
    private static final String USER = "root"; // Replace with your MySQL username
    private static final String PASSWORD = ""; // Replace with your MySQL password

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void registerUser(String name, String password, String email) {
        String query = "INSERT INTO usres (name, password, email) VALUES (?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, name);
            pstmt.setString(2, password); // You might want to hash passwords before storing them
            pstmt.setString(3, email);

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Registration successful!");
            } else {
                System.out.println("Registration failed.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
    test k=new test();
    k.registerUser("lol","1234","lol@gmail.com");
    		
    }
}