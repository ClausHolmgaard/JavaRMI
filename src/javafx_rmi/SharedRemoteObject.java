package javafx_rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class SharedRemoteObject extends UnicastRemoteObject implements SharedObjectInterface {

    private String sharedString;
    private InfoClass info;
    private int hash;  // Hash simulation


    public SharedRemoteObject() throws RemoteException {
        sharedString = "Init";
        hash = 0;
        info = new InfoClass();
    }

    @Override
    public void setString(String inString) throws RemoteException {
        hash++;
        sharedString = inString;
    }

    @Override
    public String getString() throws  RemoteException {
        return sharedString;
    }

    @Override
    public void setInfo(InfoClass i) throws RemoteException {
        hash++;
        info = i;
    }

    @Override
    public InfoClass getInfo() throws  RemoteException {
        return info;
    }

    @Override
    public int getHash() throws RemoteException {
        return hash;
    }
}
