import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class SharedObject extends UnicastRemoteObject implements  SharedObjectInterface {

    String sharedString;

    public SharedObject() throws RemoteException {
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
