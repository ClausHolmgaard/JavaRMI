package javafx_rmi;

public class Main {

    static String ip = "172.16.240.183";
    static int port = 2002;
    static String rmiObjectName = "rmi://" + ip + ":" + port + "/Shared";

    public static void main(String[] args) {
        System.setProperty("java.rmi.server.hostname", ip);

        System.out.println(("Starting server..."));
        // Start af serveren, kommenter ud, hvis kun klienten skal startes
        RMIServer s = new RMIServer(port, rmiObjectName);


        System.out.println("Starting client...");
        // Klienten startes
        new RMIClient();
    }
}
