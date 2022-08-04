package Server;

import Client.UI.LoginPage;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIRegistry {
    public static void main(String[] args) throws RemoteException {
        try {
            Registry reg = LocateRegistry.createRegistry(1099);
            reg.rebind("LoginPage",new LoginPage());
            System.out.println("Server is ready!");
        } catch(RemoteException e) {
            System.out.println("Server error!");
            e.printStackTrace();
        }

    }
}
