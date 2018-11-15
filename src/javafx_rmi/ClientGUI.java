package javafx_rmi;

import javax.swing.*;
import java.awt.*;

public class ClientGUI extends JPanel{

    private static RMIClient client;

    public JTextField ipEditField;
    public JTextField portEditField;
    public JTextField messageEditField;

    public JButton sendMessageButton;
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

        ipEditField = new JTextField();
        ipEditField.setHorizontalAlignment(JTextField.RIGHT);
        ipEditField.setSize(30, 10);
        //ipEditField.setText("Banana");

        portEditField = new JTextField();
        portEditField.setHorizontalAlignment(JTextField.RIGHT);
        portEditField.setSize(30, 10);
        //portEditField.setText("kiwi");

        sendMessageButton = new JButton("Send");
        sendMessageButton.addActionListener(e -> client.ButtonClick());
        sendMessageButton.setSize(new Dimension(40, 20));

        panel1.add(ipEditField);
        panel1.add(portEditField);

        frame.getContentPane().add(panel1, BorderLayout.PAGE_START);
        frame.getContentPane().add(sendMessageButton, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    /**
     * Init frame in the GUI
     */
    private void InitFrameGUI(){
        //start JFrame
        frame = new JFrame("GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        frame.getContentPane().setSize(300, 300);
        //frame pack means the program GUI can be scaleable
        //frame.pack();
        frame.setSize(new Dimension(300, 300));


        //Might be useful if we want full control
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }
}
