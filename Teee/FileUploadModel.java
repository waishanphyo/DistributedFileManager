package Teee;

public class FileUploadModel {
    private FileUploader fileUploader;

    public FileUploadModel(FileUploader fileUploader) {
        this.fileUploader = fileUploader;
    }

    public void uploadFile(String fileName, byte[] fileData) throws Exception {
        fileUploader.uploadFile(fileName, fileData);
    }
}
