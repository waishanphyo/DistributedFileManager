package CONTROLLER;

import javax.swing.*;
import MODEL.LoginModel;
import VIEW.LoginPage;
import VIEW.UserDash;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {
    private LoginPage view;
    private LoginModel model;

    public LoginController(LoginPage view, LoginModel model) {
        this.view = view;
        this.model = model;
        
        // Attach the action listener to the login button
        this.view.getLoginButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                authenticate();
            }
        });
    }

    private void authenticate() {
        String name = view.getName();
        String password = view.getPassword();
        boolean isValid = model.authenticate(name, password);

        if (isValid) {
            JOptionPane.showMessageDialog(view, "Login successful");
            view.dispose();
            new UserDash().setVisible(true);
            // Navigate to user dashboard or next screen
        } else {
            view.setNameError("User name or password is incorrect");
            view.setPasswordError("User name or password is incorrect");
        }
    }
}