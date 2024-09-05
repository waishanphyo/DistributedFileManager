package Teee;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class FileUploaderImpl extends UnicastRemoteObject implements FileUploader {

    protected FileUploaderImpl() throws RemoteException {
        super();
    }

    @Override
    public void uploadFile(String fileName, byte[] fileData) throws RemoteException {
        try {
            // Database connection setup (adjust with your database credentials)
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/yourdb", "root", "password");

            // SQL statement to insert file into database
            String sql = "INSERT INTO files (name, data) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, fileName);
            stmt.setBytes(2, fileData);

            stmt.executeUpdate();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RemoteException("Error uploading file", e);
        }
    }
}
