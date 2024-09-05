package Teee;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class FileUploadClient {
    public static void main(String[] args) {
        try {
            // Connect to the RMI registry on localhost at port 5000
            Registry registry = LocateRegistry.getRegistry("localhost", 5000);
            FileUploader stub = (FileUploader) registry.lookup("FileUploaderService");

            // Initialize the MVC components
            FileUploadModel model = new FileUploadModel(stub);
            FileUploadView view = new FileUploadView();
            new FileUploadController(model, view);

            view.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}