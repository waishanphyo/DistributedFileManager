package VIEW;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserDash extends JFrame {
    private JPanel containerA, containerB;
    private JLabel nameLabel, imageLabel;
    private JButton myFileButton, uploadFileButton, downloadedFileButton, logoutButton;
    private JTextField searchBar;
    private JPanel dynamicPanel;
    private Dimension buttonSize = new Dimension(200, 40); // Set button size
    private Color activeButtonColor = new Color(144, 238, 144); // Light green

    public UserDash () {
        
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
        nameLabel = new JLabel("Name");
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        containerA.add(nameLabel, gbc);

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

        // DownloadedFile button
        gbc.gridy++;
        downloadedFileButton = new JButton("Downloaded File", new ImageIcon("path/to/arrowicon.png")); // Replace with your icon
        downloadedFileButton.setPreferredSize(buttonSize);
        containerA.add(downloadedFileButton, gbc);

        // Logout button (spanning two columns)
        gbc.gridy++;
        gbc.gridwidth = 2;
        logoutButton = new JButton("Logout");
        logoutButton.setPreferredSize(buttonSize);
        logoutButton.setHorizontalAlignment(SwingConstants.CENTER);
        containerA.add(logoutButton, gbc);

        // Add components to Container B
        searchBar = new JTextField("Search...");
        searchBar.setPreferredSize(new Dimension(300, 30));
        containerB.add(searchBar, BorderLayout.NORTH);

        // Dynamic panel for showing content
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
                updateDynamicPanel("MyFile");
            }
        });

        uploadFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateDynamicPanel("UploadFile");
            }
        });

        downloadedFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateDynamicPanel("Downloaded File");
            }
        });

        setVisible(true);
    }

    private void updateDynamicPanel(String content) {
        dynamicPanel.removeAll();
        dynamicPanel.add(new JLabel(content + " content goes here."));
        dynamicPanel.revalidate();
        dynamicPanel.repaint();
        
        // Update button colors based on active state
        myFileButton.setForeground(content.equals("MyFile") ? activeButtonColor : Color.BLACK);
        uploadFileButton.setForeground(content.equals("UploadFile") ? activeButtonColor : Color.BLACK);
        downloadedFileButton.setForeground(content.equals("Downloaded File") ? activeButtonColor : Color.BLACK);
    }

    public static void main(String[] args) {
        new UserDash ();
    }
}