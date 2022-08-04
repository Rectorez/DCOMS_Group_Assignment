package Server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GUIInterface extends Remote {
    void displayGUI() throws RemoteException;
}