import java.rmi.Remote;
import java.rmi.RemoteException;


public interface SharedObjectInterface extends Remote {
    public void setString(String ind) throws RemoteException;
    public String getString() throws  RemoteException;
}
