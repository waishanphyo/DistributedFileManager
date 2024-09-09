package Register;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterServiceImpl extends UnicastRemoteObject implements RegisterService {

    protected RegisterServiceImpl() throws RemoteException {
        super();
    }

    private Connection connect() {
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

    @Override
    public boolean registerUser(String name, String password, String email) throws RemoteException {
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
