package Register;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RegisterService extends Remote {
    boolean registerUser(String name, String password, String email) throws RemoteException;
}