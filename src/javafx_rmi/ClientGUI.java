package javafx_rmi;

import javax.swing.*;
import java.awt.*;

class ClientGUI extends JPanel{

    private static RMIClient client;

    private JLabel ipLabel;
    private JLabel portLabel;

    private JTextField ipEditField;
    private JTextField portEditField;
    private JTextField messageEditField;

    private JButton sendMessageButton;
    private JButton connectButton;

    private JList<String> list;
    private DefaultListModel<String> listModel;

    private JFrame frame;

    /**
     * Constructor
     * GUI with backend function
     * @param client RMIClient
     */
    ClientGUI(RMIClient client) {
        ClientGUI.client = client;

        InitGui();
    }


    /**
     * inizalation GUI
     */
    private void InitGui() {
        InitFrameGUI();

        //variables
        JPanel topPanel = new JPanel();
        JPanel bottomPanel = new JPanel();
        JPanel panel2 = new JPanel();
        listModel = new DefaultListModel<>();
        list = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        ipLabelGUI();

        ipEditFieldGUI();

        portLabelGUI();

        portEditFieldGUI();

        ConnectBtnGUI();

        messageEditFieldGUI();

        sendMessageBtnGUI();

        addedToPanels(topPanel, bottomPanel, panel2);

        listGUI();

        addedToContentPage(topPanel, bottomPanel, panel2, scrollPane);
    }

    /**
     * Add panels to content panel
     * @param topPanel JPanel
     * @param bottomPanel JPanel
     * @param panel2 Jpanel
     * @param scrollPane JScrollPane
     */
    private void addedToContentPage(JPanel topPanel, JPanel bottomPanel, JPanel panel2, JScrollPane scrollPane) {

        //add panels to content panel.
        frame.getContentPane().add(topPanel, BorderLayout.PAGE_START);
        frame.getContentPane().add(bottomPanel, BorderLayout.AFTER_LINE_ENDS);
        frame.getContentPane().add(panel2, BorderLayout.PAGE_END);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        //set default button to send message button, so the user can use enter button.
        frame.getRootPane().setDefaultButton(sendMessageButton);

        //set frame visible
        frame.setVisible(true);
    }

    /**
     * Add labels, editfields and buttons to panels.
     * @param topPanel Jpanel
     * @param bottomPanel Jpanel
     * @param panel2 Jpanel
     */
    private void addedToPanels(JPanel topPanel, JPanel bottomPanel, JPanel panel2) {
        topPanel.add(ipLabel);
        topPanel.add(ipEditField);
        topPanel.add(portLabel);
        topPanel.add(portEditField);
        bottomPanel.add(connectButton);
        panel2.add(messageEditField);
        panel2.add(sendMessageButton);
    }

    /**
     * Chat GUI in list form
     */
    private void listGUI() {
        list.setFixedCellWidth(300);
        list.setDragEnabled(false);
        list.setSelectionModel(new DisabledItemSelectionModel());
        list.setLayoutOrientation(JList.VERTICAL);

        //if we get the last index
        int lastindex = listModel.getSize() - 1;
        if(lastindex >= 0) {
            //follow the last index
            list.ensureIndexIsVisible(lastindex);
        }
    }

    /**
     * send message button GUI
     */
    private void sendMessageBtnGUI() {
        sendMessageButton = new JButton("Send");
        sendMessageButton.addActionListener(e -> client.sendMessageButton());
        sendMessageButton.setPreferredSize(new Dimension(70, 20));
    }

    /**
     * message Edit field GUI
     */
    private void messageEditFieldGUI() {
        messageEditField = new JTextField();
        messageEditField.setHorizontalAlignment(JTextField.LEFT);
        messageEditField.setPreferredSize(new Dimension(200, 20));
    }

    /**
     * Connect button GUI
     */
    private void ConnectBtnGUI() {
        connectButton = new JButton("Connect");
        connectButton.addActionListener(e -> client.ConnectClick());
        connectButton.setPreferredSize(new Dimension(100, 20));
    }

    /**
     * port Edit field GUI
     */
    private void portEditFieldGUI() {
        portEditField = new JTextField();
        portEditField.setHorizontalAlignment(JTextField.LEFT);
        portEditField.setPreferredSize(new Dimension(100, 20));
        portEditField.setText("2002");
    }

    /**
     * port Label GUI
     */
    private void portLabelGUI() {
        portLabel = new JLabel();
        portLabel.setHorizontalAlignment(JLabel.CENTER);
        portLabel.setText("Port:");
    }

    /**
     * ip Edit field GUI
     */
    private void ipEditFieldGUI() {
        ipEditField = new JTextField();
        ipEditField.setHorizontalAlignment(JTextField.LEFT);
        ipEditField.setPreferredSize(new Dimension(100, 20));
        ipEditField.setText("localhost");
    }

    /**
     * ip Label GUI
     */
    private void ipLabelGUI() {
        ipLabel = new JLabel();
        ipLabel.setHorizontalAlignment(JLabel.CENTER);
        ipLabel.setText("IP:");
    }

    /**
     * Disable selection
     */
    class DisabledItemSelectionModel extends DefaultListSelectionModel {
        @Override
        public void setSelectionInterval(int index0, int index1) {
            super.setSelectionInterval(-1, -1);
        }
    }

    /**
     * Init frame in the GUI
     */
    private void InitFrameGUI(){
        //start JFrame
        frame = new JFrame("GUI");

        //option to exit on close the program
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //frame pack means the program GUI can be scaleable
        frame.pack();

        //set program size to 300 width, 300 height
        frame.setSize(new Dimension(300, 300));


        //disable resize function, because we want the chat to have a certain size.
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }

    String getMessageText() {
        return messageEditField.getText();
    }

    void setMessageEditField(String message){
        messageEditField.setText(message);
    }

    String getIP() {
        return ipEditField.getText();
    }

    void addElementToListModel(String s){
        listModel.addElement(s);
    }

    int getPort() {
        try {
            return Integer.valueOf(portEditField.getText());
        } catch (Exception ex) {
            System.out.println("Client: Exception: Conversion error\n" + ex.toString());
        }
        return -1;
    }

    void setConnected(Boolean isCon) {
        connectButton.setEnabled(!isCon);
    }
}
