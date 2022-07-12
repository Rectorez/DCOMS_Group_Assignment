package InventoryPackage;

import java.util.ArrayList;
import java.util.List;

public class InventoryHandler {
    private static List<Inventory> InventoryList;

    public static List<Inventory> GetInventories() {
        return new ArrayList<>();
    }
    public static List<Item> GetItems() {
        return new ArrayList<>();
    }
    public static List<Item> GetItems(Inventory inventory) {
        return new ArrayList<>();
    }

    public static void AddInventory(Inventory newInventory) {
        return;
    }
    public static void UpdateInventory(Inventory oldInventory, Inventory newInventory) {
        return;
    }
    public static void DeleteInventory(Inventory inventory) {
        return;
    }

    public static void AddItem(Item newItem) {
        return;
    }
    public static void UpdateItem(Item oldItem, Item newItem) {
        return;
    }
    public static void DeleteItem(Item item) {
        return;
    }
}
