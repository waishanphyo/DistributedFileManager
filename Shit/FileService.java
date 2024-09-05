//package Shit;
//import java.rmi.Remote;
//import java.rmi.RemoteException;
//
//public interface FileService extends Remote {
//    boolean uploadFile(int userId, String filename, byte[] fileData, String accessLevel) throws RemoteException;
//    
//}
package Shit;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface FileService extends Remote {
    boolean uploadFile(int userId, String filename, byte[] fileData, String accessLevel) throws RemoteException;
    
    // Method to retrieve file data from database
  //  List<FileData> getAllUploadedFiles(int userId) throws RemoteException;

    // Method to download a specific file
    byte[] downloadFile(String filename) throws RemoteException;

	List<FileData> getAllUploadedFiles(int userId) throws RemoteException;
	//List<FileData> searchFilesByUser(String username) throws RemoteException;

	List<FileData> searchFilesByUser(String searchQuery) throws RemoteException;
	
}