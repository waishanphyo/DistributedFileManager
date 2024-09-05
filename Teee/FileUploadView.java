package Teee;

import javax.swing.*;
import java.awt.event.ActionListener;

public class FileUploadView extends JFrame {
    private JButton uploadButton;
    private JTextField filePathField;
    private JButton browseButton;

    public FileUploadView() {
        setTitle("File Upload");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        uploadButton = new JButton("Upload");
        filePathField = new JTextField(20);
        browseButton = new JButton("Browse");

        panel.add(filePathField);
        panel.add(browseButton);
        panel.add(uploadButton);

        this.add(panel);
    }

    public String getFilePath() {
        return filePathField.getText();
    }

    public void setFilePath(String filePath) {
        filePathField.setText(filePath);
    }
    public void addBrowseButtonListener(ActionListener listener) {
        browseButton.addActionListener(listener);
    }

    public void addUploadButtonListener(ActionListener listener) {
        uploadButton.addActionListener(listener);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}