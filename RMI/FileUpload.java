package RMI;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

	public class FileUpload extends UnicastRemoteObject implements FileUploadService {

	    public FileUpload() throws RemoteException {
	        super();
	    }

	    @Override
	    public void uploadFile(File file) throws RemoteException {
	        try (FileOutputStream fos = new FileOutputStream("server_directory/" + file.getName())) {
	            byte[] fileBytes = java.nio.file.Files.readAllBytes(file.toPath());
	            fos.write(fileBytes);
	            System.out.println("File uploaded: " + file.getName());
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new RemoteException("File upload failed", e);
	        }
	    }
	    public void insertFileIntoDatabase(File file) throws RemoteException {
	        String url = "jdbc:mysql://localhost:3306/";
	        String username = "root";
	        String password = "";
	        
	        String sql = "INSERT INTO uploaded_files (file_name, file_data) VALUES (?, ?)";

	        try (Connection connection = DriverManager.getConnection(url, username, password);
	             PreparedStatement statement = connection.prepareStatement(sql);
	             FileInputStream fis = new FileInputStream(file)) {
	            
	            statement.setString(1, file.getName());
	            statement.setBinaryStream(2, fis, (int) file.length());
	            statement.executeUpdate();

	            System.out.println("File inserted into database: " + file.getName());
	        } catch (Exception e) {
	           
	        }
	    }
	}
	


