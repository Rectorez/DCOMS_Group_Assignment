package Server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class InventoryServer extends UnicastRemoteObject implements InventoryInterface {

    public InventoryServer() throws RemoteException {
        super();
    }
}
