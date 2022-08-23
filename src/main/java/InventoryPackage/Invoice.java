package InventoryPackage;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Invoice implements Serializable {
    private String ID;
    private HashMap<String, ArrayList<String>> SoldItemList;
    private double Total;
    private LocalDateTime CreateDate;

    private String GenerateID() {
        int value = InventoryHandler.GetFullInventories().size() + 1;
        int length = 6;
        StringBuilder ID = new StringBuilder("INV-");
        ID.append("0".repeat(length - String.valueOf(value).length()));
        ID.append(value);
        return ID.toString();
    }

    public Invoice(HashMap<String, ArrayList<String>> soldItemList, double total, LocalDateTime createDate) {
        ID = GenerateID();
        SoldItemList = soldItemList;
        Total = total;
        CreateDate = LocalDateTime.now();
    }

    public String getID() {
        return ID;
    }

    public HashMap<String, ArrayList<String>> getSoldItemList() {
        return SoldItemList;
    }

    public double getTotal() {
        return Total;
    }

    public LocalDateTime getCreateDate() {
        return CreateDate;
    }

}
