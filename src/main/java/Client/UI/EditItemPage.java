package Client.UI;

import Client.UI.DesignUI.AddBtn;
import Client.UI.DesignUI.DiscardBtn;
import Client.UI.DesignUI.DeleteBtn;
import Client.UI.DesignUI.ConfirmBtn;
import Client.UI.Utility.MyListCellRenderer;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class EditItemPage extends JFrame implements ActionListener {
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

        selectPanel = new JPanel(new BorderLayout(5,5));
        selectPanel.add(selectLabel, BorderLayout.NORTH);
        selectPanel.add(invenComboBoxLeft, BorderLayout.CENTER);

        list = new JList();
        list.setFont(DesignUI.defaultFont);
        list.setCellRenderer(new MyListCellRenderer());
        scrollPane = new JScrollPane(list);

        addBtn = new AddBtn("Add New Item");

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
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == addBtn){

        }
        else if(e.getSource() == delBtn){

        }
        else if(e.getSource() == discardBtn){
            new MenuPage();
            dispose();
        }
        else if(e.getSource() == confirmBtn){
            new MenuPage();
            dispose();
        }
    }
}
