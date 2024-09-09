package EntryRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface LoginService extends Remote {
    int authenticate(String name, String password) throws RemoteException;
}
