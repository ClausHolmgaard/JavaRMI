package javafx_rmi;

public class Main {

    //static int port = 2002;
    //static String rmiObjectName = "rmi://" + "172.16.242.85:" + port + "/Shared";

    public static void main(String[] args) {
        System.setProperty("java.rmi.server.hostname", "172.16.242.85");


        //todo: bruge registry i stedet for set property?
        //Registry rgsty = LocateRegistry.createRegistry(1888);
        //rgsty.rebind("hello", hello);

        System.out.println(("Starting server..."));
        RMIServer s = new RMIServer(port, rmiObjectName);

        System.out.println("Starting client...");
        new RMIClient();

    }
}
