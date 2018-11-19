/*
Klient delen af RMI implementationen
 */
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

    /*
    Start af klienten.
    RMI er ikke beregnet til at lave et "chat" program, så vi checker for updates hver 100ms
     */
    @SuppressWarnings("InfiniteLoopStatement")
    private void InitClient() {
        try {
            while (true) {
                if(checkForUpdates()) {  // hvis der er sket opdateringer
                    gui.addElementToListModel(getInfoMessage());  // Håndter dem
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

    /*
    Connect til server
     */
    private void connect() {

        // Hent ip og port fra gui
        String ip = gui.getIP();
        int port = gui.getPort();

        // Konstruer smi object fra strenge
        String folder = "/Shared";
        rmiObjectName = "rmi://" + ip + ":" + port + folder;

        System.setProperty("java.rmi.server.hostname", ip);

        try {
            // Hent en reference til det delte objekt fra serveren
            sharedObj = (SharedObjectInterface) Naming.lookup(rmiObjectName);
            System.out.println("KLIENTEN, har nu objektet");
            isConnected = true;
        } catch (java.rmi.ConnectException conEx) {
            // Håndter forbindelses fejl
            System.out.println("CLIENT: Unable to connect to server!");
            isConnected = false;
        } catch (Exception ex) {
            // Håndter andre fejl
            System.out.println("CLIENT: Exception\n" + ex.toString());
            isConnected = false;
        }

        // Fortæl gui'en at der er forbindelse
        gui.setConnected(isConnected);
    }

    /*
    Check om der er updates på objektet fra serveren.
    Dette gøres ved at se om hashen i objektet er ændret fra sidst sete.
     */
    private Boolean checkForUpdates() {
        // Hent hash, sammenlign med sidst sete, hvis der er forbindelse
        if(getSharedHash() != lastHash && isConnected) {
            lastHash = getSharedHash();
            return true;
        } else {
            return false;
        }
    }

    /*
    Når der trykkes på Send knappen.
    Burde implementeres som en Send metode, der ikke ved noget om der kommer noget fra en gui del.
     */
    void sendMessageButton() {
        if(!isConnected) {
            connect();
        }
        sendInfoMessage(gui.getMessageText());
        gui.setMessageEditField("");
    }

    /*
    Når der trykkes på Connect knappen
     */
    void ConnectClick() {
        connect();
    }

    /*
    Hent hash værdien fra det delte objekt
    returns: -1 hvis der ikke er forbindelse
             -2 hvis der sker en fejl ved hentning
     */
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

    /*
    Metode til at sætte en ny besked i custom class den delte klasse
     */
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

    /*
    Metode til at hente beskeden i custom class den delte klasse
     */
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
