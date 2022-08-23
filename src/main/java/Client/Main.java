package Client;

import AccountPackage.Account;
import Client.UI.*;

import InventoryPackage.Inventory;
import InventoryPackage.Item;
import Server.AccountInterface;
import Server.InventoryInterface;
import Server.InvoiceInterface;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static AccountInterface AccountInterface;
    public static InventoryInterface InventoryInterface;
    public static InvoiceInterface InvoiceInterface;
    public static Account currentAccount = null;
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        try {
            Registry registry = LocateRegistry.getRegistry(1099);
            AccountInterface = (AccountInterface) registry.lookup("AccountServer");
            InventoryInterface = (InventoryInterface) registry.lookup("InventoryServer");
            InvoiceInterface = (InvoiceInterface) registry.lookup("InvoiceServer");

            new LoginPage();
        } catch(Exception e) {
            System.out.println("Server error!");
            e.printStackTrace();
        }
    }
}