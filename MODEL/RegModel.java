package MODEL;

import Register.RegisterService;

import java.rmi.Naming;

public class RegModel {
    public boolean registerUser(String name, String password, String email) {
        try {
            RegisterService service = (RegisterService) Naming.lookup("rmi://localhost/RegisterService");
            return service.registerUser(name, password, email);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}