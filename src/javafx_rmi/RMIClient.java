package javafx_rmi;

import java.rmi.Naming;


class RMIClient {

    private SharedObjectInterface sharedObj;
    private String rmiObjectName;
    private int lastHash;
    private Boolean isConnected = false;

    private ClientGUI gui;

    RMIClient() {

        gui = new ClientGUI(this);

        InitClient();
        System.out.println("Client started on " + rmiObjectName);
    }

    /**
     * Initialization client
     */
    @SuppressWarnings("InfiniteLoopStatement")
    private void InitClient() {
        try {
            while (true) {
                if(checkForUpdates()) {
                    gui.addElementToListModel(getInfoMessage());
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    System.out.println("Timer interrupted");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void connect() {

        String ip = gui.getIP();
        int port = gui.getPort();

        String folder = "/Shared";
        rmiObjectName = "rmi://" + ip + ":" + port + folder;

        System.setProperty("java.rmi.server.hostname", ip);

        try {
            sharedObj = (SharedObjectInterface) Naming.lookup(rmiObjectName);
            System.out.println("KLIENTEN, har nu objektet");
            isConnected = true;
        } catch (java.rmi.ConnectException conEx) {
            System.out.println("CLIENT: Unable to connect to server!");
            isConnected = false;
        } catch (Exception ex) {
            System.out.println("CLIENT: Exception\n" + ex.toString());
            isConnected = false;
        }

        gui.setConnected(isConnected);
    }


    private Boolean checkForUpdates() {
        if(getSharedHash() != lastHash && isConnected) {
            lastHash = getSharedHash();
            return true;
        } else {
            return false;
        }
    }


    void sendMessageButton() {
        if(!isConnected) {
            connect();
        }
        sendInfoMessage(gui.getMessageText());
        gui.setMessageEditField("");
    }

    void ConnectClick() {
        connect();
    }

    private int getSharedHash() {
        if(isConnected) {
            try {
                return sharedObj.getHash();
            } catch (Exception ex) {
                System.out.println("CLIENT: Exception\n" + ex.toString());
                return -2;
            }
        }
        return -1;
    }

    private void sendInfoMessage(String msg) {
        if(isConnected) {
            InfoClass i = new InfoClass();
            i.setMessage(msg);
            try {
                sharedObj.setInfo(i);
            } catch (Exception ex) {
                System.out.println("CLIENT: Exception\n" + ex.toString());
            }
        }
    }

    private String getInfoMessage() {
        if(isConnected) {
            try {
                return sharedObj.getInfo().getMessage();
            } catch (Exception ex) {
                System.out.println("CLIENT: Exception\n" + ex.toString());
                return null;
            }
        }
        return null;
    }

}
