package javafx_rmi;

import java.rmi.Naming;


public class RMIClient {

    private SharedObjectInterface sharedObj;
    private String ip = "localhost";
    private int port = 2002;
    private String folder = "/Shared";
    private String rmiObjectName = "rmi://" + ip + ":" + port + folder;
    private int lastHash;
    private Boolean isConnected = false;

    public RMIClient() {

        ClientGUI g = new ClientGUI(this);

        InitClient();
        System.out.println("Client started on " + rmiObjectName);
    }

    /**
     * Initalization client
     */
    private void InitClient() {

        if(!isConnected) {
            connect();

            if(!isConnected) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    System.out.println("Timer interrupted");
                }
            }
        }

        while (true) {
            if(checkForUpdates()) {
                System.out.println(getInfoMessage());
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                System.out.println("Timer interrupted");
            }
        }
    }

    private void connect() {
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
    }


    private Boolean checkForUpdates() {
        if(getSharedHash() != lastHash && isConnected) {
            lastHash = getSharedHash();
            return true;
        } else {
            return false;
        }
    }


    public void ButtonClick() {
        if(!isConnected) {
            connect();
        }
        sendInfoMessage("Test");
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
                return "";
            }
        }

        return "";
    }

}
