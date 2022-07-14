package Client.UI.Utility;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MyListCellRenderer extends DefaultListCellRenderer {
    public  MyListCellRenderer(){

    }

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        //setBackground(new Color(getRandom255(), getRandom255(), getRandom255()));

        setBorder(new EmptyBorder(5,5,5,5));

        return this;
    }

    public int getRandom255(){
        return (int)(Math.random() * 256);
    }
}
