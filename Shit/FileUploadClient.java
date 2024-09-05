package Shit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Files;
import java.rmi.Naming;
import CONTROLLER.Session;
public class FileUploadClient extends JFrame {
    private JTextField fileNameField;
    private JComboBox<String> accessLevelBox;
    private JButton uploadButton;

    public FileUploadClient() {
        setTitle("Upload File");
        setLayout(new GridLayout(4, 1));

        JLabel label = new JLabel("Select File:");
        fileNameField = new JTextField(20);
        JButton browseButton = new JButton("Browse");
        accessLevelBox = new JComboBox<>(new String[]{"Public", "Private"});
        uploadButton = new JButton("Upload");

        browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    fileNameField.setText(selectedFile.getAbsolutePath());
                }
            }
        });

        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                uploadFile();
            }
        });

        JPanel filePanel = new JPanel();
        filePanel.add(label);
        filePanel.add(fileNameField);
        filePanel.add(browseButton);

        add(filePanel);
        add(new JLabel("Access Level:"));
        add(accessLevelBox);
        add(uploadButton);

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void uploadFile() {
        try {
            String filePath = fileNameField.getText();
            File file = new File(filePath);
            String accessLevel = accessLevelBox.getSelectedItem().toString();
            byte[] fileData = Files.readAllBytes(file.toPath());

            // Connect to the RMI server
            FileService fileService = (FileService) Naming.lookup("rmi://localhost/FileService");

            // Get the user ID from the session
            int userId = Session.getInstance().getUserid(); // Replace with the actual method to get user ID from session

            // Upload the file
            if (fileService.uploadFile(userId, file.getName(), fileData, accessLevel)) {
                JOptionPane.showMessageDialog(this, "File uploaded successfully!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "File upload failed.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred.");
        }
    }

    public static void main(String[] args) {
        new FileUploadClient();
    }
}