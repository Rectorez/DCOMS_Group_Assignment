package Client.UI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import AccountPackage.Account;
import AccountPackage.AdminAccount;
import AccountPackage.ExecutiveAccount;
import Client.UI.DesignUI.*;

import static Client.Main.*;

public class RegisterPage extends JFrame implements ActionListener {

    JPanel rootPanel, emailPanel, fieldPanel, buttonPanel;
    JLabel title;
    JLabel fNameLabel, lNameLabel, usernameLabel, ICLabel, passLabel, confirmPassLabel, emailLabel;
    JTextField fNameTF, lNameTF, usernameTF, ICTF, emailTF;
    JPasswordField passTF, confirmPassTF;
    RegisterBtn regBtn;
    DiscardBtn discardBtn;
    JComboBox roleBox;

    public RegisterPage(){
        setSize(843, 620);
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
        emailLabel = new JLabel("Email");
        emailLabel.setFont(DesignUI.defaultFontBold);
        emailLabel.setBorder(new EmptyBorder(20,0,5,0));

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
        emailTF = new JTextField();
        emailTF.setFont(DesignUI.defaultFontBold);

        discardBtn = new DiscardBtn("Discard");
        discardBtn.addActionListener(this);

        regBtn = new RegisterBtn("Register");
        regBtn.addActionListener(this);

        roleBox = new JComboBox(new UserRole[]{UserRole.Admin, UserRole.Executive});
        roleBox.setFont(DesignUI.defaultFont);
        roleBox.setBorder(new EmptyBorder(10,0,5,0));

        emailPanel = new JPanel(new GridLayout(2, 1, 0 ,5));
        emailPanel.add(emailLabel);
        emailPanel.add(emailTF);


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
        rootPanel.add(roleBox, c);
        c.gridy = 2;
        rootPanel.add(emailPanel, c);
        c.gridy = 3;
        c.anchor = GridBagConstraints.CENTER;
        rootPanel.add(fieldPanel, c);
        c.gridy = 4;
        c.anchor = GridBagConstraints.SOUTH;
        rootPanel.add(buttonPanel, c);

        add(rootPanel);
        setVisible(true);

    }

    private boolean checkEmpty(JComponent tf){
        if(tf instanceof JPasswordField) return ((JPasswordField)tf).getPassword() == null || new String(((JPasswordField)tf).getPassword()).isBlank();
        if(tf instanceof JTextField) return ((JTextField)tf).getText() == null || ((JTextField)tf).getText().isBlank();
        return false;
    }

    private boolean checkAllEmpty(){
        for(var x : rootPanel.getComponents()){
            if(x instanceof JPanel){
                for(var y : ((JPanel) x).getComponents()){
                    if(checkEmpty((JComponent) y)) return true;
                }
            }
            else{
                if(checkEmpty((JComponent) x)) return true;
            }
        }
        //Code above same as below, but dynamic, no need to change if add new components
//            if(checkEmpty(emailTF) || checkEmpty(fNameTF) || checkEmpty(lNameTF) || checkEmpty(usernameTF) ||
//                    checkEmpty(ICTF) || checkEmpty(passTF) || checkEmpty(confirmPassTF)) return;
        return false;
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

            if(checkAllEmpty()){
                JOptionPane.showMessageDialog(this, "Please fill in all the fields.", "Warning", JOptionPane.WARNING_MESSAGE);
                System.out.println("Incomplete credentials");
                return;
            }
            try {
                if(AccountInterface.HasExistingAccountPartial(usernameTF.getText())){
                    //Account exist
                    String IC = JOptionPane.showInputDialog("Username exist, please enter IC No. for verification");
                    Account acc = AccountInterface.GetAccount(usernameTF.getText(), new String(passTF.getPassword()));
                    if(acc.GetIC().equals(IC)){
                        //IC Match
                        System.out.println("IC Matched");
                        JOptionPane.showMessageDialog(this,
                                String.format("Account exist\nUsername: %s\nPassword: %s", acc.GetUsername(), acc.GetPassword()));
                        new LoginPage().displayGUI();
                        dispose();
                    }
                    else{
                        JOptionPane.showMessageDialog(this, "IC does not match", "Warning", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }
                else{
                    //Account does not exist, create new
                    System.out.println(AccountInterface.GetAccounts().size());
                    if(roleBox.getSelectedItem() == UserRole.Executive){
                        System.out.println("Creating new executive");
                        AccountInterface.Register(new ExecutiveAccount(ICTF.getText(), fNameTF.getText(),
                                lNameTF.getText(), usernameTF.getText(), emailTF.getText(), new String(passTF.getPassword())));

                    } else if (roleBox.getSelectedItem() == UserRole.Admin) {
                        System.out.println("Creating new admin");
                        AccountInterface.Register(new AdminAccount(ICTF.getText(), fNameTF.getText(),
                                lNameTF.getText(), usernameTF.getText(), emailTF.getText(), new String(passTF.getPassword())));
                    }
                    System.out.println("Account created. Username: " + usernameTF.getText());
                    AccountInterface.GetAccounts().forEach(System.out::println);
                    System.out.println("Account count: " + AccountInterface.GetAccounts().size());
                    new LoginPage().displayGUI();
                    dispose();
                }
            } catch (RemoteException remoteException) {
                remoteException.printStackTrace();
            }
        }
    }
}
