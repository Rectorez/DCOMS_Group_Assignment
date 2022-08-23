package Client.UI;

import Client.Main;
import Client.UI.DesignUI;
import Client.UI.DesignUI.DiscardBtn;
import Client.UI.LoginPage;
import Client.UI.MenuPage;
import Client.UI.Utility.MyListCellRenderer;
import Client.UI.Utility.MyTableCellRenderer;
import InventoryPackage.Inventory;
import InventoryPackage.InventoryHandler;
import InventoryPackage.Item;
import Server.InventoryInterface;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Vector;

import static Client.Main.*;

public class ViewItemPage extends JFrame implements ActionListener {
    JPanel rootPanel, selectPanel, contentPanel;
    JLabel titleLabel, selectLabel;
    JComboBox invenComboBox;
    JTable table;
    JScrollPane scrollPane;
    DiscardBtn backBtn;

    public ViewItemPage(){
        setSize(1280, 720);
        setTitle("View Items");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                new MenuPage();
            }
        });

        titleLabel = new JLabel("View Items");
        titleLabel.setFont(DesignUI.titleFont);

        selectLabel = new JLabel("Select Inventory");
        selectLabel.setFont(DesignUI.defaultFont);

        invenComboBox = new JComboBox();
        invenComboBox.setFont(DesignUI.defaultFont);
        invenComboBox.setBorder(new EmptyBorder(10,5,10,5));
        invenComboBox.addActionListener(this);
        invenComboBox.setRenderer(new MyListCellRenderer());

        selectPanel = new JPanel(new BorderLayout( 5, 5));
        selectPanel.add(selectLabel, BorderLayout.NORTH);
        selectPanel.add(invenComboBox, BorderLayout.CENTER);

        String col[] = {"ID", "Name", "Price", "Cost", "Stored Quantity", "Sold Quantity"};
        DefaultTableModel tableModel = new DefaultTableModel(col, 0);

        table = new JTable(tableModel){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table.setFont(DesignUI.defaultFont);
        table.setFillsViewportHeight(true);
        table.setRowHeight(DesignUI.defaultFont.getSize() + 15);
        table.getTableHeader().setPreferredSize(
                new Dimension(table.getTableHeader().getPreferredSize().width, table.getTableHeader().getPreferredSize().height + 15));
        table.setDefaultRenderer(Object.class, new MyTableCellRenderer());

        scrollPane = new JScrollPane(table);

        contentPanel = new JPanel(new BorderLayout(5, 10));
        contentPanel.add(selectPanel, BorderLayout.NORTH);
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        backBtn = new DiscardBtn("Back");
        backBtn.addActionListener(this);

        rootPanel = new JPanel(new BorderLayout(5, 10));
        rootPanel.setBorder(new EmptyBorder(20,20,20,20));
        rootPanel.add(titleLabel, BorderLayout.NORTH);
        rootPanel.add(contentPanel, BorderLayout.CENTER);
        rootPanel.add(backBtn, BorderLayout.SOUTH);

        add(rootPanel);
        setupComboboxes();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backBtn){
            new MenuPage();
            dispose();

        }
        if(e.getSource() == invenComboBox){
            try {
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.setRowCount(0);
                InventoryInterface.GetItems((Inventory) invenComboBox.getSelectedItem()).stream().
                        forEach(x -> model.addRow(new Object[]{x.GetID(), x.GetName(), x.GetPrice(), x.GetCost(),
                                x.GetStoredQuantity(), x.GetSoldQuantity()}));
                System.out.println(model.getRowCount());
                table.setModel(model);
            }
            catch (RemoteException ex){
                System.out.println("REMOTE EXCEPTION");;
            }
        }
    }
    private void setupComboboxes() {
        try{
            invenComboBox.removeAllItems();
            Main.InventoryInterface.GetInventories().stream().forEach(x ->{
                invenComboBox.addItem(x);
            });
        }
        catch(RemoteException e){
            System.out.println("REMOTE EXCEPTION");
        }
    }
}
