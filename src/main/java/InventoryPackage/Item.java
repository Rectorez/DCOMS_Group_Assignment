package InventoryPackage;

public class Item {
    private String ID;
    private String InventoryID;
    private String Name;
    private Double Price;
    private Double Cost;
    private Integer StoredQuantity;
    private Integer SoldQuantity;

    public Item(String inventoryID, String name, Double price, Double cost) {
        InventoryID = inventoryID;
        Name = name;
        Price = price;
        Cost = cost;
    }
    public Item(String ID, String inventoryID, String name, Double price, Double cost, Integer storedQuantity, Integer soldQuantity) {
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
    public Double GetPrice() {
        return Price;
    }
    public Double GetCost() {
        return Cost;
    }
    public Integer GetStoredQuantity() {
        return StoredQuantity;
    }
    public Integer GetSoldQuantity() {
        return SoldQuantity;
    }
}
