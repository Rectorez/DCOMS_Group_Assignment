package InventoryPackage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class InventoryHandler {
    private static List<Inventory> InventoryList = new ArrayList<>();

    public static List<Inventory> GetInventories() {
        return InventoryList;
    }
    public static List<Item> GetItems() {
        return InventoryList.stream()
                .map(l -> l.GetItemList().stream())
                .reduce(Stream::concat)
                .orElse(Stream.empty())
                .toList();
    }
    public static List<Item> GetItems(Inventory inventory) {
        return InventoryList.stream()
                .map(l -> l.GetItemList().stream())
                .reduce(Stream::concat)
                .orElse(Stream.empty())
                .toList();
    }

    public static boolean AddInventory(Inventory newInventory) {
        if (InventoryHandler.GetInventories().stream()
                .anyMatch(i -> i.equals(newInventory)))
            return false;

        InventoryList.add(newInventory);
        return true;
    }
    public static boolean UpdateInventory(Inventory oldInventory, Inventory newInventory) {
        if (InventoryHandler.GetInventories().stream().noneMatch(i -> i.equals(oldInventory)) ||
                InventoryHandler.GetInventories().stream().anyMatch(i -> i.equals(newInventory)))
            return false;

        InventoryList.set(InventoryList.indexOf(oldInventory), newInventory);
        return true;
    }
    public static boolean DeleteInventory(Inventory inventory) {
        if (InventoryHandler.GetInventories().stream()
                .noneMatch(i -> i.equals(inventory)))
            return false;

        InventoryList.remove(inventory);
        return true;
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
        if (InventoryList.stream().noneMatch(i -> i.GetID().equals(oldItem.GetInventoryID())) ||
                InventoryList.stream().noneMatch(i -> i.GetID().equals(newItem.GetInventoryID())) ||
                !oldItem.GetInventoryID().equals(newItem.GetInventoryID()))
            return false;

        return InventoryList.stream()
                .filter(i -> i.GetID().equals(oldItem.GetInventoryID()))
                .findFirst()
                .orElse(new Inventory("N/A", "N/A"))
                .UpdateItem(oldItem, newItem);
    }
    public static boolean DeleteItem(Item item) {
        if (InventoryList.stream().noneMatch(i -> i.GetID().equals(item.GetInventoryID())))
            return false;

        return InventoryList.stream()
                .filter(i -> i.GetID().equals(item.GetInventoryID()))
                .findFirst()
                .orElse(new Inventory("N/A", "N/A"))
                .DeleteItem(item);
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
