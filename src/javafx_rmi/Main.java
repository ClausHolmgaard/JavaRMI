package javafx_rmi;

public class Main {

    static int port = 2002;
    static String rmiObjectName = "rmi://" + "172.16.242.85:" + port + "/Shared";

    public static void main(String[] args) {
        System.setProperty("java.rmi.server.hostname", "172.16.242.85");

        System.out.println(("Starting server..."));
        RMIServer s = new RMIServer(port, rmiObjectName);

        System.out.println("Starting client...");
        RMIClient c = new RMIClient();
    }
}
