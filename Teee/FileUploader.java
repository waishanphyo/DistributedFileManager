package Teee;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FileUploader extends Remote {
    void uploadFile(String fileName, byte[] fileData) throws RemoteException;
}
