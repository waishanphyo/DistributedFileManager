package Teee;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;

import javax.swing.JFileChooser;

public class FileUploadController {
    private FileUploadModel model;
    private FileUploadView view;

    public FileUploadController(FileUploadModel model, FileUploadView view) {
        this.model = model;
        this.view = view;

        view.addBrowseButtonListener(new BrowseButtonListener());
        view.addUploadButtonListener(new UploadButtonListener());
    }

    class BrowseButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                view.setFilePath(selectedFile.getAbsolutePath());
            }
        }
    }

    class UploadButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String filePath = view.getFilePath();
                File file = new File(filePath);
                FileInputStream fis = new FileInputStream(file);
                byte[] fileData = new byte[(int) file.length()];
                fis.read(fileData);
                fis.close();

                model.uploadFile(file.getName(), fileData);
                view.showMessage("File uploaded successfully!");

            } catch (Exception ex) {
                view.showMessage("Error uploading file: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }
}
