package VIEW; // Adjust the package name accordingly

import javax.swing.*;



import Shit.ButtonEditor;
import Shit.ButtonRenderer;
import Shit.FileData;
import Shit.FileServer;
import Shit.FileService;
import Shit.FileUploadClient; // Correctly import the FileUploadClient
import Shit.TrashEditor;
import Shit.TrashRenderer;
import CONTROLLER.Session;
import MODEL.LoginModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.nio.file.Files;
import java.rmi.*;
import java.util.List;
public class UserDash extends JFrame {
    private JPanel containerA, containerB;
    private JLabel nameLabel, imageLabel;
    private JButton myFileButton, uploadFileButton, logoutButton;
    private JTextField searchBar;
    private JPanel dynamicPanel;
    private Dimension buttonSize = new Dimension(200, 40); // Set button size
    private Color activeButtonColor = new Color(144, 238, 144); // Light green

    public UserDash() {
        setTitle("UserDash");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600); // Adjust the size as needed

        // Initialize containers
        containerA = new JPanel();
        containerA.setLayout(new GridBagLayout());
        containerA.setPreferredSize(new Dimension(320, 600)); // 40% of 800 width
        containerA.setBackground(Color.LIGHT_GRAY); // Background color for Container A

        containerB = new JPanel();
        containerB.setLayout(new BorderLayout());
        containerB.setPreferredSize(new Dimension(480, 600)); // 60% of 800 width

        // Add components to Container A
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Image icon (Circle)
        ImageIcon icon = new ImageIcon("src/ING/Plant.jpg"); // Replace with your image path
        Image image = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(image);
        imageLabel = new JLabel(scaledIcon);
        imageLabel.setPreferredSize(new Dimension(100, 100)); // Circle effect
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        containerA.add(imageLabel, gbc);

        // Name label
        gbc.gridy++;
        nameLabel = new JLabel();
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Set the text of the label to the username stored in the session
        String username = Session.getInstance().getUsername();
        nameLabel.setText(username);

        // Add the label to the container
        containerA.add(nameLabel, gbc);

        // Search bar
        gbc.gridy++;
        searchBar = new JTextField("Search...");
        searchBar.setPreferredSize(new Dimension(200, 30)); // Adjust the size
        containerA.add(searchBar, gbc);

        // MyFile button
        gbc.gridy++;
        myFileButton = new JButton("MyFile", new ImageIcon("path/to/fileicon.png")); // Replace with your icon
        myFileButton.setPreferredSize(buttonSize);
        myFileButton.setForeground(activeButtonColor); // Set default active color
        containerA.add(myFileButton, gbc);

        // UploadFile button
        gbc.gridy++;
        uploadFileButton = new JButton("UploadFile", new ImageIcon("path/to/plusicon.png")); // Replace with your icon
        uploadFileButton.setPreferredSize(buttonSize);
        containerA.add(uploadFileButton, gbc);

       

        // Logout button (spanning two columns)
        gbc.gridy++;
        gbc.gridwidth = 2;
        logoutButton = new JButton("Logout");
        logoutButton.setPreferredSize(buttonSize);
        logoutButton.setHorizontalAlignment(SwingConstants.CENTER);
        containerA.add(logoutButton, gbc);
        logoutButton.addActionListener(e -> {
            Session.getInstance().clearSession();
            new LoginPage(new LoginModel()).setVisible(true); // Redirect to login page
        });
        // Add components to Container B
        dynamicPanel = new JPanel();
        containerB.add(dynamicPanel, BorderLayout.CENTER);

        // Add containers to the main frame
        add(containerA, BorderLayout.WEST);
        add(containerB, BorderLayout.CENTER);

        // Set default panel content
        updateDynamicPanel("MyFile");

