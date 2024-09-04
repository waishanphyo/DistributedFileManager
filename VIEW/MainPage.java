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
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);

        // Container with a background image
        JPanel container = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon("src/ING/Nigh.jpg");
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        container.setLayout(new BorderLayout());

        // Navigation bar
        JPanel navBar = new JPanel();
        navBar.setBackground(Color.BLACK);
        navBar.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 10));

        // Login button
        JButton loginButton = new JButton("Login");
        loginButton.setBackground(Color.GREEN);
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setFont(new Font("Arial", Font.BOLD, 12));
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new LoginPage(new LoginModel()).setVisible(true); // Ensure LoginPage class is defined
            }
        });

        // Register button
        JButton registerButton = new JButton("Register");
        registerButton.setBackground(Color.YELLOW);
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false);
        registerButton.setFont(new Font("Arial", Font.BOLD, 12));
        registerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new RegisterForm(new RegModel()).setVisible(true); // Ensure RegisterForm class is defined
            }
        });

        // Add buttons to the navbar
        navBar.add(loginButton);
        navBar.add(registerButton);

        // Add navbar to the container
        container.add(navBar, BorderLayout.NORTH);

        // Add container to the frame
        add(container);

        // Set the frame visible
        setVisible(true);
    }

   public static void main(String[] args) {
       SwingUtilities.invokeLater(MainPage::new);
   }
}