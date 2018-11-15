package javafx_rmi;

import java.rmi.Naming;


class RMIClient {

    private SharedObjectInterface sharedObj;
    private String rmiObjectName;
    private int port;

    public  RMIClient(int rmiPort, String objectName) {
        rmiObjectName = objectName;
        port = rmiPort;

        ClientGUI g = new ClientGUI(this);

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

    public void ButtonClick() {
        try {
            System.out.println("Text in object: " + sharedObj.getString());
        } catch(Exception ex) {
            System.out.println("CLIENT: Exception when reading.\n" + ex.toString());
        }
    }

}
