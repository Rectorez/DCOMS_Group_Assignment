package InventoryPackage;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class Item implements Serializable {
    private String ID;
    private String InventoryID;
    private String Name;
    private double Price;
    private double Cost;
    private ItemStatus Status;
    private LocalDateTime CreateDate;
    private LocalDateTime DeleteDate;
    private int StoredQuantity;
    private int SoldQuantity;

    private String GenerateID() {
        int value = InventoryHandler.GetFullInventories()
                .stream()
                .map(i -> i.GetFullItemList().size())
                .reduce(Integer::sum)
                .orElse(0)
                + 1;
        int length = 8;
        StringBuilder ID = new StringBuilder("ITM-");
        ID.append("0".repeat(length - String.valueOf(value).length()));
        ID.append(value);
        return ID.toString();
    }
    public Item(String inventoryID, String name, double price, double cost) {
        ID = GenerateID();
        InventoryID = inventoryID;
        Name = name;
        Price = price;
        Cost = cost;
        Status = ItemStatus.ACTIVE;
        CreateDate = LocalDateTime.now();
        DeleteDate = null;
        StoredQuantity = 0;
        SoldQuantity = 0;
    }
    public Item(String ID, String inventoryID, String name, double price, double cost, int storedQuantity, int soldQuantity) {
        this.ID = ID;
        InventoryID = inventoryID;
        Name = name;
        Price = price;
        Cost = cost;
        Status = ItemStatus.ACTIVE;
        CreateDate = LocalDateTime.now();
        DeleteDate = null;
        StoredQuantity = storedQuantity;
        SoldQuantity = soldQuantity;
    }
    public Item(String ID, String inventoryID, String name, double price, double cost, ItemStatus status, LocalDateTime createDate, LocalDateTime deleteDate, int storedQuantity, int soldQuantity) {
        this.ID = ID;
        InventoryID = inventoryID;
        Name = name;
        Price = price;
        Cost = cost;
        Status = status;
        CreateDate = createDate;
        DeleteDate = deleteDate;
        StoredQuantity = storedQuantity;
        SoldQuantity = soldQuantity;
    }

    public String GetID() {
        return ID;
    }
    public String GetInventoryID() {
        return InventoryID;
    }
    public String GetName() {
        return Name;
    }
    public double GetPrice() {
        return Price;
    }
    public double GetCost() {
        return Cost;
    }
    public ItemStatus GetStatus() {
        return Status;
    }
    public LocalDateTime GetCreateDate() {
        return CreateDate;
    }
    public LocalDateTime GetDeleteDate() {
        return DeleteDate;
    }
    public int GetStoredQuantity() {
        return StoredQuantity;
    }
    public int GetSoldQuantity() {
        return SoldQuantity;
    }

    public boolean Import(int amount) {
        if (amount < 0) return false;
        StoredQuantity += amount;
        return true;
    }
    public boolean Export(int amount) {
        if (amount < 0 || amount > StoredQuantity - SoldQuantity) return false;
        SoldQuantity += amount;
        return true;
    }

    public boolean Delete() {
        if (Status == ItemStatus.DELETED) return false;
        Status = ItemStatus.DELETED;
        DeleteDate = LocalDateTime.now();
        return true;
    }

    public boolean Remove(int amount) {
        if (amount < 0) return false;
        SoldQuantity -= amount;
        return true;
    }
    @Override
    public String toString() {
        return "Item{" +
                "ID='" + ID + '\'' +
                ", InventoryID='" + InventoryID + '\'' +
                ", Name='" + Name + '\'' +
                ", Price=" + Price +
                ", Cost=" + Cost +
                ", Status=" + Status +
                ", CreateDate=" + CreateDate +
                ", DeleteDate=" + DeleteDate +
                ", StoredQuantity=" + StoredQuantity +
                ", SoldQuantity=" + SoldQuantity +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Double.compare(item.Price, Price) == 0 && Double.compare(item.Cost, Cost) == 0 && StoredQuantity == item.StoredQuantity && SoldQuantity == item.SoldQuantity && Objects.equals(ID, item.ID) && Objects.equals(InventoryID, item.InventoryID) && Objects.equals(Name, item.Name) && Status == item.Status && Objects.equals(CreateDate, item.CreateDate) && Objects.equals(DeleteDate, item.DeleteDate);
    }
    @Override
    public int hashCode() {
        return Objects.hash(ID, InventoryID, Name, Price, Cost, Status, CreateDate, DeleteDate, StoredQuantity, SoldQuantity);
    }
}
