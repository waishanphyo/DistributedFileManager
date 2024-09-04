import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JOptionPane;

import MODEL.UploadModel;
import VIEW.UploadView;

public class UploadController {
    private UploadView view;
    private UploadModel model;

    public UploadController(UploadView view, UploadModel model) {
        this.view = view;
        this.model = model;

        this.view.getUploadButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String filePath = view.getFilePath();
                File file = new File(filePath);
                if (file.exists() && file.isFile()) {
                    try {
                        model.insertFileIntoDatabase(file);
                        JOptionPane.showMessageDialog(view, "File uploaded and saved to database successfully!");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(view, "Failed to upload file: " + ex.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(view, "Invalid file path!");
                }
            }
        });
    }
}