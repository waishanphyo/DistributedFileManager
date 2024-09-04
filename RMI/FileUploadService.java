package RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.io.File;


public interface FileUploadService extends Remote {
    void uploadFile(File file) throws RemoteException;
    void insertFileIntoDatabase(File file) throws RemoteException;  
}

