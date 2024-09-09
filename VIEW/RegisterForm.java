package VIEW;

import CONTROLLER.RegisterController;
import MODEL.RegModel;

import javax.swing.*;
import java.awt.*;

public class RegisterForm extends JFrame {
    private JTextField nameField, emailField;
    private JPasswordField passwordField, confirmPasswordField;
    private JLabel nameError, emailError, passwordError, confirmPasswordError;
    private JButton registerButton;

    public RegisterForm(RegModel model) {
        setTitle("Register Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 800); // Set size to 1000x800
        setLocationRelativeTo(null); // Center the window on the screen

        // Main panel with background color #ADD8E6
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.decode("#ADD8E6"));
        mainPanel.setLayout(new GridBagLayout());

        // Form panel with white transparent background, centered in the main window
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
        nameError = new JLabel("");
        nameError.setForeground(Color.RED);
        gbc.gridx = 1;
        gbc.gridy = 2;
        formPanel.add(nameError, gbc);

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
        passwordError = new JLabel("");
        passwordError.setForeground(Color.RED);
        gbc.gridx = 1;
        gbc.gridy = 4;
        formPanel.add(passwordError, gbc);

        // Confirm Password Label
        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 5;
        formPanel.add(confirmPasswordLabel, gbc);

        // Confirm Password Field
        confirmPasswordField = new JPasswordField(25); // Increased width
        confirmPasswordField.setPreferredSize(new Dimension(150, 30)); // Increased height
        confirmPasswordField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        gbc.gridx = 1;
        formPanel.add(confirmPasswordField, gbc);

        // Confirm Password Error Label
        confirmPasswordError = new JLabel("");
        confirmPasswordError.setForeground(Color.RED);
        gbc.gridx = 1;
        gbc.gridy = 6;
        formPanel.add(confirmPasswordError, gbc);

        // Email Label
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 7;
        formPanel.add(emailLabel, gbc);

        // Email Field
        emailField = new JTextField(25); // Increased width
        emailField.setPreferredSize(new Dimension(150, 30)); // Increased height
        emailField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        gbc.gridx = 1;
        formPanel.add(emailField, gbc);

        // Email Error Label
        emailError = new JLabel("");
        emailError.setForeground(Color.RED);
        gbc.gridx = 1;
        gbc.gridy = 8;
        formPanel.add(emailError, gbc);

        // Register Button
        registerButton = new JButton("Register");
        registerButton.setPreferredSize(new Dimension(100, 30));
        gbc.gridx = -1;
        gbc.gridy = 30;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        formPanel.add(registerButton, gbc);

        // Center the form panel inside the main panel
        mainPanel.add(formPanel, gbc);

        // Add the main panel to the frame
        add(mainPanel, BorderLayout.CENTER);

        setVisible(true);

        // Initialize the controller
        new RegisterController(this, model);
    }

    // Getter methods for form components
    public JTextField getNameField() {
        return nameField;
    }

    public JTextField getEmailField() {
        return emailField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JPasswordField getConfirmPasswordField() {
        return confirmPasswordField;
    }

    public JLabel getNameError() {
        return nameError;
    }

    public JLabel getEmailError() {
        return emailError;
    }

    public JLabel getPasswordError() {
        return passwordError;
    }

    public JLabel getConfirmPasswordError() {
        return confirmPasswordError;
    }

    public JButton getRegisterButton() {
        return registerButton;
    }

    public void setNameError(String message) {
        nameError.setText(message);
    }

    public void setEmailError(String message) {
        emailError.setText(message);
    }

    public void setPasswordError(String message) {
        passwordError.setText(message);
    }

    public void setConfirmPasswordError(String message) {
        confirmPasswordError.setText(message);
    }

    public void showSuccessMessageBox() {
        JOptionPane.showMessageDialog(this, "Registration successful");
    }

    public void refresh() {
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        // Pass a dummy model or initialize it properly
        RegModel model = new RegModel();
        new RegisterForm(model);
    }
}