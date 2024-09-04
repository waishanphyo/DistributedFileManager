import java.rmi.Naming;

import RMI.FileUpload;
import RMI.FileUploadService;

public class FileUploadServer {

	    public static void main(String[] args) {
	        try {
	            FileUploadService fileUploadService = new FileUpload();
	            java.rmi.registry.LocateRegistry.createRegistry(1099);
	            Naming.rebind("FileUploadService", fileUploadService);
	            System.out.println("FileUploadService is running...");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}

