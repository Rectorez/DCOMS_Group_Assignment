package Client.UI;

import Client.Main;
import Client.UI.DesignUI.AddBtn;
import Client.UI.DesignUI.DiscardBtn;
import Client.UI.DesignUI.DeleteBtn;
import Client.UI.DesignUI.ConfirmBtn;
import Client.UI.Utility.MyListCellRenderer;
import InventoryPackage.Inventory;
import InventoryPackage.InventoryHandler;
import InventoryPackage.Item;
import Server.InventoryInterface;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;
import java.util.Arrays;

import static Client.Main.*;
public class EditItemPage extends JFrame implements ActionListener, ListSelectionListener {
    JLabel titleLabel;
    JPanel rootPanel, contentPanel, leftPanel, rightPanel, selectPanel, fieldPanel, buttonPanel;
    JList list;
    JScrollPane scrollPane;
    JLabel selectLabel, idLabel, invenLabel, nameLabel, priceLabel, costLabel, storedLabel, soldLabel;
    JTextField idTF, nameTF, priceTF, costTF, storedTF, soldTF;
    JComboBox invenComboBoxLeft, invenComboBoxRight;
    AddBtn addBtn;
    DeleteBtn delBtn;
    DiscardBtn discardBtn;
    ConfirmBtn confirmBtn;

