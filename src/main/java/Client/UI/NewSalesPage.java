package Client.UI;

import Client.UI.DesignUI.*;
import Client.UI.Utility.MyListCellRenderer;
import InventoryPackage.Inventory;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class NewSalesPage extends JPanel implements ActionListener {

    JPanel rootPanel, contentPanel, leftPanel, quantityPanel, getQuantityPanel, invenPanel, itemPanel, rightPanel, totalPanel, rightContentPanel, buttonPanel;
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

    public NewSalesPage(){
        setLayout(new BorderLayout());

        titleLabel = new JLabel("New Sales");
        titleLabel.setFont(DesignUI.titleFont);

        invenLabel = new JLabel("Select Inventory");
        invenLabel.setFont(DesignUI.defaultFont);

        invenComboBox = new JComboBox();
        invenComboBox.setFont(DesignUI.defaultFont);

        invenPanel = new JPanel(new BorderLayout(5, 5));
        invenPanel.add(invenLabel, BorderLayout.NORTH);
        invenPanel.add(invenComboBox, BorderLayout.CENTER);

        list = new JList();
        list.setFont(DesignUI.defaultFont);
        list.setCellRenderer(new MyListCellRenderer());
        scrollPaneLeft = new JScrollPane();
        scrollPaneLeft.setViewportView(list);

        quantityLabel = new JLabel("Quantity");
        quantityLabel.setFont(DesignUI.defaultFont);
        quantityValLabel = new JLabel("0");
        quantityValLabel.setFont(DesignUI.defaultFont);
        quantityValLabel.setHorizontalAlignment(SwingConstants.CENTER);
        quantityValLabel.setBorder(new LineBorder(Color.black));
        minusQuantityBtn = new BlueButton("-");
        minusQuantityBtn.addActionListener(this);
        addQuantityBtn = new BlueButton("+");
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

        table = new JTable();
        table.setFont(DesignUI.defaultFont);
        table.setFillsViewportHeight(true);

        scrollPaneRight = new JScrollPane(table);

        totalLabel = new JLabel("Total:");
        totalLabel.setFont(DesignUI.bigFont);
        totalLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        totalValueLabel = new JLabel("RM0.00");
        totalValueLabel.setFont(DesignUI.bigFont);
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
        if(e.getSource() == addBtn){

        }
        else if(e.getSource() == delBtn){

        }
        else if(e.getSource() == discardBtn){
            JDialog d = (JDialog) SwingUtilities.getRoot(this);
            d.dispose();
        }
        else if(e.getSource() == confirmBtn){
            JDialog d = (JDialog) SwingUtilities.getRoot(this);
            d.dispose();
        }

    }
}
