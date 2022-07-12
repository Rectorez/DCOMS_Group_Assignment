package Client;

import Client.UI.DesignUI;
import Client.UI.DesignUI.DiscardBtn;
import Client.UI.LoginPage;
import Client.UI.MenuPage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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

        selectPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        selectPanel.add(selectLabel);
        selectPanel.add(invenComboBox);

        table = new JTable();
        table.setFont(DesignUI.defaultFont);
        table.setFillsViewportHeight(true);

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
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backBtn){
            new MenuPage();
            dispose();
        }
    }
}
