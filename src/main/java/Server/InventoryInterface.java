package Server;

import InventoryPackage.Inventory;
import InventoryPackage.Item;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface InventoryInterface extends Remote {
    //Getters
    List<Inventory> GetInventories() throws RemoteException;
    List<Item> GetItems() throws RemoteException;
    List<Item> GetItems(Inventory inventory) throws RemoteException;

    //Manipulation
    boolean AddInventory(Inventory newInventory) throws RemoteException;
    boolean UpdateInventory(Inventory oldInventory, Inventory newInventory) throws RemoteException;
    boolean DeleteInventory(Inventory inventory) throws RemoteException;
    boolean AddItem(Item newItem) throws RemoteException;
    boolean UpdateItem(Item oldItem, Item newItem) throws RemoteException;
    boolean DeleteItem(Item item) throws RemoteException;

    //Import / Export
    boolean ImportItem(Item targetItem, int amount) throws RemoteException;
    boolean ExportItem(Item targetItem, int amount) throws RemoteException;
}