    public  EditItemPage(){
        setSize(1280, 720);
        setTitle("Edit Items");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                new MenuPage();
            }
        });

        titleLabel = new JLabel("Edit Items");
        titleLabel.setFont(DesignUI.titleFont);

        selectLabel = new JLabel("Select Inventory");
        selectLabel.setFont(DesignUI.defaultFont);

        invenComboBoxLeft = new JComboBox();
        invenComboBoxLeft.setFont(DesignUI.defaultFont);
        invenComboBoxLeft.addActionListener(this);
        invenComboBoxLeft.setRenderer(new MyListCellRenderer());

        selectPanel = new JPanel(new BorderLayout(5,5));
        selectPanel.add(selectLabel, BorderLayout.NORTH);
        selectPanel.add(invenComboBoxLeft, BorderLayout.CENTER);

        list = new JList();
        list.setFont(DesignUI.defaultFont);
        list.setCellRenderer(new MyListCellRenderer());
        list.addListSelectionListener(this);
        scrollPane = new JScrollPane(list);

        addBtn = new AddBtn("Add New Item");
        addBtn.addActionListener(this);

        leftPanel = new JPanel(new BorderLayout(5, 10)){
            @Override
            public Dimension getPreferredSize() {
                return new Dimension();
            }
        };
        leftPanel.add(selectPanel, BorderLayout.NORTH);
        leftPanel.add(scrollPane, BorderLayout.CENTER);
        leftPanel.add(addBtn, BorderLayout.SOUTH);

        idLabel = new JLabel("ID");
        idLabel.setFont(DesignUI.defaultFont);
        idLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        invenLabel = new JLabel("Inventory");
        invenLabel.setFont(DesignUI.defaultFont);
        invenLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        nameLabel = new JLabel("Name");
        nameLabel.setFont(DesignUI.defaultFont);
        nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        priceLabel = new JLabel("Price");
        priceLabel.setFont(DesignUI.defaultFont);
        priceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        costLabel = new JLabel("Cost");
        costLabel.setFont(DesignUI.defaultFont);
        costLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        storedLabel = new JLabel("Stored Quantity");
        storedLabel.setFont(DesignUI.defaultFont);
        storedLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        soldLabel = new JLabel("Sold Quantity");
        soldLabel.setFont(DesignUI.defaultFont);
        soldLabel.setHorizontalAlignment(SwingConstants.RIGHT);


        Border b = new CompoundBorder(new LineBorder(Color.black), new EmptyBorder(10,5,10,5));
        idTF = new JTextField();
        idTF.setFont(DesignUI.defaultFont);
        idTF.setEnabled(false);
        idTF.setBorder(b);
        invenComboBoxRight = new JComboBox();
        invenComboBoxRight.setFont(DesignUI.defaultFont);
        invenComboBoxRight.setRenderer(new MyListCellRenderer());
        nameTF = new JTextField();
        nameTF.setFont(DesignUI.defaultFont);
        nameTF.setBorder(b);
        priceTF = new JTextField();
        priceTF.setFont(DesignUI.defaultFont);
        priceTF.setBorder(b);
        costTF = new JTextField();
        costTF.setFont(DesignUI.defaultFont);
        costTF.setBorder(b);
        storedTF = new JTextField();
        storedTF.setFont(DesignUI.defaultFont);
        storedTF.setBorder(b);
        soldTF = new JTextField();
        soldTF.setFont(DesignUI.defaultFont);
        soldTF.setBorder(b);

        fieldPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 0.35;
        c.weighty = 1;
        c.insets = new Insets(10,15,10,5);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;

        fieldPanel.add(idLabel, c);
        c.gridy = 1;
        fieldPanel.add(invenLabel, c);
        c.gridy = 2;
        fieldPanel.add(nameLabel, c);
        c.gridy = 3;
        fieldPanel.add(priceLabel, c);
        c.gridy = 4;
        fieldPanel.add(costLabel, c);
        c.gridy = 5;
        fieldPanel.add(storedLabel, c);
        c.gridy = 6;
        fieldPanel.add(soldLabel, c);

        c.weightx = 0.65;
        c.gridx = 1;
        c.gridy = 0;

        fieldPanel.add(idTF, c);
        c.gridy = 1;
        fieldPanel.add(invenComboBoxRight, c);
        c.gridy = 2;
        fieldPanel.add(nameTF, c);
        c.gridy = 3;
        fieldPanel.add(priceTF, c);
        c.gridy = 4;
        fieldPanel.add(costTF, c);
        c.gridy = 5;
        fieldPanel.add(storedTF, c);
        c.gridy = 6;
        fieldPanel.add(soldTF, c);

        delBtn = new DeleteBtn();
        delBtn.addActionListener(this);
        discardBtn = new DiscardBtn("Back to Menu");
        discardBtn.addActionListener(this);
        confirmBtn = new ConfirmBtn();
        confirmBtn.addActionListener(this);

        buttonPanel = new JPanel(new GridLayout(1, 3, 20, 5));
        buttonPanel.add(delBtn);
        buttonPanel.add(discardBtn);
        buttonPanel.add(confirmBtn);

        rightPanel = new JPanel(new BorderLayout(5, 20)){
            @Override
            public Dimension getPreferredSize() {
                return new Dimension();
            }
        };
        rightPanel.add(fieldPanel, BorderLayout.NORTH);
        rightPanel.add(buttonPanel, BorderLayout.SOUTH);

        contentPanel = new JPanel(new GridBagLayout());
        GridBagConstraints rootC = new GridBagConstraints();
        rootC.weightx = 0.4;
        rootC.weighty = 1;
        rootC.gridx = 0;
        rootC.insets = new Insets(0, 20, 0, 20);
        rootC.fill = GridBagConstraints.BOTH;
        contentPanel.add(leftPanel, rootC);
        rootC.gridx = 1;
        rootC.weightx = 0.6;
        contentPanel.add(rightPanel, rootC);

        rootPanel = new JPanel(new BorderLayout(5, 10));
        rootPanel.setBorder(new EmptyBorder(20,20,20,20));
        rootPanel.add(titleLabel, BorderLayout.NORTH);
        rootPanel.add(contentPanel, BorderLayout.CENTER);

        add(rootPanel);

        setupComboboxes();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            if(e.getSource() == addBtn){
                if(invenComboBoxLeft.getSelectedIndex() < 0) return;
                InventoryInterface.AddItem(InventoryInterface.GenerateItem(((Inventory)invenComboBoxLeft.getSelectedItem())
                        .GetID(), "New Item", 0, 0));
                updateComboboxes();
                list.setSelectedIndex(list.getLastVisibleIndex());
            }
            else if(e.getSource() == delBtn){
                if (list.getSelectedIndex() < 0) return;

                String password = JOptionPane.showInputDialog("Please enter password to verify deletion.");
                if(password.equals(currentAccount.GetPassword())){
                    var target = (Item)list.getSelectedValue();

                    InventoryInterface.DeleteItem(target);
                    updateComboboxes();
                }
                else{
                    JOptionPane.showMessageDialog(this, "Password does not match", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }
            else if(e.getSource() == discardBtn){
                new MenuPage();
                dispose();
            }
            else if(e.getSource() == confirmBtn){
                Item oldItem = (Item) list.getSelectedValue();
                Item newItem = InventoryInterface.GenerateItem(idTF.getText(),((Inventory)
                        invenComboBoxRight.getSelectedItem()).GetID(), nameTF.getText(),
                        Double.parseDouble(priceTF.getText()),  Double.parseDouble(costTF.getText()),
                        Integer.parseInt(storedTF.getText()), Integer.parseInt(soldTF.getText()));
                InventoryInterface.UpdateItem(oldItem, newItem);
                updateComboboxes();
            }
            else if(e.getSource() == invenComboBoxLeft){
                System.out.println("Updating list");
                updateItemList();
            }
        }
        catch (RemoteException ex){
            System.out.println("REMOTE EXCEPTION");
        }

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(list.getSelectedIndex() == -1) return;

        Item i = (Item) list.getSelectedValue();
        idTF.setText(i.GetID());
        nameTF.setText(i.GetName());
        priceTF.setText(String.valueOf(i.GetPrice()));
        costTF.setText(String.valueOf(i.GetCost()));
        storedTF.setText(String.valueOf(i.GetStoredQuantity()));
        soldTF.setText(String.valueOf(i.GetSoldQuantity()));

        try{
            var target = InventoryInterface.GetInventories().stream().filter(x -> x.GetID().equals(i.GetInventoryID())).findFirst().orElse(null);
            if(target == null) throw new IndexOutOfBoundsException(); //Inventory is not found (not likely to happen)
            invenComboBoxRight.setSelectedItem(target);
        }
        catch (RemoteException ex) {
            System.out.println("REMOTE EXCEPTION");
        }
        catch (IndexOutOfBoundsException exp){
            System.out.println("Invalid Inventory of item");
        }


    }

    private void setupComboboxes() {
        try{
            invenComboBoxLeft.removeAllItems();
            invenComboBoxRight.removeAllItems();
            InventoryInterface.GetInventories().stream().forEach(x ->{
                invenComboBoxLeft.addItem(x);
                invenComboBoxRight.addItem(x);
            });
        }
        catch(RemoteException e){
            System.out.println("REMOTE EXCEPTION");
        }
    }

    private void updateComboboxes(){
        var target = (Inventory)invenComboBoxLeft.getSelectedItem();
        setupComboboxes(); //Update current comboBox with latest data from server
        invenComboBoxLeft.setSelectedItem(target); //will trigger actionPerformed to call updateItemList
    }
    private void updateItemList(){
        if(invenComboBoxLeft.getSelectedIndex() == -1){
            return;
        }
        DefaultListModel model = new DefaultListModel();
        try{
            ((Inventory)invenComboBoxLeft.getSelectedItem()).GetItemList().forEach(x -> model.addElement(x));

            list.setModel(model);

            if(model.size() > 0) list.setSelectedIndex(0);
            else clearAllFields();;
        }
        catch (NullPointerException e){
            System.out.println("Inventory does not exist");
            list.setModel(model);
        }
    }

    private void clearAllFields(){
        idTF.setText("");
        nameTF.setText("");
        priceTF.setText("");
        costTF.setText("");
        storedTF.setText("");
        soldTF.setText("");
    }
}
