package InventoryPackage;

public class Item {
    private String ID;
    private String InventoryID;
    private String Name;
    private double Price;
    private double Cost;
    private int StoredQuantity;
    private int SoldQuantity;

    private String GenerateID() {
        int value = InventoryHandler.GetInventories()
                .stream()
                .map(i -> i.GetItemList().size())
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
        StoredQuantity = 0;
        SoldQuantity = 0;
    }
    public Item(String ID, String inventoryID, String name, double price, double cost, int storedQuantity, int soldQuantity) {
        this.ID = ID;
        InventoryID = inventoryID;
        Name = name;
        Price = price;
        Cost = cost;
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
}
