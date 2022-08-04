package Client;

import Client.UI.*;

import javax.swing.*;

import Server.GUIInterface;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main {
    public static void main(String[] args) throws RemoteException, NotBoundException {
        try {
            Registry registry = LocateRegistry.getRegistry();
            GUIInterface loginPage = (GUIInterface) registry.lookup("LoginPage");
            loginPage.displayGUI();
        } catch(Exception e) {
            System.out.println("Server error!");
            e.printStackTrace();
        }
        //LoginPage lp = new LoginPage();
        //MenuPage m = new MenuPage();
        //RegisterPage r = new RegisterPage();
        //InventoryPage i = new InventoryPage();
        //ViewItemPage vItem = new ViewItemPage();
        // eItem = new EditItemPage();
        //SalesPage s = new SalesPage();
        //NewSalesPage ns = new NewSalesPage();

    }
}