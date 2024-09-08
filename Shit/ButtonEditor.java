package Shit;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Files;
import java.rmi.Naming;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

public class ButtonEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {
    private JButton button;
    private String filename;

  

    public ButtonEditor(JTable table) {
		// TODO Auto-generated constructor stub
    	button = new JButton();
        button.setOpaque(true);
        button.addActionListener(this);
	}

	@Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        filename = (value == null) ? "" : value.toString();
        button.setText(filename);
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        return filename;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Perform the download operation
        downloadFile(filename);
    }

    private void downloadFile(String filename) {
        // Add your RMI download logic here
        System.out.println("Downloading file: " + filename);
      
        try {
            // Connect to RMI service
            FileService fileService = (FileService) Naming.lookup("rmi://localhost/FileService");

            // Download file data
            byte[] fileData = fileService.downloadFile(filename);

            if (fileData != null) {
                // Open JFileChooser dialog to allow user to select a save location
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setSelectedFile(new File(filename)); // Pre-set filename

                int result = fileChooser.showSaveDialog(null);

                // Proceed only if user clicked "Save"
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();

                    if (selectedFile != null) {
                        // Save file data to the selected path
                        Files.write(selectedFile.toPath(), fileData);
                        JOptionPane.showMessageDialog(null, "File downloaded successfully to " + selectedFile.getAbsolutePath());
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid file path selected.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "File download canceled.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "File not found on the server.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error downloading file: " + e.getMessage());
        }
        }
    
}
