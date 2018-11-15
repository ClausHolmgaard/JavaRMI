package javafx_rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface SharedObjectInterface extends Remote {
    void setString(String ind) throws RemoteException;
    String getString() throws  RemoteException;

    void setInfo(InfoClass i) throws  RemoteException;
    InfoClass getInfo() throws RemoteException;

    int getHash() throws RemoteException;
}
