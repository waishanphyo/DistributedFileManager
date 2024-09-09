package Shit;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FileServer {
    private Registry registry;
    private Connection connection;
   // private Registry rg;
    public FileServer() {
        try {
            // Database connection setup
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dfm", "root", "");

            // Create and export RMI service
            FileService fileService = new FileServiceImpl(connection);
            registry = LocateRegistry.createRegistry(1077);
            //rg=LocateRegistry.createRegistry(1022);
            Naming.rebind("rmi://localhost/FileService", fileService);
            
            System.out.println("File Service is running...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        try {
            // Unbind the RMI service
            if (registry != null) {
                Naming.unbind("rmi://localhost/FileService");
                System.out.println("File Service unbound from RMI registry.");
            }

            // Close the RMI registry
            if (registry != null) {
                LocateRegistry.getRegistry().unbind("FileService");
                System.out.println("RMI registry closed.");
            }

            // Close the database connection
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        FileServer fileServer = new FileServer();

        // Add shutdown hook to close the server gracefully
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            fileServer.stop();
        }));
    }
}