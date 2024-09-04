package MODEL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginModel {
    private static final String URL = "jdbc:mysql://localhost/dfm";
    private static final String USER = "root"; // Replace with your DB username
    private static final String PASSWORD = ""; // Replace with your DB password

    public boolean authenticate(String name, String password) {
        boolean isValid = false;
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            String sql = "SELECT * FROM users WHERE name = ? AND password = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            System.out.print("A");
            if (rs.next()) {
                isValid = true;
            }

            rs.close();
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isValid;
    }
}