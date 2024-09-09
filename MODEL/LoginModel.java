package MODEL;


import EntryRMI.LoginService;

import java.rmi.Naming;

public class LoginModel {

    public int authenticate(String name, String password) {
        int userId = -1;
        try {
            // Locate the RMI service
            LoginService service = (LoginService) Naming.lookup("rmi://localhost/LoginService");
            
            // Call the remote authenticate method
            userId = service.authenticate(name, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userId;
    }
}