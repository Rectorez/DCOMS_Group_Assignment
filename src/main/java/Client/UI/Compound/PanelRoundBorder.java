package Client.UI.Compound;

import javax.swing.*;
import java.awt.*;

public class PanelRoundBorder extends PanelRound{

    PanelRound contentPanel;

    public PanelRoundBorder(PanelRound comp){
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        contentPanel = comp;
        setup();
    }

    public PanelRoundBorder(PanelRound comp, Color c){
        setLayout(new BorderLayout());
        setBackground(c);

        contentPanel = comp;
        setup();
    }
    public PanelRoundBorder(PanelRound comp, Color c, int thickness){
        setLayout(new BorderLayout());
        setBackground(c);

        contentPanel = comp;
        setup(thickness);
    }

    private void setup(){
        setupRoundness(2);
        add(Box.createRigidArea(new Dimension(10, 2)), BorderLayout.NORTH);
        add(Box.createRigidArea(new Dimension(10, 2)), BorderLayout.SOUTH);
        add(Box.createRigidArea(new Dimension(2, 10)), BorderLayout.WEST);
        add(Box.createRigidArea(new Dimension(2, 10)), BorderLayout.EAST);
        add(contentPanel, BorderLayout.CENTER);
    }

    private void setup(int thickness){
        setupRoundness(thickness);
        add(Box.createRigidArea(new Dimension(10, thickness)), BorderLayout.NORTH);
        add(Box.createRigidArea(new Dimension(10, thickness)), BorderLayout.SOUTH);
        add(Box.createRigidArea(new Dimension(thickness, 10)), BorderLayout.WEST);
        add(Box.createRigidArea(new Dimension(thickness, 10)), BorderLayout.EAST);
        add(contentPanel, BorderLayout.CENTER);
    }

    private void setupRoundness(int thickness){
        setRoundTopLeft(contentPanel.getTopLeft() + thickness);
        setRoundTopRight(contentPanel.getTopRight() + thickness);
        setRoundBtmLeft(contentPanel.getBottomLeft() + thickness);
        setRoundBtmRight(contentPanel.getBottomRight() + thickness);
    }
    //getPreferredSize return 0,0 so size fully determined by weight in GridBagLayout
    @Override
    public Dimension getPreferredSize() {
        return new Dimension();
    }
}
