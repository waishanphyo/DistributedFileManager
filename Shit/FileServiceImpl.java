package Shit;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FileServiceImpl extends UnicastRemoteObject implements FileService {
    private Connection connection;
    private int id;
    protected FileServiceImpl(Connection connection) throws RemoteException {
        this.connection = connection;
    }

    @Override
    public boolean uploadFile(int userId, String filename, byte[] fileData, String accessLevel) throws RemoteException {
        String query = "INSERT INTO uploadedfiles (user_id, filename, filedata, access_level) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.setString(2, filename);
            stmt.setBytes(3, fileData);
            stmt.setString(4, accessLevel);
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public boolean deleteFile(String filename) throws RemoteException {
        try {
            String query = "DELETE FROM uploadedfiles WHERE filename = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, filename);
                int rowsAffected = statement.executeUpdate();
                return rowsAffected > 0; // Return true if deletion was successful
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<FileData> getAllUploadedFiles(int userId) throws RemoteException {
    	id=userId;
        List<FileData> files = new ArrayList<>();
        String query = "SELECT filename, upload_time, access_level FROM uploadedfiles WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                files.add(new FileData(userId, rs.getString("filename"), rs.getTimestamp("upload_time"), rs.getString("access_level")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return files;
    }
    
    public List<FileData> searchFilesByUser(String username) throws RemoteException {
        List<FileData> files = new ArrayList<>();
        String query = "SELECT filename, upload_time, access_level FROM uploadedfiles WHERE access_level = 'Public' AND user_id = (SELECT id FROM users WHERE name = ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                files.add(new FileData(id,rs.getString("filename"), rs.getTimestamp("upload_time"), rs.getString("access_level")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return files;
    }
    @Override
    public byte[] downloadFile(String filename) throws RemoteException {
        String query = "SELECT filedata FROM uploadedFiles WHERE filename = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, filename);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getBytes("filedata");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}