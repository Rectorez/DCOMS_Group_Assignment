package Client.UI;

import Client.UI.Compound.PanelRound;
import Client.UI.Compound.PanelRoundBorder;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Locale;

public class LoginPage extends JFrame implements ActionListener, MouseListener {

    JPanel rootPanel, leftPanel, rightPanel;
    JLabel logo;
    PanelRoundBorder loginCard;
    PanelRound loginCardContent;

    JLabel loginLabel, usernameLabel, passwordLabel;
    JTextField usernameTF, passwordTF;
    JButton loginButton;
    JLabel incorrectLabel, registerLabel;

    public LoginPage(){
        setSize(1280, 720);
        setTitle("Login Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        logo = new JLabel("Logo here");
        logo.setHorizontalAlignment(SwingConstants.CENTER);

        leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(logo, BorderLayout.CENTER);

        loginLabel = new JLabel("Login");
        loginLabel.setFont(DesignUI.bigFont);
        loginLabel.setBorder(new EmptyBorder(10,0,10,0));
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);

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
        passwordTF = new JTextField();
        passwordTF.setFont(DesignUI.defaultFont);
        passwordTF.setBorder(b);

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

        c.gridy = 3;
        loginCardContent.add(incorrectLabel, c);
        c.gridy = 4;
        loginCardContent.add(loginButton, c);
        c.gridy = 5;
        loginCardContent.add(registerLabel, c);

        c.gridy = 1;
        c.weightx = 0.45;
        c.gridwidth = 1;
        loginCardContent.add(usernameLabel, c);
        c.gridy = 2;
        loginCardContent.add(passwordLabel, c);

        c.weightx = 0.55;
        c.gridx = 1;
        c.gridy = 1;
        loginCardContent.add(usernameTF, c);
        c.gridy = 2;
        loginCardContent.add(passwordTF, c);

        loginCard = new PanelRoundBorder(loginCardContent, DesignUI.blue);

        rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(Box.createRigidArea(new Dimension(0, (int)(this.getHeight() * 0.2))), BorderLayout.NORTH);
        rightPanel.add(Box.createRigidArea(new Dimension(0, (int)(this.getHeight() * 0.2))), BorderLayout.SOUTH);
        rightPanel.add(Box.createRigidArea(new Dimension((int)(this.getHeight() * 0.1), 0)), BorderLayout.EAST);
        rightPanel.add(Box.createRigidArea(new Dimension((int)(this.getHeight() * 0.1), 0)), BorderLayout.WEST);
        rightPanel.add(loginCard, BorderLayout.CENTER);

        rootPanel = new JPanel(new GridBagLayout());
        GridBagConstraints rootC = new GridBagConstraints();
        rootC.weightx = 0.45;
        rootC.weighty = 1;
        rootC.fill = GridBagConstraints.BOTH;

        rootPanel.add(leftPanel, rootC);
        rootC.weightx = 0.55;
        rootPanel.add(rightPanel, rootC);

        add(rootPanel);
        setVisible(true);
        validate();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == loginButton){
            if(usernameTF.getText().equals("F".toLowerCase())){
                incorrectLabel.setVisible(true);
                return;
            }
            incorrectLabel.setVisible(false);
            new MenuPage();
            dispose();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        new RegisterPage();
        dispose();
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
}
