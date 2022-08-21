package Server;

import InventoryPackage.Inventory;
import InventoryPackage.InventoryStatus;
import InventoryPackage.Item;
import InventoryPackage.ItemStatus;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.List;

public interface InventoryInterface extends Remote {
    //Getters
    List<Inventory> GetInventories() throws RemoteException;
    List<Inventory> GetFullInventories() throws RemoteException;
    List<Item> GetItems() throws RemoteException;
    List<Item> GetFullItems() throws RemoteException;
    List<Item> GetItems(Inventory inventory) throws RemoteException;
    List<Item> GetFullItems(Inventory inventory) throws RemoteException;

    //Manipulation
    boolean AddInventory(Inventory newInventory) throws RemoteException;
    boolean UpdateInventory(Inventory oldInventory, Inventory newInventory) throws RemoteException;
    boolean DeleteInventory(Inventory targetInventory) throws RemoteException;
    boolean AddItem(Item newItem) throws RemoteException;
    boolean UpdateItem(Item oldItem, Item newItem) throws RemoteException;
    boolean DeleteItem(Item targetItem) throws RemoteException;

    //Object Generation
    Inventory GenerateInventory(String name, String description) throws RemoteException;
    Inventory GenerateInventory(String ID, String name, String description, List<Item> itemList, InventoryStatus status, LocalDateTime createDate, LocalDateTime deleteDate) throws RemoteException;
    Item GenerateItem(String inventoryID, String name, double price, double cost) throws RemoteException;
    Item GenerateItem(String ID, String inventoryID, String name, double price, double cost, int storedQuantity, int soldQuantity) throws RemoteException;
    Item GenerateItem(String ID, String inventoryID, String name, double price, double cost, ItemStatus status, LocalDateTime createDate, LocalDateTime deleteDate, int storedQuantity, int soldQuantity) throws RemoteException;

    //Import / Export
    boolean ImportItem(Item targetItem, int amount) throws RemoteException;
    boolean ExportItem(Item targetItem, int amount) throws RemoteException;
}
