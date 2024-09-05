package MODEL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginModel {
    private static final String URL = "jdbc:mysql://localhost:3306/dfm"; // Adjust the database name and URL
    private static final String USER = "root"; // Database username
    private static final String PASSWORD = ""; // Database password
    
    public int authenticate(String name, String password) {
        int userId = -1;
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            String sql = "SELECT id FROM users WHERE name = ? AND password = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, password);
            
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                userId = rs.getInt("id"); // Fetch the user ID from the database
            }

            rs.close();
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userId; // Return -1 if authentication fails, or the userId if successful
    }
}