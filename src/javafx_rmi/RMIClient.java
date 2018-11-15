package javafx_rmi;

import java.rmi.Naming;


public class RMIClient {

    SharedObjectInterface sharedObj;
    String rmiObjectName;
    int port;
    int lastHash;

    public  RMIClient(int rmiPort, String objectName) {
        rmiObjectName = objectName;
        port = rmiPort;

        ClientGUI g = new ClientGUI(this);

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

        while (true) {
            if(checkForUpdates()) {
                System.out.println(getInfoMessage());
            }
        }
    }


    private Boolean checkForUpdates() {
        if(getSharedHash() != lastHash) {
            lastHash = getSharedHash();
            return true;
        } else {
            return false;
        }
    }


    public void ButtonClick() {
        sendInfoMessage("Test");
    }

    private int getSharedHash() {
        try {
            return sharedObj.getHash();
        } catch (Exception ex) {
            System.out.println("CLIENT: Exception\n" + ex.toString());
            return -1;
        }
    }

    private void sendInfoMessage(String msg) {
        InfoClass i = new InfoClass();
        i.setMessage(msg);
        try {
            sharedObj.setInfo(i);
        } catch (Exception ex) {
            System.out.println("CLIENT: Exception\n" + ex.toString());
        }
    }

    private String getInfoMessage() {
        try {
            return sharedObj.getInfo().getMessage();
        } catch (Exception ex) {
            System.out.println("CLIENT: Exception\n" + ex.toString());
            return "";
        }
    }

}
