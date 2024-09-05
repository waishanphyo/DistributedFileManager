package CONTROLLER;



public class Session {
    private static Session instance;
    private String username;
    private int userid;

    // Private constructor to prevent instantiation
    private Session() {}

    // Method to get the singleton instance of the Session
    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    // Method to set session data
    public void setSession(String username, int userid) {
        this.username = username;
        this.userid = userid;
    }

    // Method to clear the session
    public void clearSession() {
        username = null;
        userid = -1;
    }

    // Getters
    public String getUsername() {
        return username;
    }

    public int getUserid() {
        return userid;
    }
}