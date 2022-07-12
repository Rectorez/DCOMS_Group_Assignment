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

    public String getID() {
        return ID;
    }
    public String getName() {
        return Name;
    }
    public String getDescription() {
        return Description;
    }
    public List<Item> getItemList() {
        return ItemList;
    }
    public String getStatus() {
        return Status;
    }
    public LocalDateTime getCreateDate() {
        return CreateDate;
    }
    public LocalDateTime getDeleteDate() {
        return DeleteDate;
    }

    public void AddItem(Item newItem) {

    }
    public void UpdateItem(Item oldItem, Item newItem) {

    }
    public void DeleteItem(Item item) {

    }
}
