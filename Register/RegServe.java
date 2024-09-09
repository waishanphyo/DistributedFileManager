package Register;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class RegServe {
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);
            RegisterService service = new RegisterServiceImpl();
            Naming.rebind("rmi://localhost/RegisterService", service);
            System.out.println("Registration Service started...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}