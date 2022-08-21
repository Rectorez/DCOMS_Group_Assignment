package InventoryPackage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class InventoryHandler {
    private static List<Inventory> InventoryList = new ArrayList<>();

    public static List<Inventory> GetInventories() {
        return InventoryList.stream()
                .filter(l -> !l.GetStatus().equals(InventoryStatus.DELETED))
                .toList();
    }
    public static List<Inventory> GetFullInventories() {
        return InventoryList;
    }
    public static List<Item> GetItems() {
        return InventoryList.stream()
                .map(l -> l.GetItemList().stream())
                .reduce(Stream::concat)
                .orElse(Stream.empty())
                .toList();
    }
    public static List<Item> GetFullItems() {
        return InventoryList.stream()
                .map(l -> l.GetFullItemList().stream())
                .reduce(Stream::concat)
                .orElse(Stream.empty())
                .toList();
    }
    public static List<Item> GetItems(Inventory inventory) {
        return InventoryList.stream()
                .filter(l -> l.equals(inventory))
                .map(l -> l.GetItemList().stream())
                .reduce(Stream::concat)
                .orElse(Stream.empty())
                .toList();
    }
    public static List<Item> GetFullItems(Inventory inventory) {
        return InventoryList.stream()
                .filter(l -> l.equals(inventory))
                .map(l -> l.GetFullItemList().stream())
                .reduce(Stream::concat)
                .orElse(Stream.empty())
                .toList();
    }

    public static boolean AddInventory(Inventory newInventory) {
        if (GetInventories().stream().anyMatch(i -> i.equals(newInventory))) return false;

        InventoryList.add(newInventory);
        return true;
    }
    public static boolean UpdateInventory(Inventory oldInventory, Inventory newInventory) {
        if (GetInventories().stream().noneMatch(i -> i.equals(oldInventory)) ||
                GetInventories().stream().anyMatch(i -> i.equals(newInventory)) ||
                !oldInventory.GetID().equals(newInventory.GetID())) return false;

        GetFullInventories().set(GetFullInventories().indexOf(oldInventory), newInventory);
        return true;
    }
    public static boolean DeleteInventory(Inventory targetInventory) {
        if (InventoryHandler.GetInventories().stream().noneMatch(i -> i.equals(targetInventory))) return false;

        Inventory inventory = InventoryList.stream()
                .filter(i -> i.equals(targetInventory))
                .findFirst()
                .orElse(null);
        if (inventory == null) return false;
        return inventory.Delete();
    }

    public static boolean AddItem(Item newItem) {
        if (InventoryList.stream().noneMatch(i -> i.GetID().equals(newItem.GetInventoryID())))
            return false;

        return InventoryList.stream()
                .filter(i -> i.GetID().equals(newItem.GetInventoryID()))
                .findFirst()
                .orElse(new Inventory("N/A", "N/A"))
                .AddItem(newItem);
    }
    public static boolean UpdateItem(Item oldItem, Item newItem) {
        if (GetInventories().stream().noneMatch(i -> i.GetID().equals(oldItem.GetInventoryID())) ||
                GetInventories().stream().anyMatch(i -> i.GetID().equals(newItem.GetInventoryID())) ||
                oldItem.GetID().equals(newItem.GetID())) return false;

        Inventory targetInventory = GetFullInventories().stream()
                .filter(i -> i.GetID().equals(oldItem.GetInventoryID()))
                .findFirst()
                .orElse(null);
        if (targetInventory == null) return false;
        targetInventory.UpdateItem(oldItem, newItem);
        return true;
    }
    public static boolean DeleteItem(Item targetItem) {
        if (InventoryList.stream().noneMatch(i -> i.GetID().equals(targetItem.GetInventoryID()))) return false;

        Inventory inventory = InventoryList.stream()
                .filter(i -> i.GetID().equals(targetItem.GetInventoryID()))
                .findFirst()
                .orElse(null);
        if (inventory == null) return false;
        return inventory.DeleteItem(targetItem);
    }

    public static boolean ImportItem(Item targetItem, int amount) {
        if (InventoryList.stream().noneMatch(l -> l.GetItemList().contains(targetItem))) return false;
        return InventoryList.stream()
                .filter(l -> l.GetItemList().contains(targetItem))
                .findFirst()
                .orElse(new Inventory("N/A", "N/A"))
                .ImportItem(targetItem, amount);
    }
    public static boolean ExportItem(Item targetItem, int amount) {
        if (InventoryList.stream().noneMatch(l -> l.GetItemList().contains(targetItem))) return false;
        return InventoryList.stream()
                .filter(l -> l.GetItemList().contains(targetItem))
                .findFirst()
                .orElse(new Inventory("N/A", "N/A"))
                .ExportItem(targetItem, amount);
    }
}
