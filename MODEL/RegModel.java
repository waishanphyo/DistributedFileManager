package MODEL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegModel {
	  private Connection connect() {
	        // Connect to your database
	        String url = "jdbc:mysql://127.0.0.1:3306/dfm";
	        String user = "root";
	        String password = "";
	        Connection conn = null;

	        try {
	            conn = DriverManager.getConnection(url, user, password);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return conn;
	  }
	        public boolean registerUser(String name, String password, String email) {
	            String sql = "INSERT INTO users (name, password, email) VALUES (?, ?, ?)";

	            try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
	                pstmt.setString(1, name);
	                pstmt.setString(2, password);
	                pstmt.setString(3, email);
	                pstmt.executeUpdate();
	                return true;
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	            return false;
	        }

}