        // Add action listeners for dynamic content
        myFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	new FileServer();
            	displayFiles();
            }
        });

        uploadFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open FileUploadClient when the UploadFile button is clicked
            	new FileServer();
                new FileUploadClient(); 
            }
        });

     

        searchBar.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    performSearch();
                }
            }
        });

        searchBar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performSearch();
            }
        });

        searchBar.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                searchBar.setForeground(activeButtonColor); // Change color when active
                if (searchBar.getText().equals("Search...")) {
                    searchBar.setText("");
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                searchBar.setForeground(Color.BLACK); // Reset to default when inactive
                if (searchBar.getText().isEmpty()) {
                    searchBar.setText("Search...");
                }
            }
        });
        setActiveButton(myFileButton);

        // Start FileServer and display files in a separate thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    new FileServer();
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            displayFiles();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

       
        setVisible(true);
    }
    
    private void setActiveButton(JButton activeButton) {
        // Reset all buttons to default color
        myFileButton.setForeground(Color.BLACK);
        uploadFileButton.setForeground(Color.BLACK);
        //downloadedFileButton.setForeground(Color.BLACK);

        // Set the active button's color
        activeButton.setForeground(activeButtonColor);
    }
   public void displayFiles() {
    try {
        // Connect to RMI service
        FileService fileService = (FileService) Naming.lookup("rmi://localhost/FileService");

        // Assuming userId is fetched from the session
        int currentUserId = Session.getInstance().getUserid();
        List<FileData> files = fileService.getAllUploadedFiles(currentUserId);

        dynamicPanel.removeAll();
        dynamicPanel.setLayout(new BorderLayout());

        String[] columnNames = {"Filename", "Uploaded Time", "Access Level", "Actions"};
        Object[][] data = new Object[files.size()][4]; // Updated to include Actions column

        for (int i = 0; i < files.size(); i++) {
            FileData file = files.get(i);
            data[i][0] = file.getFilename(); // Filename (button)
            data[i][1] = file.getUploadTime();
            data[i][2] = file.getAccessLevel();
            // Add Trash icon if the current user is the owner
            if (file.isOwner(currentUserId)) {
                data[i][3] = "Delete"; // Set "Delete" as the action for owners
            } else {
                data[i][3] = ""; // No action button for non-owners
            }
        }

        JTable fileTable = new JTable(data, columnNames);

        // Set custom renderer and editor for each column
        fileTable.getColumn("Filename").setCellRenderer(new ButtonRenderer());
        fileTable.getColumn("Filename").setCellEditor(new ButtonEditor(fileTable));
        
        fileTable.getColumn("Actions").setCellRenderer(new TrashRenderer());
        fileTable.getColumn("Actions").setCellEditor(new TrashEditor(fileTable, currentUserId));

        JScrollPane scrollPane = new JScrollPane(fileTable);
        dynamicPanel.add(scrollPane, BorderLayout.CENTER);
        dynamicPanel.revalidate();
        dynamicPanel.repaint();
    } catch (Exception e) {
        e.printStackTrace();
    }

    
    }
//
//    private JButton createDownloadButton(String filename) {
//        JButton button = new JButton(filename);
//        button.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                downloadFile(filename);
//                System.out.print("A");
//            }
//        });
//        return button;
//    }
//    private void downloadFile(String filename) {
//        try {
//            // Connect to RMI service
//            FileService fileService = (FileService) Naming.lookup("rmi://localhost/FileService");
//            System.out.print("B");
//            // Download file data
//            byte[] fileData = fileService.downloadFile(filename);
//
//            if (fileData != null) {
//                // Save to user's desktop by default
//                String userDesktop = System.getProperty("user.home") + "/Desktop";
//                File defaultFile = new File(userDesktop, filename);
//
//                // Write file to default desktop location
//                Files.write(defaultFile.toPath(), fileData);
//                JOptionPane.showMessageDialog(null, "File downloaded successfully to Desktop: " + defaultFile.getAbsolutePath());
//            } else {
//                JOptionPane.showMessageDialog(null, "File not found.");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(null, "Error downloading file.");
//        }
//    }

    private void updateDynamicPanel(String content) {
        dynamicPanel.removeAll();
        dynamicPanel.add(new JLabel(content + " content goes here."));
        dynamicPanel.revalidate();
        dynamicPanel.repaint();

        // Update button colors based on active state
        myFileButton.setForeground(content.equals("MyFile") ? activeButtonColor : Color.BLACK);
        uploadFileButton.setForeground(content.equals("UploadFile") ? activeButtonColor : Color.BLACK);
       // downloadedFileButton.setForeground(content.equals("Downloaded File") ? activeButtonColor : Color.BLACK);
    }

    private void performSearch() {
        String searchQuery = searchBar.getText().trim();
        if (!searchQuery.isEmpty() && !searchQuery.equals("Search...")) {
            try {
                // Connect to RMI service
                FileService fileService = (FileService) Naming.lookup("rmi://localhost/FileService");

                // Retrieve files by username from the search query
                List<FileData> files = fileService.searchFilesByUser(searchQuery);

                dynamicPanel.removeAll();
                dynamicPanel.setLayout(new BorderLayout());

                String[] columnNames = {"Filename", "Uploaded Time", "Access Level"};
                Object[][] data = new Object[files.size()][3];

                for (int i = 0; i < files.size(); i++) {
                    FileData file = files.get(i);
                    data[i][0] = file.getFilename();
                    data[i][1] = file.getUploadTime();
                    data[i][2] = file.getAccessLevel();
                }

                JTable fileTable = new JTable(data, columnNames);
                fileTable.getColumn("Filename").setCellRenderer(new ButtonRenderer());
                fileTable.getColumn("Filename").setCellEditor(new ButtonEditor(fileTable));

                JScrollPane scrollPane = new JScrollPane(fileTable);
                dynamicPanel.add(scrollPane, BorderLayout.CENTER);
                dynamicPanel.revalidate();
                dynamicPanel.repaint();
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error performing search: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please enter a valid search query.");
        }
    }

    public static void main(String[] args) {
        new UserDash();
    }
}