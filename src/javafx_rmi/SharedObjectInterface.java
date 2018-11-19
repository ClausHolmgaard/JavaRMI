/*
Interface til det delte objekt, skal laves for RMI kan bruges
 */
package javafx_rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface SharedObjectInterface extends Remote {
    // Getter / Setter til en streng. bruges ikke efter implementation custom class
    void setString(String ind) throws RemoteException;
    String getString() throws  RemoteException;

    // Getters / setters til vores custom class
    void setInfo(InfoClass i) throws  RemoteException;
    InfoClass getInfo() throws RemoteException;

    // Metode til at hente en hash. Bruges for at se om der er sket ændringer.
    int getHash() throws RemoteException;
}
