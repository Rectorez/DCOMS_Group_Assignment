package Server;

import InventoryPackage.Inventory;
import InventoryPackage.InventoryHandler;
import InventoryPackage.Item;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class InventoryServer extends UnicastRemoteObject implements InventoryInterface {
    public InventoryServer() throws RemoteException {
        super();
    }

    //Getters
    public List<Inventory> GetInventories() {
        return InventoryHandler.GetInventories();
    }
    public List<Item> GetItems() {
        return InventoryHandler.GetItems();
    }
    public List<Item> GetItems(Inventory inventory) {
        return InventoryHandler.GetItems(inventory);
    }

    //Manipulation
    public boolean AddInventory(Inventory newInventory) {
        return InventoryHandler.AddInventory(newInventory);
    }
    public boolean UpdateInventory(Inventory oldInventory, Inventory newInventory) {
        return InventoryHandler.UpdateInventory(oldInventory, newInventory);
    }
    public boolean DeleteInventory(Inventory inventory) {
        return InventoryHandler.DeleteInventory(inventory);
    }
    public boolean AddItem(Item newItem) {
        return InventoryHandler.AddItem(newItem);
    }
    public boolean UpdateItem(Item oldItem, Item newItem) {
        return InventoryHandler.UpdateItem(oldItem, newItem);
    }
    public boolean DeleteItem(Item item) {
        return InventoryHandler.DeleteItem(item);
    }

    //Import / Export
    public boolean ImportItem(Item targetItem, int amount) {
        return InventoryHandler.ImportItem(targetItem, amount);
    }
    public boolean ExportItem(Item targetItem, int amount) {
        return InventoryHandler.ExportItem(targetItem, amount);
    }
}
