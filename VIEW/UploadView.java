//package VIEW;
//
//
//import javax.swing.*;
//
//import CONTROLLER.UploadController;
//import MODEL.UploadModel;
//
//import java.awt.*;
//import java.awt.event.ActionListener;
//
//public class UploadView extends JFrame {
//    private JButton uploadButton;
//    private JTextField filePathField;
//	public Object getUploadButton;
//
//    public UploadView(UploadModel model) {
//        setTitle("File Upload");
//        setSize(400, 200);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        filePathField = new JTextField(20);
//        uploadButton = new JButton("Upload File");
//
//        JPanel panel = new JPanel(new GridLayout(2, 1));
//        panel.add(filePathField);
//        panel.add(uploadButton);
//
//        getContentPane().add(panel, BorderLayout.CENTER);
//        new UploadController(this,model);
//        
//    }
//
//    public String getFilePath() {
//        return filePathField.getText();
//    }
//public JButton getUploadButton() {
//	return uploadButton;
//}
////    public void addUploadButtonListener(ActionListener listener) {
////        uploadButton.addActionListener(listener);
////    }
//    public static void main(String[] args) {
//    	UploadModel ld=new UploadModel();
//    	new UploadView(ld);
//}
//
//}
//
//
