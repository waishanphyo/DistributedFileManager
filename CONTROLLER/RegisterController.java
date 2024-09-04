package CONTROLLER;

import VIEW.LoginPage;
import VIEW.RegisterForm;
import MODEL.LoginModel;
import MODEL.RegModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterController {
    private RegisterForm registerForm;
    private RegModel model;

    public RegisterController(RegisterForm registerForm,RegModel model) {
        this.registerForm = registerForm;
        this.model = model;
        this.registerForm.getRegisterButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleRegisterButton();
            }
        });
    }

    private void handleRegisterButton() {
        String name = registerForm.getNameField().getText();
        String password = new String(registerForm.getPasswordField().getPassword());
        String confirmPassword = new String(registerForm.getConfirmPasswordField().getPassword());
        String email = registerForm.getEmailField().getText();

        boolean isValid = true;

        // Validate Name
        if (!validateName(name)) {
            isValid = false;
            registerForm.setNameError("Name must be at least 4 characters");
        } else {
            registerForm.setNameError("");
        }

        // Validate Password
        if (!validatePassword(password)) {
            isValid = false;
            registerForm.setPasswordError("Password must be at least 5 characters");
        } else {
            registerForm.setPasswordError("");
        }

        // Validate Confirm Password
        if (!validateConfirmPassword(password, confirmPassword)) {
            isValid = false;
            registerForm.setConfirmPasswordError("Passwords do not match");
        } else {
            registerForm.setConfirmPasswordError("");
        }

        // Validate Email
        if (!validateEmail(email)) {
            isValid = false;
            registerForm.setEmailError("Invalid email address");
        } else {
            registerForm.setEmailError("");
        }

        if (isValid) {
            // Proceed with registration
            System.out.println("Registration successful");
            model.registerUser(name, password, email);
            registerForm.showSuccessMessageBox();
            navigateToLoginPage();
        }

        // Refresh the form
        registerForm.refresh();
    }

    private boolean validateName(String name) {
        return name.length() >= 4;
    }

    private boolean validatePassword(String password) {
        return password.length() >= 5;
    }

    private boolean validateConfirmPassword(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }

    private boolean validateEmail(String email) {
        return email.contains("@") && email.contains(".");
    }

    private void navigateToLoginPage() {
        registerForm.dispose(); // Close the registration form
        new LoginPage(new LoginModel()).setVisible(true); // Open the login page
    }
}