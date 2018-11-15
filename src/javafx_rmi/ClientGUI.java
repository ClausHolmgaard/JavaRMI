package javafx_rmi;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ClientGUI extends JPanel{

    private static RMIClient client;

    private JLabel ipLabel;
    private JLabel portLabel;

    private JTextField ipEditField;
    private JTextField portEditField;
    private JTextField messageEditField;

    private JButton sendMessageButton;
    private JFrame frame;

    public ClientGUI(RMIClient client) {
        ClientGUI.client = client;

        InitGui();
    }


    /**
     * inizalation GUI
     */
    private void InitGui() {
        InitFrameGUI();

        JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout(FlowLayout.CENTER));

        ipLabel = new JLabel();
        ipLabel.setHorizontalAlignment(JLabel.CENTER);
        ipLabel.setText("IP:");

        ipEditField = new JTextField();
        ipEditField.setHorizontalAlignment(JTextField.LEFT);
        ipEditField.setPreferredSize(new Dimension(100, 20));
        ipEditField.setText("localhost");

        portLabel = new JLabel();
        portLabel.setHorizontalAlignment(JLabel.CENTER);
        portLabel.setText("Port:");

        portEditField = new JTextField();
        portEditField.setHorizontalAlignment(JTextField.LEFT);
        portEditField.setPreferredSize(new Dimension(100, 20));
        portEditField.setText("2002");

        panel1.add(ipLabel);
        panel1.add(ipEditField);
        panel1.add(portLabel);
        panel1.add(portEditField);

        JPanel panel2 = new JPanel();
        panel1.setLayout(new FlowLayout());

        messageEditField = new JTextField();
        messageEditField.setHorizontalAlignment(JTextField.LEFT);
        messageEditField.setPreferredSize(new Dimension(200, 20));

        sendMessageButton = new JButton("Send");
        sendMessageButton.addActionListener(e -> client.ButtonClick());
        sendMessageButton.setPreferredSize(new Dimension(70, 20));

        panel2.add(messageEditField);
        panel2.add(sendMessageButton);

        frame.getContentPane().add(panel1, BorderLayout.PAGE_START);
        //frame.getContentPane().add(messageEditField, BorderLayout.SOUTH);
        //frame.getContentPane().add(sendMessageButton, BorderLayout.AFTER_LAST_LINE);
        frame.getContentPane().add(panel2, BorderLayout.PAGE_END);
        frame.setVisible(true);
    }

    /**
     * Init frame in the GUI
     */
    private void InitFrameGUI(){
        //start JFrame
        frame = new JFrame("GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //frame.getContentPane().setSize(300, 300);
        //frame pack means the program GUI can be scaleable
        frame.pack();
        frame.setSize(new Dimension(300, 300));


        //Might be useful if we want full control
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }

    public String getMessageText() {
        return messageEditField.getText();
    }

    public String getIP() {
        return ipEditField.getText();
    }

    public int getPort() {
        try {
            return Integer.valueOf(portEditField.getText());
        } catch (Exception ex) {
            System.out.println("Client: Exception: Conversion error\n" + ex.toString());
        }

        return -1;
    }
}
