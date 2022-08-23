package Client.UI;

import Client.UI.DesignUI.*;
import Client.UI.Utility.MyListCellRenderer;
import InventoryPackage.*;
import Server.InventoryInterface;
import Server.InventoryServer;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.RoundingMode;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static Client.Main.*;
import static java.lang.Math.PI;
import static java.lang.Math.round;

public class NewSalesPage extends JPanel implements ActionListener, ListSelectionListener {
    JPanel rootPanel, contentPanel, leftPanel, quantityPanel, getQuantityPanel, invenPanel, itemPanel, rightPanel, totalPanel, rightContentPanel, buttonPanel, dialogPanel;
    JList list;
    JTable table;
    JScrollPane scrollPaneLeft, scrollPaneRight;
    AddBtn addBtn;
    DeleteBtn delBtn;
    DiscardBtn discardBtn;
    ConfirmBtn confirmBtn;
    BlueButton addQuantityBtn, minusQuantityBtn;
    JComboBox invenComboBox;
    JLabel titleLabel, invenLabel, invoiceLabel, totalLabel, totalValueLabel, quantityLabel, quantityValLabel;
    DefaultListModel itemListModel;
    int currentQty;
    int availableQty;
    int invenComboBoxSelected = 0;
    Item selectedItem = null;
    String[] columnNames = {"ID", "Inventory ID", "Name", "Quantity", "Price"};
    DefaultTableModel model = new DefaultTableModel(0, 0);
    DecimalFormat df = new DecimalFormat("0.00");

