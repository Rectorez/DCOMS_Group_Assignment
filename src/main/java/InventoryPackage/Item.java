package InventoryPackage;

public class Item {
    private String ID;
    private String InventoryID;
    private String Name;
    private Double Price;
    private Double Cost;
    private Integer StoredQuantity;
    private Integer SoldQuantity;

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
    public Item(String inventoryID, String name, Double price, Double cost) {
        ID = GenerateID();
        InventoryID = inventoryID;
        Name = name;
        Price = price;
        Cost = cost;
        StoredQuantity = 0;
        SoldQuantity = 0;
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
