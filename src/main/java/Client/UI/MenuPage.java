package Client.UI;

import AccountPackage.AdminAccount;
import Client.UI.Compound.TilePanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.nio.file.Path;
import java.rmi.RemoteException;

import static Client.Main.currentAccount;

public class MenuPage extends JFrame implements MouseListener, ComponentListener {
    JPanel rootPanel, bigTilePanel, topTilePanel, lowTilePanel;
    TilePanel manageInven, viewItem, editItem, sales, logout;
    JLabel userLabel;

    public MenuPage(){
        setSize(1280, 720);
        setTitle("Menu");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    new LoginPage().displayGUI();
                } catch (RemoteException remoteException) {
                    remoteException.printStackTrace();
                }
            }
        });
        String username = currentAccount == null ? "user" : currentAccount.GetUsername();;
        userLabel = new JLabel("Hello, " + username);
        userLabel.setFont(DesignUI.bigFont);
        userLabel.setBorder(new EmptyBorder(5, 5, 10, 5));

        manageInven = new TilePanel(new ImageIcon(getFilePath(NameConstant.inven)), "Manage Inventory List", DesignUI.red);
        viewItem = new TilePanel(new ImageIcon(getFilePath(NameConstant.view)), "View Items", DesignUI.green);
        editItem = new TilePanel(new ImageIcon(getFilePath(NameConstant.edit)), "Edit Items", DesignUI.orange);
        sales = new TilePanel(new ImageIcon(getFilePath(NameConstant.sales)), "Record Sales", DesignUI.blue);
        logout = new TilePanel(new ImageIcon(getFilePath(NameConstant.logout)), "Logout", DesignUI.pink);

        manageInven.addMouseListener(this);
        viewItem.addMouseListener(this);
        editItem.addMouseListener(this);
        sales.addMouseListener(this);
        logout.addMouseListener(this);

        topTilePanel = new JPanel(new GridLayout(1, 3, 30, 10));
        topTilePanel.add(manageInven);
        topTilePanel.add(viewItem);
        topTilePanel.add(editItem);

        lowTilePanel = new JPanel(new GridLayout(1, 2, 30, 10));
        lowTilePanel.add(sales);
        lowTilePanel.add(logout);

        bigTilePanel = new JPanel(new GridLayout(2, 1, 30, 10));
        bigTilePanel.setBorder(new EmptyBorder(10,20,10,20));
        bigTilePanel.add(topTilePanel);
        bigTilePanel.add(lowTilePanel);

        rootPanel = new JPanel(new BorderLayout(5, 10));
        rootPanel.setBorder(new EmptyBorder(20,20,20,20));
        rootPanel.add(userLabel, BorderLayout.NORTH);
        rootPanel.add(bigTilePanel, BorderLayout.CENTER);
        rootPanel.addComponentListener(this);

        add(rootPanel, BorderLayout.CENTER);

        setVisible(true);

        int padding = (topTilePanel.getWidth() / 6);
        lowTilePanel.setBorder(new EmptyBorder(0, padding, 0, padding));

        validate();

    }

    private String getFilePath(NameConstant e){
        return Path.of(System.getProperty("user.dir"), getFileName(e) + ".png").toString();
    }

    private String getFileName(NameConstant e){
        return switch (e) {
            case edit -> "Edit";
            case view -> "View";
            case inven -> "Inventories";
            case sales -> "Sales";
            case logout -> "Logout";
            default -> "";
        };
    }

    @Override
    public void componentResized(ComponentEvent e) {
        manageInven.resizeIcon();
        viewItem.resizeIcon();
        editItem.resizeIcon();
        sales.resizeIcon();
        logout.resizeIcon();
    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == manageInven){
            new InventoryPage();
            dispose();
        }
        else if(e.getSource() == viewItem){
            new ViewItemPage();
            dispose();

        }
        else if(e.getSource() == editItem){
            if(currentAccount != null && currentAccount.getClass().equals(AdminAccount.class)){
                JOptionPane.showMessageDialog(this, "This function is not available for admin account",
                        "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            new EditItemPage();
            dispose();

        }
        else if(e.getSource() == sales){
            if(currentAccount != null && currentAccount.getClass().equals(AdminAccount.class)){
                JOptionPane.showMessageDialog(this, "This function is not available for admin account",
                        "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            new SalesPage();
            dispose();
        }
        else if(e.getSource() == logout){
            try {
                new LoginPage().displayGUI();
            } catch (RemoteException remoteException) {
                remoteException.printStackTrace();
            }
            dispose();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
