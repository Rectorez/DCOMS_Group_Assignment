package Client.UI;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import Client.UI.DesignUI.*;

public class RegisterPage extends JFrame implements ActionListener {

    JPanel rootPanel, fieldPanel, buttonPanel;
    JLabel title;
    JLabel fNameLabel, lNameLabel, usernameLabel, ICLabel, passLabel, confirmPassLabel;
    JTextField fNameTF, lNameTF, usernameTF, ICTF;
    JPasswordField passTF, confirmPassTF;
    RegisterBtn regBtn;
    DiscardBtn discardBtn;

    public RegisterPage(){
        setSize(843, 520);
        setTitle("Register Page");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        title = new JLabel("Register");
        title.setFont(DesignUI.titleFont);

        fNameLabel = new JLabel("First Name");
        fNameLabel.setFont(DesignUI.defaultFontBold);
        fNameLabel.setBorder(new EmptyBorder(20,0,5,0));
        lNameLabel = new JLabel("Last Name");
        lNameLabel.setFont(DesignUI.defaultFontBold);
        lNameLabel.setBorder(new EmptyBorder(20,0,5,0));
        usernameLabel = new JLabel("Username");
        usernameLabel.setFont(DesignUI.defaultFontBold);
        usernameLabel.setBorder(new EmptyBorder(20,0,5,0));
        ICLabel = new JLabel("IC/Passport No.");
        ICLabel.setFont(DesignUI.defaultFontBold);
        ICLabel.setBorder(new EmptyBorder(20,0,5,0));
        passLabel = new JLabel("Password");
        passLabel.setFont(DesignUI.defaultFontBold);
        passLabel.setBorder(new EmptyBorder(20,0,5,0));
        confirmPassLabel = new JLabel("Confirm Password");
        confirmPassLabel.setFont(DesignUI.defaultFontBold);
        confirmPassLabel.setBorder(new EmptyBorder(20,0,5,0));

        fNameTF = new JTextField();
        fNameTF.setFont(DesignUI.defaultFontBold);
        lNameTF = new JTextField();
        lNameTF.setFont(DesignUI.defaultFontBold);
        usernameTF = new JTextField();
        usernameTF.setFont(DesignUI.defaultFontBold);
        ICTF = new JTextField();
        ICTF.setFont(DesignUI.defaultFontBold);
        passTF = new JPasswordField();
        passTF.setFont(DesignUI.defaultFontBold);
        confirmPassTF = new JPasswordField();
        confirmPassTF.setFont(DesignUI.defaultFontBold);

        discardBtn = new DiscardBtn("Discard");
        discardBtn.addActionListener(this);

        regBtn = new RegisterBtn("Register");
        regBtn.addActionListener(this);

        fieldPanel = new JPanel(new GridLayout(6, 2, 20, 5));
        fieldPanel.add(fNameLabel);
        fieldPanel.add(lNameLabel);
        fieldPanel.add(fNameTF);
        fieldPanel.add(lNameTF);
        fieldPanel.add(usernameLabel);
        fieldPanel.add(ICLabel);
        fieldPanel.add(usernameTF);
        fieldPanel.add(ICTF);
        fieldPanel.add(passLabel);
        fieldPanel.add(confirmPassLabel);
        fieldPanel.add(passTF);
        fieldPanel.add(confirmPassTF);

        buttonPanel = new JPanel(new GridLayout(1, 2, 20, 5));
        buttonPanel.add(discardBtn);
        buttonPanel.add(regBtn);

        rootPanel = new JPanel(new GridBagLayout());
        rootPanel.setBorder(new EmptyBorder(20,20,20,20));
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.NORTH;
        c.gridy = 0;
        rootPanel.add(title, c);
        c.gridy = 1;
        c.anchor = GridBagConstraints.CENTER;
        rootPanel.add(fieldPanel, c);
        c.gridy = 2;
        c.anchor = GridBagConstraints.SOUTH;
        rootPanel.add(buttonPanel, c);

        add(rootPanel);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == discardBtn){
            try {
                new LoginPage().displayGUI();
            } catch (RemoteException remoteException) {
                remoteException.printStackTrace();
            }
            dispose();
        }
        else if (e.getSource() == regBtn) {
            try {
                new LoginPage().displayGUI();
            } catch (RemoteException remoteException) {
                remoteException.printStackTrace();
            }
            dispose();
        }
    }
}
