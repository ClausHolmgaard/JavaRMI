package javafx_rmi;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;


class RMIClient {

    private SharedObjectInterface sharedObj;
    private String rmiObjectName;
    private int port;

    RMIClient(int rmiPort, String objectName) {
        rmiObjectName = objectName;
        port = rmiPort;
        InitGui();
        InitClient();
        System.out.println("Client started on " + rmiObjectName);
    }

    /**
     * Initalization client
     */
    private void InitClient() {
        try {
            sharedObj = (SharedObjectInterface) Naming.lookup(rmiObjectName);
            System.out.println("KLIENTEN, har nu objektet");
        } catch (java.rmi.ConnectException conEx) {
            System.out.println("CLIENT: Unable to connect to server!");
        } catch (Exception ex) {
            System.out.println("CLIENT: Exception\n" + ex.toString());
        }
    }

    /**
     * Initalization GUI
     */
    private void InitGui() {
        String titleOfGUI = "GUI";

        JFrame frame = new JFrame(titleOfGUI);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(300,300);

        JButton button = new JButton("Press");
        button.addActionListener(e -> ButtonClick());

        frame.getContentPane().add(button); // Adds Button to content pane of frame
        frame.setVisible(true);
    }

    /**
     * Send text object to textbox
     */
    private void ButtonClick() {
        try {
            System.out.println("Text in object: " + sharedObj.getString());
        } catch(Exception ex) {
            System.out.println("CLIENT: Exception when reading.\n" + ex.toString());
        }
    }

}
