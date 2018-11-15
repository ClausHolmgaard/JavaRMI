package javafx_rmi;

import java.rmi.Naming;
import java.rmi.registry.*;

public class RMIServer {

    static SharedRemoteObject sharedObj;
    static String rmiObjectName;
    static Registry reg;
    static int port;

    public RMIServer(int rmiPort, String objectName) {
        rmiObjectName = objectName;
        port = rmiPort;
        InitServer();
        System.out.println("Server started on " + rmiObjectName);
    }

    private void InitServer() {
        try {
            sharedObj = new SharedRemoteObject();

            reg = LocateRegistry.createRegistry(port);

            Naming.rebind(rmiObjectName, sharedObj);
            System.out.println("Binding complete...\n");
        } catch (Exception ldj) {
            System.out.println("SERVER: Exception\n" + ldj.toString());
        }
    }
}