    public NewSalesPage() throws RemoteException {
        setLayout(new BorderLayout());

        titleLabel = new JLabel("New Sales");
        titleLabel.setFont(DesignUI.titleFont);

        invenLabel = new JLabel("Select Inventory");
        invenLabel.setFont(DesignUI.defaultFont);

        invenComboBox = new JComboBox(InventoryInterface.GetInventories().stream().map(i -> i.GetName()).toArray(String[]::new));
        invenComboBox.setFont(DesignUI.defaultFont);
        invenComboBox.setSelectedIndex(-1);
        invenComboBox.addActionListener(this);


        invenPanel = new JPanel(new BorderLayout(5, 5));
        invenPanel.add(invenLabel, BorderLayout.NORTH);
        invenPanel.add(invenComboBox, BorderLayout.CENTER);

        itemListModel = new DefaultListModel();
        list = new JList(itemListModel);
        list.setFont(DesignUI.defaultFont);
        list.setCellRenderer(new MyListCellRenderer());
        list.addListSelectionListener(this);
        scrollPaneLeft = new JScrollPane();
        scrollPaneLeft.setViewportView(list);

        quantityLabel = new JLabel("Quantity");
        quantityLabel.setFont(DesignUI.defaultFont);
        quantityValLabel = new JLabel("0");
        quantityValLabel.setFont(DesignUI.defaultFont);
        quantityValLabel.setHorizontalAlignment(SwingConstants.CENTER);
        quantityValLabel.setBorder(new LineBorder(Color.black));
        minusQuantityBtn = new BlueButton("-");
        minusQuantityBtn.setEnabled(false);
        minusQuantityBtn.addActionListener(this);
        addQuantityBtn = new BlueButton("+");
        addQuantityBtn.setEnabled(false);
        addQuantityBtn.addActionListener(this);

        getQuantityPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 0.25;
        c.weighty = 1;
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.BOTH;

        getQuantityPanel.add(minusQuantityBtn, c);
        c.gridx = 2;
        getQuantityPanel.add(addQuantityBtn, c);
        c.gridx = 1;
        c.weightx = 0.5;
        getQuantityPanel.add(quantityValLabel, c);

        quantityPanel = new JPanel(new GridLayout(1, 2, 10, 5));
        quantityPanel.add(quantityLabel);
        quantityPanel.add(getQuantityPanel);

        itemPanel = new JPanel(new BorderLayout(5, 10));
        itemPanel.add(scrollPaneLeft, BorderLayout.CENTER);
        itemPanel.add(quantityPanel, BorderLayout.SOUTH);

        addBtn = new AddBtn("Add to Invoice");
        addBtn.addActionListener(this);

        leftPanel = new JPanel(new BorderLayout(5, 10)){
            @Override
            public Dimension getPreferredSize() {
                return new Dimension();
            }
        };
        leftPanel.add(invenPanel, BorderLayout.NORTH);
        leftPanel.add(itemPanel);
        leftPanel.add(addBtn, BorderLayout.SOUTH);

        invoiceLabel = new JLabel("Invoice");
        invoiceLabel.setFont(DesignUI.defaultFontBold);

        df.setRoundingMode(RoundingMode.HALF_UP);
        model.setColumnIdentifiers(columnNames);
        model.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                double invoiceSum = 0;
                if(model.getRowCount() != 0) {
                    for(int i = 0; i < model.getRowCount(); i++) {
                        invoiceSum += Double.parseDouble(model.getValueAt(i, 4).toString());
                    }
                    totalValueLabel.setText(df.format(invoiceSum));
                } else {
                    totalValueLabel.setText("0.00");
                }
            }
        });
        table = new JTable(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.setFont(DesignUI.defaultFont);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setModel(model);
        table.setFillsViewportHeight(true);

        scrollPaneRight = new JScrollPane(table);

        totalLabel = new JLabel("Total (RM):");
        totalLabel.setFont(DesignUI.defaultFontBold);
        totalLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        totalValueLabel = new JLabel("0.00");
        totalValueLabel.setFont(DesignUI.defaultFontBold);
        totalValueLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        totalPanel = new JPanel(new GridLayout(1,2, 10, 5));
        totalPanel.add(totalLabel);
        totalPanel.add(totalValueLabel);

        rightContentPanel = new JPanel(new BorderLayout(5, 10));
        rightContentPanel.add(invoiceLabel, BorderLayout.NORTH);
        rightContentPanel.add(scrollPaneRight, BorderLayout.CENTER);
        rightContentPanel.add(totalPanel, BorderLayout.SOUTH);

        delBtn = new DeleteBtn("Remove Item");
        delBtn.addActionListener(this);
        discardBtn = new DiscardBtn();
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
        rightPanel.add(rightContentPanel, BorderLayout.CENTER);
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
        add(rootPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if(e.getSource() == addBtn){
                if(invenComboBox.getSelectedIndex() != -1) {
                    boolean duplicate = false;
                    currentQty = Integer.parseInt(quantityValLabel.getText());
                    selectedItem = InventoryInterface.GetInventories().stream()
                            .filter(i -> i.GetName().equals(invenComboBox.getSelectedItem()))
                            .findFirst().get().GetItemList().stream()
                            .filter(i -> i.GetName().equals(list.getSelectedValue()))
                            .findFirst().get();

                    if (selectedItem != null && currentQty != 0 && InventoryInterface.ExportItem(selectedItem, currentQty) && selectedItem.Export(currentQty)) {
                        if (model.getRowCount() != 0) {
                            for (int i = 0; i < model.getRowCount(); i++) {
                                if (model.getValueAt(i, 2).toString().equals(selectedItem.GetName())) {
                                    model.setValueAt(Integer.parseInt(model.getValueAt(i, 3).toString()) + currentQty, i, 3);
                                    model.setValueAt(df.format(Double.parseDouble(model.getValueAt(i, 4).toString()) +
                                            (selectedItem.GetPrice() * currentQty)), i, 4);
                                    duplicate = true;
                                    break;
                                }
                            }
                        }
                        if (!duplicate) {
                            model.addRow(new Object[]{
                                    selectedItem.GetID(), selectedItem.GetInventoryID(),
                                    selectedItem.GetName(), currentQty,
                                    df.format(selectedItem.GetPrice() * currentQty)
                            });
                        }
                        System.out.println("Remaining from local: " + (selectedItem.GetStoredQuantity() - selectedItem.GetSoldQuantity()));
                        System.out.println("Remaining from inventoryList: " + (InventoryInterface.GetInventories().stream().filter(i -> i.GetName().equals(invenComboBox.getSelectedItem().toString())).findFirst().get().GetItemList().stream().filter(i -> i.GetName().equals(selectedItem.GetName())).findFirst().get().GetStoredQuantity() - InventoryInterface.GetInventories().stream().filter(i -> i.GetName().equals(invenComboBox.getSelectedItem().toString())).findFirst().get().GetItemList().stream().filter(i -> i.GetName().equals(selectedItem.GetName())).findFirst().get().GetSoldQuantity()));
                        System.out.println(selectedItem.GetSoldQuantity());


                    } else if (!InventoryInterface.ExportItem(selectedItem, currentQty)) {

                        JOptionPane.showMessageDialog(this, "Insufficient stock. Available quantity: " + (selectedItem.GetStoredQuantity() - selectedItem.GetSoldQuantity()));
                        System.out.println("Remaining from inventoryList: " + (InventoryInterface.GetInventories().stream().filter(i -> i.GetName().equals(invenComboBox.getSelectedItem().toString())).findFirst().get().GetItemList().stream().filter(i -> i.GetName().equals(selectedItem.GetName())).findFirst().get().GetStoredQuantity() - InventoryInterface.GetInventories().stream().filter(i -> i.GetName().equals(invenComboBox.getSelectedItem().toString())).findFirst().get().GetItemList().stream().filter(i -> i.GetName().equals(selectedItem.GetName())).findFirst().get().GetSoldQuantity()));

                    } else {
                        JOptionPane.showMessageDialog(this, "Please select item and quantity.");
                    }
                }
            }

            else if(e.getSource() == delBtn){
                if(table.getSelectedRowCount() != 1) {
                    JOptionPane.showMessageDialog(this, "Please select an item to remove.");
                } else {
                    int i = table.getSelectedRow();
                    int row = table.convertRowIndexToModel(i);
                    int itemQty = Integer.parseInt(model.getValueAt(i, 3).toString());
                    String itemName = model.getValueAt(i, 2).toString();
                    String itemInvenID = model.getValueAt(i, 1).toString();
                    selectedItem = InventoryInterface.GetInventories().stream()
                            .filter(l -> l.GetID().equals(itemInvenID))
                            .findFirst().get().GetItemList().stream()
                            .filter(l -> l.GetName().equals(itemName))
                            .findFirst().get();
                    InventoryInterface.RemoveItem(selectedItem, itemQty);
                    selectedItem.Remove(itemQty);
                    model.removeRow(row);

                }
            }

            else if(e.getSource() == discardBtn){
                for(int j = 0; j < model.getRowCount(); j++) {
                    int itemQty = Integer.parseInt(model.getValueAt(j, 3).toString());
                    String itemName = model.getValueAt(j, 2).toString();
                    String itemInvenID = model.getValueAt(j, 1).toString();
                    selectedItem = InventoryInterface.GetInventories().stream()
                            .filter(l -> l.GetID().equals(itemInvenID))
                            .findFirst().get().GetItemList().stream()
                            .filter(l -> l.GetName().equals(itemName))
                            .findFirst().get();
                    InventoryInterface.RemoveItem(selectedItem, itemQty);
                    selectedItem.Remove(itemQty);
                }
                JDialog d = (JDialog) SwingUtilities.getRoot(this);
                d.dispose();
            }

            else if(e.getSource() == confirmBtn){
                if(model.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(this, "Nothing to confirm.");
                } else {
                    HashMap<String, ArrayList<String>> soldItems = new HashMap<>();
                    ArrayList<String> soldItemDetails = new ArrayList<>();
                    for(int i = 0; i < model.getRowCount(); i++) {
                        soldItemDetails.add(model.getValueAt(i, 1).toString());
                        soldItemDetails.add(model.getValueAt(i, 2).toString());
                        soldItemDetails.add(model.getValueAt(i, 3).toString());
                        soldItemDetails.add(model.getValueAt(i, 4).toString());
                        soldItems.put(model.getValueAt(i, 0).toString(), soldItemDetails);
                        soldItemDetails.clear();
                    }
                    InvoiceHandler.AddInvoice(new Invoice(soldItems, Double.parseDouble(totalValueLabel.getText()), LocalDateTime.now()));
                    //Serialize to Invoices.ser
                    //Serialize updated items in memory to Inventories.ser
                }
                JDialog d = (JDialog) SwingUtilities.getRoot(this);
                d.dispose();
            }

            else if(e.getSource() == invenComboBox) {
                if (itemListModel.size() == 0 || invenComboBox.getSelectedIndex() != invenComboBoxSelected ) {
                    itemListModel.removeAllElements();
                    InventoryInterface.GetInventories().stream()
                            .filter(i -> i.GetName().equals(invenComboBox.getSelectedItem()))
                            .findFirst().get().GetItemList().stream()
                            .map(j -> j.GetName()).forEach(itemListModel::addElement);
                    invenComboBoxSelected = invenComboBox.getSelectedIndex();
                }

            }

            else if(e.getSource() == addQuantityBtn) {
                selectedItem = InventoryInterface.GetInventories().stream()
                        .filter(i -> i.GetName().equals(invenComboBox.getSelectedItem()))
                        .findFirst().get().GetItemList().stream()
                        .filter(i -> i.GetName().equals(list.getSelectedValue()))
                        .findFirst().get();
                availableQty = selectedItem.GetStoredQuantity() - selectedItem.GetSoldQuantity();
                if(currentQty != availableQty) quantityValLabel.setText(String.valueOf(currentQty += 1));
            }

            else if(e.getSource() == minusQuantityBtn) {
                if(currentQty != 0) quantityValLabel.setText(String.valueOf(currentQty -= 1));
            }
        } catch(RemoteException ex) {
            System.out.println("REMOTE EXCEPTION");
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        try {
            if(!e.getValueIsAdjusting()) {
                invenComboBoxSelected = invenComboBox.getSelectedIndex();
                minusQuantityBtn.setEnabled(true);
                addQuantityBtn.setEnabled(true);
                currentQty = 0;
                quantityValLabel.setText(String.valueOf(currentQty));
                if (itemListModel.size() != 0) {
                    selectedItem = InventoryInterface.GetInventories().stream()
                            .filter(i -> i.GetName().equals(invenComboBox.getSelectedItem()))
                            .findFirst().get().GetItemList().stream()
                            .filter(i -> i.GetName().equals(list.getSelectedValue()))
                            .findFirst().orElse(null);
                }
            }
        } catch(RemoteException ex) {
            System.out.println("REMOTE EXCEPTION");
        }
    }
}
