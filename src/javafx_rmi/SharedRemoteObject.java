package javafx_rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class SharedRemoteObject extends UnicastRemoteObject implements SharedObjectInterface {

    private String sharedString;
    private InfoClass info;
    private int hash;  // Hash simulation


    SharedRemoteObject() throws RemoteException {
        sharedString = "Init";
        hash = 0;
        info = new InfoClass();
    }

    @Override
    public void setString(String inString) {
        hash++;
        sharedString = inString;
    }

    @Override
    public String getString() {
        return sharedString;
    }

    @Override
    public void setInfo(InfoClass i) {
        hash++;
        info = i;
    }

    @Override
    public InfoClass getInfo() {
        return info;
    }

    @Override
    public int getHash() {
        return hash;
    }
}
