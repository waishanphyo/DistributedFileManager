package Shit;

import javax.swing.*;
import javax.swing.table.TableCellEditor;

import VIEW.UserDash;
import java.util.List; // This should be at the top of your class
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;

public class TrashEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {
    private JButton button;
    private JTable table;
    private int userId;

    public TrashEditor(JTable table, int userId) {
        this.table = table;
        this.userId = userId;
        button = new JButton("Delete");
        button.setPreferredSize(new Dimension(80, 30)); // Adjust size as needed
        button.addActionListener(this);
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        if (value != null && value.equals("Delete")) {
            return button;
        }
        return new JLabel(); // Return empty label if not delete
    }
   

    @Override
    public Object getCellEditorValue() {
        return null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int row = table.getSelectedRow();
        String filename = (String) table.getValueAt(row, 0); // Get the filename from column 0
        
        // Assuming you have a way to retrieve the owner ID (e.g., from the FileData object or session)
        try {
            FileService fileService = (FileService) Naming.lookup("rmi://localhost/FileService");

            List<FileData> files=fileService.getAllUploadedFiles(userId);

            FileData file = files.get(row);  // Assuming rows correspond to the files
            int ownerId = file.getOwnerId();  // Retrieve owner ID from FileData object

            // Check if the current user is the owner of the file
            if (ownerId == userId) {
                int confirm = JOptionPane.showConfirmDialog(table, "Are you sure you want to delete this file?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    deleteFile(filename);
                }
            } else {
                JOptionPane.showMessageDialog(table, "You are not authorized to delete this file.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(table, "Error retrieving file data.");
        }
    }

    private void deleteFile(String filename) {
        try {
            FileService fileService = (FileService) Naming.lookup("rmi://localhost/FileService");
            if (fileService.deleteFile(filename)) {
                JOptionPane.showMessageDialog(null, "File deleted successfully.");
                ((UserDash) SwingUtilities.getWindowAncestor(button)).displayFiles(); // Refresh file list
            } else {
                JOptionPane.showMessageDialog(null, "File deletion failed.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error deleting file.");
        }
    }
}