package EntryRMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginServiceImpl extends UnicastRemoteObject implements LoginService {

    private static final String URL = "jdbc:mysql://localhost:3306/dfm";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    protected LoginServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public int authenticate(String name, String password) throws RemoteException {
        int userId = -1;
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            String sql = "SELECT id FROM users WHERE name = ? AND password = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, password);
            
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                userId = rs.getInt("id");
            }
            
            rs.close();
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userId;
    }
    public static void main(String[] args) {
        try {
            // Create RMI registry on port 1099
            java.rmi.registry.LocateRegistry.createRegistry(1094);
            LoginServiceImpl service = new LoginServiceImpl();
            java.rmi.Naming.rebind("rmi://localhost:1099/LoginService", service);
            System.out.println("Login RMI Service is running on port 1099...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}