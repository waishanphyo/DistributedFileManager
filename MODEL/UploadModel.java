package MODEL;


	import java.rmi.Naming;
	import java.rmi.RemoteException;

import RMI.FileUploadService;

import java.io.File;

public class UploadModel {
    private FileUploadService fileUploadService;

    public UploadModel() {
        try {
            fileUploadService = (FileUploadService) Naming.lookup("rmi://localhost/FileUploadService");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertFileIntoDatabase(File file) throws RemoteException {
        if (fileUploadService != null) {
            fileUploadService.insertFileIntoDatabase(file);
        }
    }

	
}

