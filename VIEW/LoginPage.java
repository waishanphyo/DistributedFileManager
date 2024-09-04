package VIEW;

import javax.swing.*;
import CONTROLLER.LoginController;
import CONTROLLER.RegisterController;
import MODEL.LoginModel;
import MODEL.RegModel;

import java.awt.*;

public class LoginPage extends JFrame {
    private JTextField nameField;
    private JPasswordField passwordField;
    private JLabel nameErrorLabel;
    private JLabel passwordErrorLabel;
    private JButton loginButton;

    public LoginPage(LoginModel model) {
        setTitle("Login Form");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(new Color(0xf2f2f2));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        formPanel.setOpaque(true);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel imageLabel = new JLabel(new ImageIcon("path/to/your/image.png"));
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        formPanel.add(imageLabel, gbc);

        JLabel nameLabel = new JLabel("Name:");
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(nameLabel, gbc);

        nameField = new JTextField(20);
        nameField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        gbc.gridx = 1;
        formPanel.add(nameField, gbc);

        nameErrorLabel = new JLabel("");
        nameErrorLabel.setForeground(Color.RED);
        gbc.gridx = 1;
        gbc.gridy = 2;
        formPanel.add(nameErrorLabel, gbc);

        JLabel passwordLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(passwordLabel, gbc);

        passwordField = new JPasswordField(20);
        passwordField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        gbc.gridx = 1;
        formPanel.add(passwordField, gbc);

        passwordErrorLabel = new JLabel("");
        passwordErrorLabel.setForeground(Color.RED);
        gbc.gridx = 1;
        gbc.gridy = 4;
        formPanel.add(passwordErrorLabel, gbc);

        loginButton = new JButton("Login");
        loginButton.setPreferredSize(new Dimension(100, 30));
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        formPanel.add(loginButton, gbc);

        add(formPanel, BorderLayout.CENTER);
        new LoginController(this,model);
        
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
//      new RegisterController(new RegisterForm(),new );
        new LoginPage(model);
    }
}