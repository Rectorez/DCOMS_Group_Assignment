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
    public static AccountInterface AccountInterface;
    public static InventoryInterface InventoryInterface;
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        try {
            Registry registry = LocateRegistry.getRegistry(1099);
            AccountInterface = (AccountInterface) registry.lookup("AccountServer");
            InventoryInterface = (InventoryInterface) registry.lookup("InventoryServer");

            //GUIInterface loginPage = (GUIInterface) registry.lookup("LoginPage");
            LoginPage loginPage = new LoginPage();
            loginPage.displayGUI();
        } catch(Exception e) {
            System.out.println("Server error!");
            e.printStackTrace();
        }

    }
}