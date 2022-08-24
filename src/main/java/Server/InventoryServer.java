package Server;

import InventoryPackage.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.util.List;

public class InventoryServer extends UnicastRemoteObject implements InventoryInterface {
    public InventoryServer() throws RemoteException {
        super();
    }

    //Getters
    public List<Inventory> GetInventories() {
        return InventoryHandler.GetInventories();
    }
    public List<Inventory> GetFullInventories() {
        return InventoryHandler.GetFullInventories();
    }
    public List<Item> GetItems() {
        return InventoryHandler.GetItems();
    }
    public List<Item> GetFullItems() {
        return InventoryHandler.GetFullItems();
    }
    public List<Item> GetItems(Inventory inventory) {
        return InventoryHandler.GetItems(inventory);
    }
    public List<Item> GetFullItems(Inventory inventory) {
        return InventoryHandler.GetFullItems(inventory);
    }

    //Manipulation
    public boolean AddInventory(Inventory newInventory) {
        return InventoryHandler.AddInventory(newInventory);
    }
    public boolean UpdateInventory(Inventory oldInventory, Inventory newInventory) {
        return InventoryHandler.UpdateInventory(oldInventory, newInventory);
    }
    public boolean DeleteInventory(Inventory targetInventory) {
        return InventoryHandler.DeleteInventory(targetInventory);
    }
    public boolean AddItem(Item newItem) {
        return InventoryHandler.AddItem(newItem);
    }
    public boolean UpdateItem(Item oldItem, Item newItem) {
        return InventoryHandler.UpdateItem(oldItem, newItem);
    }
    public boolean DeleteItem(Item targetItem) {
        return InventoryHandler.DeleteItem(targetItem);
    }

    //Object Generation
    public Inventory GenerateInventory(String name, String description) {
        return new Inventory(name, description);
    }
    public Inventory GenerateInventory(String ID, String name, String description, List<Item> itemList, InventoryStatus status, LocalDateTime createDate, LocalDateTime deleteDate) {
        return new Inventory(ID, name, description, itemList, status, createDate, deleteDate);
    }
    public Item GenerateItem (String inventoryID, String name, double price, double cost) {
        return new Item(inventoryID, name, price, cost);
    }
    public Item GenerateItem(String ID, String inventoryID, String name, double price, double cost, int storedQuantity, int soldQuantity) {
        return new Item(ID, inventoryID, name, price, cost, storedQuantity, soldQuantity);
    }
    public Item GenerateItem(String ID, String inventoryID, String name, double price, double cost, ItemStatus status, LocalDateTime createDate, LocalDateTime deleteDate, int storedQuantity, int soldQuantity) {
        return new Item(ID, inventoryID, name, price, cost, status, createDate, deleteDate, storedQuantity, soldQuantity);
    }

    //Import / Export
    public boolean ImportItem(Item targetItem, int amount) {
        return InventoryHandler.ImportItem(targetItem, amount);
    }
    public boolean ExportItem(Item targetItem, int amount) {
        return InventoryHandler.ExportItem(targetItem, amount);
    }
    public boolean RemoveItem(Item targetItem, int amount) {
        return InventoryHandler.RemoveItem(targetItem, amount);
    }
}
