package Shit;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class TrashRenderer extends JButton implements TableCellRenderer {
    public TrashRenderer() {
        setOpaque(true);
        setText("Delete"); // Set text for the button
        setPreferredSize(new Dimension(80, 30)); // Adjust size as needed
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (value != null && value.equals("Delete")) {
            return this;
        }
        return new JLabel(); // Return empty label if not delete
    }
}