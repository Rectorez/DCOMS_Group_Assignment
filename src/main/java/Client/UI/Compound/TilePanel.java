package Client.UI.Compound;

import Client.UI.DesignUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TilePanel extends PanelRound{
    JLabel iconLabel, textLabel;
    ImageIcon icon;

    public TilePanel(ImageIcon icon, String title, Color color){
        setLayout(new BorderLayout(5, 5));
        setBackground(color);
        setRoundAll(50);

        this.icon = icon;
        int iconHeight = Math.max((int)(this.getHeight() * 0.5), 150);
        iconLabel = new JLabel(getScaledImage(icon, iconHeight, iconHeight));
        textLabel = new JLabel(title);
        textLabel.setBorder(new EmptyBorder(10,5,20,5));
        textLabel.setFont(DesignUI.defaultFontBold);
        textLabel.setForeground(DesignUI.fontWhiteColor);
        textLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(iconLabel, BorderLayout.CENTER);
        add(textLabel, BorderLayout.SOUTH);

    }

    public void resizeIcon(){
        int iconHeight = Math.max((int)(this.getHeight() * 0.5), 150);
        iconLabel.setIcon(getScaledImage(icon, iconHeight, iconHeight));
    }
    private ImageIcon getScaledImage(ImageIcon og, int w, int h){
        Image i = og.getImage();
        Image scaled = i.getScaledInstance(w, h, Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    }


}
