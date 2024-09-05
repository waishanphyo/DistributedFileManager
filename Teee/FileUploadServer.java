package Teee;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class FileUploadServer {
    public static void main(String[] args) {
        try {
            // Start RMI registry on port 5000
            LocateRegistry.createRegistry(5000);

            // Create and bind the FileUploaderImpl to the RMI registry
            FileUploader stub = new FileUploaderImpl();
            Registry registry = LocateRegistry.getRegistry(5000);
            registry.rebind("FileUploaderService", stub);

            System.out.println("Server is ready.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}