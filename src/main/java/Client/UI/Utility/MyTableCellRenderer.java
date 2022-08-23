package Client.UI.Utility;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.text.NumberFormat;

public class MyTableCellRenderer extends DefaultTableCellRenderer {
    public  MyTableCellRenderer(){
        this.setOpaque(false);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        setBorder(new EmptyBorder(5,5,5,5));

        if(value instanceof Number){
            //setText(NumberFormat.getNumberInstance().format(value));
            setHorizontalAlignment(RIGHT);
        }
        else {
            setHorizontalAlignment(LEFT);
        }

        return this;
    }
}
