package javafx_rmi;

public class Main {

    static int port = 2002;
    static String rmiObjectName = "rmi://" + "localhost:" + port + "/Shared";

    public static void main(String[] args) {
        System.out.println(("Starting server..."));
        RMIServer s = new RMIServer(port, rmiObjectName);

        System.out.println("Starting client...");
        RMIClient c = new RMIClient();
    }
}
