package InventoryPackage;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Inventory implements Serializable {
    private String ID;
    private String Name;
    private String Description;
    private List<Item> ItemList;
    private InventoryStatus Status;
    private LocalDateTime CreateDate;
    private LocalDateTime DeleteDate;

    private String GenerateID() {
        int value = InventoryHandler.GetFullInventories().size() + 1;
        int length = 4;
        StringBuilder ID = new StringBuilder("INV-");
        ID.append("0".repeat(length - String.valueOf(value).length()));
        ID.append(value);
        return ID.toString();
    }
    public Inventory(String name, String description) {
        ID = GenerateID();
        Name = name;
        Description = description;
        ItemList = new ArrayList<>();
        Status = InventoryStatus.EMPTY;
        CreateDate = LocalDateTime.now();
        DeleteDate = null;
    }
    public Inventory(String ID, String name, String description, List<Item> itemList, InventoryStatus status, LocalDateTime createDate, LocalDateTime deleteDate) {
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
        return ItemList.stream()
                .filter(i -> i.GetStatus().equals(ItemStatus.ACTIVE))
                .toList();
    }
    public List<Item> GetFullItemList() {
        return ItemList;
    }
    public InventoryStatus GetStatus() {
        return Status;
    }
    public LocalDateTime GetCreateDate() {
        return CreateDate;
    }
    public LocalDateTime GetDeleteDate() {
        return DeleteDate;
    }

    public boolean AddItem(Item newItem) {
        if (!newItem.GetInventoryID().equals(ID)) return false;
        ItemList.add(newItem);

        if (ItemList.stream().anyMatch(i -> i.GetStatus().equals(ItemStatus.ACTIVE))) Status = InventoryStatus.FILLED;
        else Status = InventoryStatus.EMPTY;
        return true;
    }
    public boolean UpdateItem(Item oldItem, Item newItem) {
        if (!(oldItem.GetInventoryID().equals(ID) && newItem.GetInventoryID().equals(ID) &&
                oldItem.GetID().equals(newItem.GetID()) && ItemList.contains(oldItem)))
            return false;
        ItemList.set(ItemList.indexOf(oldItem), newItem);

        if (ItemList.stream().anyMatch(i -> i.GetStatus().equals(ItemStatus.ACTIVE))) Status = InventoryStatus.FILLED;
        else Status = InventoryStatus.EMPTY;
        return true;
    }
    public boolean DeleteItem(Item targetItem) {
        if (!ItemList.contains(targetItem)) return false;
        Item item = ItemList.stream()
                .filter(i -> i.equals(targetItem))
                .findFirst()
                .orElse(null);

        if (item == null) return false;
        boolean deleteSuccessful = item.Delete();

        if (ItemList.stream().anyMatch(i -> i.GetStatus().equals(ItemStatus.ACTIVE))) Status = InventoryStatus.FILLED;
        else Status = InventoryStatus.EMPTY;
        return deleteSuccessful;
    }

    public boolean ImportItem(Item targetItem, int amount) {
        if (!ItemList.contains(targetItem)) return false;
        return ItemList.get(ItemList.indexOf(targetItem)).Import(amount);
    }
    public boolean ExportItem(Item targetItem, int amount) {
        if (!ItemList.contains(targetItem)) return false;
        return ItemList.get(ItemList.indexOf(targetItem)).Export(amount);
    }

    public boolean Delete() {
        if (Status == InventoryStatus.DELETED) return false;
        Status = InventoryStatus.DELETED;
        DeleteDate = LocalDateTime.now();
        for (Item item : ItemList) item.Delete();
        return true;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "ID='" + ID + '\'' +
                ", Name='" + Name + '\'' +
                ", Description='" + Description + '\'' +
                ", Status=" + Status +
                ", CreateDate=" + CreateDate +
                ", DeleteDate=" + DeleteDate +
                '}' + (GetItemList().size() > 0 ? "\n\t" + String.join("\n\t", GetItemList().stream().map(Item::toString).toList()) : "");
    }
    public String toFullString() {
        return "Inventory{" +
                "ID='" + ID + '\'' +
                ", Name='" + Name + '\'' +
                ", Description='" + Description + '\'' +
                ", Status=" + Status +
                ", CreateDate=" + CreateDate +
                ", DeleteDate=" + DeleteDate +
                '}' + (GetFullItemList().size() > 0 ? "\n\t" + String.join("\n\t", GetFullItemList().stream().map(Item::toString).toList()) : "");
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inventory inventory = (Inventory) o;
        return Objects.equals(ID, inventory.ID) && Objects.equals(Name, inventory.Name) && Objects.equals(Description, inventory.Description) && Objects.equals(ItemList, inventory.ItemList) && Status == inventory.Status && Objects.equals(CreateDate, inventory.CreateDate) && Objects.equals(DeleteDate, inventory.DeleteDate);
    }
    @Override
    public int hashCode() {
        return Objects.hash(ID, Name, Description, ItemList, Status, CreateDate, DeleteDate);
    }
}
