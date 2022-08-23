package Client.UI.Utility;

import InventoryPackage.Inventory;
import InventoryPackage.Invoice;
import InventoryPackage.Item;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.format.DateTimeFormatter;

public class MyListCellRenderer extends DefaultListCellRenderer {
    public  MyListCellRenderer(){

    }

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        if(value instanceof Inventory){
            setText(((Inventory)value).GetName());
        }
        if(value instanceof Item){
            Item i = (Item)value;
            setText(String.format("%s: %s", i.GetID(), i.GetName()));
        }
        if(value instanceof Invoice){
            Invoice i = (Invoice) value;
            setText(String.format("%s - %s", i.getID(), i.getCreateDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss"))));
        }

        setBorder(new EmptyBorder(5,5,5,5));

        return this;
    }

    public int getRandom255(){
        return (int)(Math.random() * 256);
    }
}
