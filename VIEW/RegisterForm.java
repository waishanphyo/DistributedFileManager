package VIEW;

import CONTROLLER.RegisterController;
import MODEL.RegModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterForm extends JFrame {
    private JTextField nameField, emailField;
    private JPasswordField passwordField, confirmPasswordField;
    private JLabel nameError, emailError, passwordError, confirmPasswordError;
    private JButton registerButton;

    public RegisterForm(RegModel model) {
        setTitle("Register Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);

        JPanel container = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon("src/ING/Nigh.jpg");
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        container.setLayout(new GridBagLayout());

        JPanel formPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(242, 242, 242, 154));
                g.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
            }
        };
        formPanel.setLayout(new GridBagLayout());
        formPanel.setPreferredSize(new Dimension(600, 700));
        formPanel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Add the header image at the center, above the name field
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        ImageIcon homeIcon = new ImageIcon("src/ING/head.png");
        Image scaledImage = homeIcon.getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH);
        JLabel headerLabel = new JLabel(new ImageIcon(scaledImage));
        formPanel.add(headerLabel, gbc);

        // Add the name label and field with bold font
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        formPanel.add(nameLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        nameField = new JTextField(15);
        nameField.setPreferredSize(new Dimension(0, 30)); // Height adjustment
        nameField.setFont(new Font("Arial", Font.PLAIN, 14));
        nameField.setBorder(BorderFactory.createCompoundBorder(
                nameField.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5))); // Padding
        nameField.setBackground(Color.WHITE);
        nameField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true)); // Rounded border
        formPanel.add(nameField, gbc);

        nameError = new JLabel("");
        nameError.setForeground(Color.RED);
        nameError.setPreferredSize(new Dimension(200, 15));
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        formPanel.add(nameError, gbc);

        // Add the password label and field
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 14));
        formPanel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        passwordField = new JPasswordField(15);
        passwordField.setPreferredSize(new Dimension(0, 30)); // Height adjustment
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField.setBorder(BorderFactory.createCompoundBorder(
                passwordField.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5))); // Padding
        passwordField.setBackground(Color.WHITE);
        passwordField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true)); // Rounded border
        formPanel.add(passwordField, gbc);

        passwordError = new JLabel("");
        passwordError.setForeground(Color.RED);
        passwordError.setPreferredSize(new Dimension(300, 15));
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        formPanel.add(passwordError, gbc);

        // Add the confirm password label and field
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordLabel.setFont(new Font("Arial", Font.BOLD, 14));
        formPanel.add(confirmPasswordLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        confirmPasswordField = new JPasswordField(15);
        confirmPasswordField.setPreferredSize(new Dimension(0, 30)); // Height adjustment
        confirmPasswordField.setFont(new Font("Arial", Font.PLAIN, 14));
        confirmPasswordField.setBorder(BorderFactory.createCompoundBorder(
                confirmPasswordField.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5))); // Padding
        confirmPasswordField.setBackground(Color.WHITE);
        confirmPasswordField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true)); // Rounded border
        formPanel.add(confirmPasswordField, gbc);

        confirmPasswordError = new JLabel("");
        confirmPasswordError.setForeground(Color.RED);
        confirmPasswordError.setPreferredSize(new Dimension(300, 15));
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        formPanel.add(confirmPasswordError, gbc);

        // Add the email label and field
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Arial", Font.BOLD, 14));
        formPanel.add(emailLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        emailField = new JTextField(15);
        emailField.setPreferredSize(new Dimension(0, 30)); // Height adjustment
        emailField.setFont(new Font("Arial", Font.PLAIN, 14));
        emailField.setBorder(BorderFactory.createCompoundBorder(
                emailField.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5))); // Padding
        emailField.setBackground(Color.WHITE);
        emailField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true)); // Rounded border
        formPanel.add(emailField, gbc);

        emailError = new JLabel("");
        emailError.setForeground(Color.RED);
        emailError.setPreferredSize(new Dimension(200, 15));
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        formPanel.add(emailError, gbc);

        // Center the register button
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        registerButton = new JButton("Register");
        registerButton.setPreferredSize(new Dimension(200, 40));
        registerButton.setFont(new Font("Arial", Font.BOLD, 16));
        registerButton.setBackground(Color.BLACK);
        registerButton.setForeground(Color.WHITE);
        formPanel.add(registerButton, gbc);

        container.add(formPanel);
        add(container);
        setVisible(true);

        // Initialize the controller
        new RegisterController(this,model);
    }

   




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
//      new RegisterController(new RegisterForm(),new );
        new RegisterForm(model);
	
   }
}