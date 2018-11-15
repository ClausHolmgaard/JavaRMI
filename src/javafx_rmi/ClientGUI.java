package javafx_rmi;

import javax.swing.*;
import java.awt.*;

public class ClientGUI extends JPanel{

    private static RMIClient client;

    public JTextField ipEditField;
    public JTextField portEditField;
    public JTextField messageEditField;

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

        ipEditField = new JTextField();
        ipEditField.setHorizontalAlignment(JTextField.RIGHT);

        portEditField = new JTextField();
        portEditField.setHorizontalAlignment(JTextField.RIGHT);

        sendMessageButton = new JButton("Send");
        sendMessageButton.addActionListener(e -> client.ButtonClick());
        sendMessageButton.setSize(new Dimension(40, 20));


        add(ipEditField, BorderLayout.NORTH);
        add(portEditField, BorderLayout.NORTH);
        
        add(sendMessageButton, BorderLayout.SOUTH);
    }

    /**
     * Init frame in the GUI
     */
    private void InitFrameGUI(){
        //start JFrame
        frame = new JFrame("GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //frame pack means the program GUI can be scaleable
        frame.pack();
        frame.setVisible(true);

        //Might be useful if we want full control
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }
}
