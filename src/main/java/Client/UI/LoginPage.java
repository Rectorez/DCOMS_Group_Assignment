package Client.UI;

import AccountPackage.AccountType;
import Client.UI.Compound.PanelRound;
import Client.UI.Compound.PanelRoundBorder;
import Server.AccountInterface;
import Server.GUIInterface;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;

import static Client.Main.*;

public class LoginPage extends UnicastRemoteObject implements ActionListener, MouseListener, GUIInterface {

    JFrame frame = new JFrame();
    JPanel rootPanel, leftPanel, rightPanel;
    JLabel logo;
    PanelRoundBorder loginCard;
    PanelRound loginCardContent;

    JLabel loginLabel, accountTypeLabel, usernameLabel, passwordLabel;
    JTextField usernameTF;
    JPasswordField passwordTF;
    JButton loginButton;
    JLabel incorrectLabel, registerLabel;
    JComboBox<String> accountTypeBox;

    public LoginPage() throws RemoteException {
        frame.setSize(1280, 720);
        frame.setTitle("Login Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        logo = new JLabel("Logo here");
        logo.setHorizontalAlignment(SwingConstants.CENTER);

        leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(logo, BorderLayout.CENTER);

        loginLabel = new JLabel("Login");
        loginLabel.setFont(DesignUI.bigFont);
        loginLabel.setBorder(new EmptyBorder(10,0,10,0));
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);

        accountTypeLabel = new JLabel("Account Type:");
        accountTypeLabel.setFont(DesignUI.defaultFontBold);
        accountTypeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(DesignUI.defaultFontBold);
        usernameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(DesignUI.defaultFontBold);
        passwordLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        CompoundBorder b = new CompoundBorder(new LineBorder(Color.black, 2), new EmptyBorder(2,2,2,2));

        usernameTF = new JTextField();
        usernameTF.setFont(DesignUI.defaultFont);
        usernameTF.setBorder(b);
        passwordTF = new JPasswordField();
        passwordTF.setFont(DesignUI.defaultFont);
        passwordTF.setBorder(b);

        accountTypeBox = new JComboBox<>();
        accountTypeBox.setFont(DesignUI.defaultFont);
        for (AccountType accountType : Arrays.stream(AccountType.values()).filter(t -> !t.equals(AccountType.ALL)).toList())
            accountTypeBox.addItem(accountType.toString());

        incorrectLabel = new JLabel("Incorrect username or password");
        incorrectLabel.setFont(DesignUI.tooltipFont);
        incorrectLabel.setForeground(DesignUI.red);
        incorrectLabel.setHorizontalAlignment(SwingConstants.CENTER);
        incorrectLabel.setVisible(false);

        loginButton = new JButton("Login");
        loginButton.setFont(DesignUI.defaultFont);
        loginButton.setBackground(Color.white);
        loginButton.setBorder(new CompoundBorder(new LineBorder(Color.black), new EmptyBorder(5,5,5,5)));
        loginButton.addActionListener(this);

        registerLabel = new JLabel("Click here to register");
        registerLabel.setFont(DesignUI.defaultFont);
        registerLabel.setForeground(DesignUI.blue);
        registerLabel.setBorder(new EmptyBorder(10,0,10,0));
        registerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        registerLabel.addMouseListener(this);

        loginCardContent = new PanelRound(new GridBagLayout());
        loginCardContent.setRoundAll(50);
        loginCardContent.setBorder(new EmptyBorder(5,5,5,5));
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5,5,5,5);
        c.weightx = 1;
        c.weighty = 1;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.BOTH;

        loginCardContent.add(loginLabel, c);

        c.gridy = 4;
        loginCardContent.add(incorrectLabel, c);
        c.gridy = 5;
        loginCardContent.add(loginButton, c);
        c.gridy = 6;
        loginCardContent.add(registerLabel, c);

        c.weightx = 0.45;
        c.gridwidth = 1;
        c.gridy = 1;
        loginCardContent.add(accountTypeLabel, c);
        c.gridy = 2;
        loginCardContent.add(usernameLabel, c);
        c.gridy = 3;
        loginCardContent.add(passwordLabel, c);

        c.weightx = 0.55;
        c.gridx = 1;
        c.gridy = 1;
        loginCardContent.add(accountTypeBox, c);
        c.gridy = 2;
        loginCardContent.add(usernameTF, c);
        c.gridy = 3;
        loginCardContent.add(passwordTF, c);

        loginCard = new PanelRoundBorder(loginCardContent, DesignUI.blue);

        rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(Box.createRigidArea(new Dimension(0, (int)(frame.getHeight() * 0.2))), BorderLayout.NORTH);
        rightPanel.add(Box.createRigidArea(new Dimension(0, (int)(frame.getHeight() * 0.2))), BorderLayout.SOUTH);
        rightPanel.add(Box.createRigidArea(new Dimension((int)(frame.getHeight() * 0.1), 0)), BorderLayout.EAST);
        rightPanel.add(Box.createRigidArea(new Dimension((int)(frame.getHeight() * 0.1), 0)), BorderLayout.WEST);
        rightPanel.add(loginCard, BorderLayout.CENTER);

        rootPanel = new JPanel(new GridBagLayout());
        GridBagConstraints rootC = new GridBagConstraints();
        rootC.weightx = 0.45;
        rootC.weighty = 1;
        rootC.fill = GridBagConstraints.BOTH;

        rootPanel.add(leftPanel, rootC);
        rootC.weightx = 0.55;
        rootPanel.add(rightPanel, rootC);

        frame.add(rootPanel);
        //frame.setVisible(true);
        frame.validate();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == loginButton){
            try {
                AccountType selectedAccountType = AccountType.valueOf(accountTypeBox.getSelectedItem() == null ?
                        AccountType.ALL.toString() : accountTypeBox.getSelectedItem().toString());
                if (AccountInterface.Login(selectedAccountType, usernameTF.getText(), new String(passwordTF.getPassword()))){
                    incorrectLabel.setVisible(false);
                    new MenuPage();
                    frame.dispose();
                }
                else{
                    incorrectLabel.setVisible(true);
                }
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        new RegisterPage();
        frame.dispose();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void displayGUI() throws RemoteException {
        frame.setVisible(true);
    }
}
