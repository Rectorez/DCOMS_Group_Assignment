package InventoryPackage;

import java.time.LocalDateTime;
import java.util.List;

public class Inventory {
    private String ID;
    private String Name;
    private String Description;
    private List<Item> ItemList;
    private String Status;
    private LocalDateTime CreateDate;
    private LocalDateTime DeleteDate;

    public Inventory(String name, String description) {
        Name = name;
        Description = description;
    }
    public Inventory(String ID, String name, String description, List<Item> itemList, String status, LocalDateTime createDate, LocalDateTime deleteDate) {
        this.ID = ID;
        Name = name;
        Description = description;
        ItemList = itemList;
        Status = status;
        CreateDate = createDate;
        DeleteDate = deleteDate;
    }

    public String GetID() {
        return ID;
    }
    public String GetName() {
        return Name;
    }
    public String GetDescription() {
        return Description;
    }
    public List<Item> GetItemList() {
        return ItemList;
    }
    public String GetStatus() {
        return Status;
    }
    public LocalDateTime GetCreateDate() {
        return CreateDate;
    }
    public LocalDateTime GetDeleteDate() {
        return DeleteDate;
    }

    public boolean AddItem(Item newItem) {
        if (!newItem.GetInventoryID().equals(ID))
            return false;

        ItemList.add(newItem);
        return true;
    }
    public boolean UpdateItem(Item oldItem, Item newItem) {
        if (!oldItem.GetInventoryID().equals(ID) ||
                !newItem.GetInventoryID().equals(ID) ||
                !ItemList.contains(oldItem))
            return false;

        ItemList.set(ItemList.indexOf(oldItem), newItem);
        return true;
    }
    public boolean DeleteItem(Item item) {
        if (!item.GetInventoryID().equals(ID) ||
                ItemList.contains(item))
            return false;

        ItemList.remove(item);
        return true;
    }
}
