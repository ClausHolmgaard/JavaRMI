package javafx_rmi;

public class Main {

    static int port = 2002;
    static String rmiObjectName = "rmi://" + "localhost:" + port + "/Shared";
    //static String rmiObjectName = "//localhost/shared";

    public static void main(String[] args) {
        System.out.println(("Starting server..."));
        RMIServer s = new RMIServer(port, rmiObjectName);

        try
        {
            Thread.sleep(100);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }

        System.out.println("Starting client...");
        RMIClient c = new RMIClient(port, rmiObjectName);
    }
}
