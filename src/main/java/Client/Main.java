package Client;

import Client.UI.*;

import Server.AccountInterface;
import Server.InventoryInterface;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main {
    public static AccountInterface accountInterface;
    public static InventoryInterface inventoryInterface;
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        try {
            Registry registry = LocateRegistry.getRegistry(1099);
            accountInterface = (AccountInterface) registry.lookup("accountServer");
            inventoryInterface = (InventoryInterface) registry.lookup("inventoryServer");
            //GUIInterface loginPage = (GUIInterface) registry.lookup("LoginPage");
            LoginPage loginPage = new LoginPage();
            loginPage.displayGUI();
        } catch(Exception e) {
            System.out.println("Server error!");
            e.printStackTrace();
        }

    }
}