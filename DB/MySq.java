package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySq{
	    public static void main(String[] args) {
	        // Database credentials
	        String url = "jdbc:mysql://127.0.0.1:3306/testb";
	        String user = "root";
	        String password = "";

	        // Load and register the JDBC driver
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	            System.out.println("MySQL JDBC driver not found.");
	            return;
	        }

	        // Establish a connection
	        try (Connection connection = DriverManager.getConnection(url, user, password)) {
	            if (connection != null) {
	                System.out.println("Connected to the database!");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            System.out.println("Connection failed.");
	        }
	    }
	}



