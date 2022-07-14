package Client.UI;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class DesignUI {
    public static Font defaultFont = new Font("Arial", Font.PLAIN, 16);
    public static Font defaultFontBold = new Font("Arial", Font.BOLD, 16);
    public static Font bigFont = new Font("Arial", Font.PLAIN, 24);
    public static Font titleFont = new Font("Arial", Font.BOLD, 24);
    public static Font tooltipFont = new Font("Arial", Font.ITALIC, 12);

    public static Color red = new Color(234, 107, 102);
    public static Color green = new Color(26, 188, 156);
    public static Color orange = new Color(243, 156, 18);
    public static Color blue = new Color(0, 127, 255);
    public static Color pink = new Color(253, 167, 223);

    public static Color fontWhiteColor = Color.white;

    public static class RegisterBtn extends JButton {
        public RegisterBtn(){
            setText("Register");
            setFont(DesignUI.defaultFont);
            setForeground(DesignUI.fontWhiteColor);
            setBackground(DesignUI.blue);
            setBorder(new CompoundBorder(new LineBorder(DesignUI.blue), new EmptyBorder(10,5,10,5)));
        }
        public RegisterBtn(String text){
            setText(text);
            setFont(DesignUI.defaultFont);
            setForeground(DesignUI.fontWhiteColor);
            setBackground(DesignUI.blue);
            setBorder(new CompoundBorder(new LineBorder(DesignUI.blue), new EmptyBorder(10,5,10,5)));
        }
    }

    public static class DiscardBtn extends JButton {
        public DiscardBtn(){
            setText("Discard");
            setFont(DesignUI.defaultFont);
            setForeground(DesignUI.orange);
            setBackground(Color.white);
            setBorder(new CompoundBorder(new LineBorder(DesignUI.orange), new EmptyBorder(10,5,10,5)));
        }
        public DiscardBtn(String text){
            setText(text);
            setFont(DesignUI.defaultFont);
            setForeground(DesignUI.orange);
            setBackground(Color.white);
            setBorder(new CompoundBorder(new LineBorder(DesignUI.orange), new EmptyBorder(10,5,10,5)));
        }
    }

    public static class DeleteBtn extends JButton {
        public DeleteBtn(){
            setText("Delete");
            setFont(DesignUI.defaultFont);
            setForeground(DesignUI.fontWhiteColor);
            setBackground(DesignUI.red);
            setBorder(new CompoundBorder(new LineBorder(DesignUI.red), new EmptyBorder(10,5,10,5)));
        }
        public DeleteBtn(String text){
            setText(text);
            setFont(DesignUI.defaultFont);
            setForeground(DesignUI.fontWhiteColor);
            setBackground(DesignUI.red);
            setBorder(new CompoundBorder(new LineBorder(DesignUI.red), new EmptyBorder(10,5,10,5)));
        }
    }

    public static class ConfirmBtn extends JButton {
        public ConfirmBtn(){
            setText("Confirm");
            setFont(DesignUI.defaultFont);
            setForeground(DesignUI.fontWhiteColor);
            setBackground(DesignUI.green);
            setBorder(new CompoundBorder(new LineBorder(DesignUI.green), new EmptyBorder(10,5,10,5)));
        }
        public ConfirmBtn(String text){
            setText(text);
            setFont(DesignUI.defaultFont);
            setForeground(DesignUI.fontWhiteColor);
            setBackground(DesignUI.green);
            setBorder(new CompoundBorder(new LineBorder(DesignUI.green), new EmptyBorder(10,5,10,5)));
        }
    }

    public static class AddBtn extends JButton{
        public AddBtn(){
            setText("Add New");
            setFont(DesignUI.defaultFont);
            setForeground(DesignUI.blue);
            setBackground(Color.white);
            setBorder(new CompoundBorder(new LineBorder(DesignUI.blue), new EmptyBorder(10,5,10,5)));
        }
        public AddBtn(String text){
            setText(text);
            setFont(DesignUI.defaultFont);
            setForeground(DesignUI.blue);
            setBackground(Color.white);
            setBorder(new CompoundBorder(new LineBorder(DesignUI.blue), new EmptyBorder(10,5,10,5)));
        }
    }

    public static class BlueButton extends  JButton{
        public BlueButton(){
            setText("");
            setFont(DesignUI.defaultFont);
            setForeground(DesignUI.fontWhiteColor);
            setBackground(DesignUI.blue);
            setBorder(new CompoundBorder(new LineBorder(DesignUI.blue), new EmptyBorder(10,5,10,5)));
        }
        public BlueButton(String text){
            setText(text);
            setFont(DesignUI.defaultFont);
            setForeground(DesignUI.fontWhiteColor);
            setBackground(DesignUI.blue);
            setBorder(new CompoundBorder(new LineBorder(DesignUI.blue), new EmptyBorder(10,5,10,5)));
        }
    }
}
