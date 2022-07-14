package Client.UI;

import Client.UI.DesignUI.AddBtn;
import Client.UI.DesignUI.ConfirmBtn;
import Client.UI.DesignUI.DeleteBtn;
import Client.UI.DesignUI.DiscardBtn;
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

public class InventoryPage extends JFrame implements ActionListener {

    JPanel rootPanel, contentPanel, leftPanel, rightPanel, fieldPanel, buttonPanel;
    JList list;
    JScrollPane scrollPane;
    AddBtn newInvenBtn;
    DeleteBtn delBtn;
    DiscardBtn discardBtn;
    ConfirmBtn confirmBtn;
    JLabel titleLabel, idLabel, nameLabel, descLabel,countLabel, countValLabel, statusLabel, statusValLabel, createdLabel, createdValLabel, delLabel, delValLabel;
    JTextField idTF, nameTF;
    JTextArea descTA;

    public InventoryPage(){
        setSize(1280, 720);
        setTitle("Manage Inventory List");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                new MenuPage();
            }
        });

        titleLabel = new JLabel("Inventories");
        titleLabel.setFont(DesignUI.titleFont);

        list = new JList(new Integer[]{1,2,3,4,5,6,7,8,9});
        list.setFont(DesignUI.defaultFont);
        list.setCellRenderer(new MyListCellRenderer());
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(list);

        newInvenBtn = new AddBtn();
        newInvenBtn.addActionListener(this);

        leftPanel = new JPanel(new BorderLayout(5, 10)){
            @Override
            public Dimension getPreferredSize() {
                return new Dimension();
            }
        };
        leftPanel.add(scrollPane);
        leftPanel.add(newInvenBtn, BorderLayout.SOUTH);

        idLabel = new JLabel("ID:");
        idLabel.setFont(DesignUI.defaultFont);
        idLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        nameLabel = new JLabel("Name:");
        nameLabel.setFont(DesignUI.defaultFont);
        nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        descLabel = new JLabel("Description:");
        descLabel.setFont(DesignUI.defaultFont);
        descLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        countLabel = new JLabel("Item Count:");
        countLabel.setFont(DesignUI.defaultFont);
        countLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        statusLabel = new JLabel("Status:");
        statusLabel.setFont(DesignUI.defaultFont);
        statusLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        createdLabel = new JLabel("Created Date:");
        createdLabel.setFont(DesignUI.defaultFont);
        createdLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        delLabel = new JLabel("Deleted Date:");
        delLabel.setFont(DesignUI.defaultFont);
        delLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        Border b = new CompoundBorder(new LineBorder(Color.black), new EmptyBorder(10,5,10,5));
        idTF = new JTextField();
        idTF.setFont(DesignUI.defaultFont);
        idTF.setBorder(b);
        idTF.setEnabled(false);
        nameTF = new JTextField();
        nameTF.setFont(DesignUI.defaultFont);
        nameTF.setBorder(b);
        descTA = new JTextArea();
        descTA.setFont(DesignUI.defaultFont);
        descTA.setBorder(b);
        descTA.setLineWrap(true);
        countValLabel = new JLabel("current count");
        countValLabel.setFont(DesignUI.defaultFont);
        statusValLabel = new JLabel("current status");
        statusValLabel.setFont(DesignUI.defaultFont);
        createdValLabel = new JLabel("created date");
        createdValLabel.setFont(DesignUI.defaultFont);
        delValLabel = new JLabel("deleted date");
        delValLabel.setFont(DesignUI.defaultFont);

        delBtn = new DeleteBtn();
        delBtn.addActionListener(this);
        discardBtn = new DiscardBtn();
        discardBtn.addActionListener(this);
        confirmBtn = new ConfirmBtn();
        confirmBtn.addActionListener(this);

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
        fieldPanel.add(nameLabel, c);
        c.gridy = 2;
        fieldPanel.add(descLabel, c);
        c.gridy = 3;
        fieldPanel.add(countLabel, c);
        c.gridy = 4;
        fieldPanel.add(statusLabel, c);
        c.gridy = 5;
        fieldPanel.add(createdLabel, c);
        c.gridy = 6;
        fieldPanel.add(delLabel, c);

        c.weightx = 0.65;
        c.gridx = 1;
        c.gridy = 0;

        fieldPanel.add(idTF, c);
        c.gridy = 1;
        fieldPanel.add(nameTF, c);
        c.gridy = 2;
        fieldPanel.add(descTA, c);
        c.gridy = 3;
        fieldPanel.add(countValLabel, c);
        c.gridy = 4;
        fieldPanel.add(statusValLabel, c);
        c.gridy = 5;
        fieldPanel.add(createdValLabel, c);
        c.gridy = 6;
        fieldPanel.add(delValLabel, c);

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
        if(e.getSource() == newInvenBtn){

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
