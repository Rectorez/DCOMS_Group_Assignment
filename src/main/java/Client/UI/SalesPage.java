package Client.UI;

import Client.UI.DesignUI.AddBtn;
import Client.UI.DesignUI.ConfirmBtn;
import Client.UI.DesignUI.DeleteBtn;
import Client.UI.DesignUI.DiscardBtn;
import Client.UI.Utility.MyListCellRenderer;
import Client.UI.Utility.MyTableCellRenderer;
import InventoryPackage.Inventory;
import InventoryPackage.Invoice;
import Server.InvoiceInterface;

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
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.RoundingMode;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import static Client.Main.*;

public class SalesPage extends JFrame implements ActionListener, ListSelectionListener {

    JPanel rootPanel, contentPanel, leftPanel, rightPanel, totalPanel, rightContentPanel, buttonPanel;
    JList list;
    JTable table;
    JScrollPane scrollPaneLeft, scrollPaneRight;
    AddBtn addBtn;
    DiscardBtn discardBtn;
    JLabel titleLabel, invoiceLabel, totalLabel, totalValueLabel;
    DefaultTableModel model = new DefaultTableModel();
    String[] columnNames = {"ID", "Inventory ID", "Name", "Quantity", "Price"};
    DecimalFormat df = new DecimalFormat("0.00");

    public SalesPage(){
        setSize(1280, 720);
        setTitle("Sales");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                new MenuPage();
            }
        });

        titleLabel = new JLabel("Record Sales");
        titleLabel.setFont(DesignUI.titleFont);

        list = new JList();
        list.setFont(DesignUI.defaultFont);
        list.setCellRenderer(new MyListCellRenderer());
        list.addListSelectionListener(this);
        scrollPaneLeft = new JScrollPane();
        scrollPaneLeft.setViewportView(list);

        addBtn = new AddBtn();
        addBtn.addActionListener(this);

        leftPanel = new JPanel(new BorderLayout(5, 10)){
            @Override
            public Dimension getPreferredSize() {
                return new Dimension();
            }
        };
        leftPanel.add(scrollPaneLeft);
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
        table.setRowHeight(DesignUI.defaultFont.getSize() + 15);
        table.setModel(model);
        table.getTableHeader().setPreferredSize(
                new Dimension(table.getTableHeader().getPreferredSize().width, table.getTableHeader().getPreferredSize().height + 15));
        table.setDefaultRenderer(Object.class, new MyTableCellRenderer());
        table.setFillsViewportHeight(true);

        scrollPaneRight = new JScrollPane(table);

        totalLabel = new JLabel("Total (RM):");
        totalLabel.setFont(DesignUI.defaultFontBold);
        totalLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        totalValueLabel = new JLabel("RM0.00");
        totalValueLabel.setFont(DesignUI.defaultFontBold);
        totalValueLabel.setHorizontalAlignment(SwingConstants.RIGHT);


        totalPanel = new JPanel(new GridLayout(1,2, 10, 5));
        totalPanel.add(totalLabel);
        totalPanel.add(totalValueLabel);

        rightContentPanel = new JPanel(new BorderLayout(5, 10));
        rightContentPanel.add(invoiceLabel, BorderLayout.NORTH);
        rightContentPanel.add(scrollPaneRight, BorderLayout.CENTER);
        rightContentPanel.add(totalPanel, BorderLayout.SOUTH);


        discardBtn = new DiscardBtn("Back");
        discardBtn.addActionListener(this);


        buttonPanel = new JPanel(new GridLayout(1, 1, 20, 5));
        buttonPanel.add(discardBtn);

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
        add(rootPanel);

        updateList();
        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == addBtn){
            final JDialog frame = new JDialog(this, "New Sales", true);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            try {
                frame.getContentPane().add(new NewSalesPage());
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
            frame.setSize(1280, 720);
            frame.setVisible(true);
        }

        else if(e.getSource() == discardBtn){
            new MenuPage();
            dispose();
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        //TODO copy update table code from newSalesPage
        //Use InvoiceInterface instead of InvoiceHandler when accessing server item (eg. line 176 or 3rd line in 'updateList' function below)
        if (!e.getValueIsAdjusting()) {
            model.setRowCount(0);
            ArrayList<String> values;
            String key;
            Invoice selectedInvoice = (Invoice) list.getSelectedValue();
            if(selectedInvoice != null) {
                for(int j = 0; j < selectedInvoice.getSoldItemList().size(); j++) {
                    key = selectedInvoice.getSoldItemList().keySet().toArray()[j].toString();
                    values = selectedInvoice.getSoldItemList().get(key);
                    model.addRow(new Object[]{key, values.get(0), values.get(1), values.get(2), values.get(3)});
                }
            }
        }
    }

    public void updateList(){
        try{
            DefaultListModel model = new DefaultListModel();
            InvoiceInterface.GetInvoices().forEach(x-> model.addElement(x));
            list.setModel(model);
        }
        catch (RemoteException ex){
            System.out.println("REMOTE EXCEPTION");
        }
    }
}
