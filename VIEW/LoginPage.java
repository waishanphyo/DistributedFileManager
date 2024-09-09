package VIEW;

import javax.swing.*;
import CONTROLLER.LoginController;
import MODEL.LoginModel;
import java.awt.*;

public class LoginPage extends JFrame {
    private JTextField nameField;
    private JPasswordField passwordField;
    private JLabel nameErrorLabel;
    private JLabel passwordErrorLabel;
    private JButton loginButton;

    public LoginPage(LoginModel model) {
        setTitle("Login Form");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Main panel with background color #ADD8E6
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.decode("#ADD8E6")); // Set background color
        mainPanel.setLayout(new GridBagLayout());

        // Form panel with background color white transparent, centered in the main window
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(new Color(255, 255, 255, 200)); // White transparent
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        formPanel.setOpaque(true);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        // Add image at the top
        JLabel imageLabel = new JLabel(new ImageIcon(new ImageIcon("src/ING/file.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        formPanel.add(imageLabel, gbc);

        // Name Label
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(nameLabel, gbc);

        // Name Text Field
        nameField = new JTextField(25); // Increased width
        nameField.setPreferredSize(new Dimension(150, 30)); // Increased height
        nameField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        gbc.gridx = 1;
        formPanel.add(nameField, gbc);

        // Name Error Label
        nameErrorLabel = new JLabel("");
        nameErrorLabel.setForeground(Color.RED);
        gbc.gridx = 1;
        gbc.gridy = 2;
        formPanel.add(nameErrorLabel, gbc);

        // Password Label
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(passwordLabel, gbc);

        // Password Field
        passwordField = new JPasswordField(25); // Increased width
        passwordField.setPreferredSize(new Dimension(150, 30)); // Increased height
        passwordField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        gbc.gridx = 1;
        formPanel.add(passwordField, gbc);

        // Password Error Label
        passwordErrorLabel = new JLabel("");
        passwordErrorLabel.setForeground(Color.RED);
        gbc.gridx = 1;
        gbc.gridy = 4;
        formPanel.add(passwordErrorLabel, gbc);

        // Login Button
        loginButton = new JButton("Login");
        loginButton.setPreferredSize(new Dimension(100, 30));
        gbc.gridx = -1;
        gbc.gridy = 5;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        formPanel.add(loginButton, gbc);

        // Center the form panel inside the main panel
        mainPanel.add(formPanel, gbc);

        // Add the main panel to the frame
        add(mainPanel, BorderLayout.CENTER);

        new LoginController(this, model);
    }

    public String getName() {
        return nameField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public void setNameError(String message) {
        nameErrorLabel.setText(message);
    }

    public void setPasswordError(String message) {
        passwordErrorLabel.setText(message);
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public static void main(String[] args) {
        LoginModel model = new LoginModel();
        new LoginPage(model).setVisible(true);
    }
}