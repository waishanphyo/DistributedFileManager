package VIEW;

import javax.swing.*;
import MODEL.LoginModel;
import MODEL.RegModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPage extends JFrame {
    public MainPage() {
        setTitle("Main Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 800); // Set frame size to 1000x800
        setLocationRelativeTo(null); // Center the frame
        setUndecorated(false); // Allow default window decorations

        // Container with background color #ADD8E6
        JPanel container = new JPanel();
        container.setLayout(new GridBagLayout()); // Set GridBagLayout for centering
        container.setBackground(Color.decode("#ADD8E6")); // Set background color to #ADD8E6

        // GridBagConstraints to position the header and buttons
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Add spacing between components
        gbc.anchor = GridBagConstraints.CENTER;

        // Header label "Distributed Files Manager"
        JLabel headerLabel = new JLabel("Distributed Files Manager");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Bold, large font
        headerLabel.setForeground(Color.BLACK);
        
        // Set header to be centered and span two columns
        gbc.gridx = 0;
        gbc.gridy = 0; // Set the header position
        gbc.gridwidth = 2; // Make it span across two columns
        container.add(headerLabel, gbc);

        // Set button size
        Dimension buttonSize = new Dimension(150, 50); // Equal button sizes

        // Login button
        JButton loginButton = new JButton("Login");
        loginButton.setPreferredSize(buttonSize); // Set button size
        loginButton.setBackground(new Color(0xf2, 0xf2, 0xf2, 0x9a)); // #f2f2f29a
        loginButton.setForeground(Color.BLACK);
        loginButton.setFocusPainted(false);
        loginButton.setFont(new Font("Arial", Font.BOLD, 16)); // Bold text
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true)); // Rounded border

        // Mouse hover effect for Login button
        loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loginButton.setBackground(Color.GREEN);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                loginButton.setBackground(new Color(0xf2, 0xf2, 0xf2, 0x9a));
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new LoginPage(new LoginModel()).setVisible(true);
            }
        });

        // Register button
        JButton registerButton = new JButton("Register");
        registerButton.setPreferredSize(buttonSize); // Set button size
        registerButton.setBackground(new Color(0xf2, 0xf2, 0xf2, 0x9a)); // #f2f2f29a
        registerButton.setForeground(Color.BLACK);
        registerButton.setFocusPainted(false);
        registerButton.setFont(new Font("Arial", Font.BOLD, 16)); // Bold text
        registerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        registerButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true)); // Rounded border

        // Mouse hover effect for Register button
        registerButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                registerButton.setBackground(Color.GREEN);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                registerButton.setBackground(new Color(0xf2, 0xf2, 0xf2, 0x9a));
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new RegisterForm(new RegModel()).setVisible(true);
            }
        });

        // Add Login button to the container
        gbc.gridx = 0; // Set the position for the Login button
        gbc.gridy = 1; // Position it below the header
        gbc.gridwidth = 1; // Reset to single column width
        container.add(loginButton, gbc);

        // Add Register button next to Login button
        gbc.gridx = 1; // Set the position for the Register button
        gbc.gridy = 1; // Same row as Login button
        container.add(registerButton, gbc);

        // Add container to the frame
        add(container);

        // Set the frame visible
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainPage::new);
    }
}