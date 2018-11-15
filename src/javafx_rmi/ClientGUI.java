package javafx_rmi;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientGUI {

    static RMIClient rmiBackend;

    public ClientGUI(RMIClient ref) {
        rmiBackend = ref;

        InitGui();
    }

    private void InitGui() {
        JFrame frame = new JFrame("GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,300);

        JButton button = new JButton("Press");
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                rmiBackend.ButtonClick();
            }
        });

        frame.getContentPane().add(button); // Adds Button to content pane of frame
        frame.setVisible(true);
    }
}
