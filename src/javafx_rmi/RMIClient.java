package javafx_rmi;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;


public class RMIClient {

    SharedObjectInterface sharedObj;
    String rmiObjectName;
    int port;

    public  RMIClient(int rmiPort, String objectName) {
        rmiObjectName = objectName;
        port = rmiPort;
        InitGui();
        InitClient();
        System.out.println("Client started on " + rmiObjectName);
    }

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

    private void InitGui() {
        JFrame frame = new JFrame("GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,300);

        JButton button = new JButton("Press");
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ButtonClick();
            }
        });

        frame.getContentPane().add(button); // Adds Button to content pane of frame
        frame.setVisible(true);
    }

    private void ButtonClick() {
        try {
            System.out.println("Text in object: " + sharedObj.getString());
        } catch(Exception ex) {
            System.out.println("CLIENT: Exception when reading.\n" + ex.toString());
        }
    }

}
