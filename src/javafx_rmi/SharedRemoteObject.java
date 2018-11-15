package javafx_rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class SharedRemoteObject extends UnicastRemoteObject implements SharedObjectInterface {

    String sharedString;

    public SharedRemoteObject() throws RemoteException {
        sharedString = "Init";
    }

    @Override
    public void setString(String inString) throws RemoteException {
        sharedString = inString;
    }

    @Override
    public String getString() throws  RemoteException {
        return sharedString;
    }


}
