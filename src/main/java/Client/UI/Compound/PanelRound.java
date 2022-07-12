package Client.UI.Compound;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

public class PanelRound extends JPanel {
    private int topLeft = 0, topRight = 0, btmLeft = 0, btmRight = 0;

    public PanelRound(){
        setOpaque(false);
    }

    public PanelRound(LayoutManager l){
        setOpaque(false);
        setLayout(l);
    }

    public void setRoundTopLeft(int topLeft) {
        this.topLeft = topLeft;
        repaint();
    }

    public void setRoundTopRight(int topRight) {
        this.topRight = topRight;
        repaint();
    }

    public void setRoundBtmLeft(int btmLeft) {
        this.btmLeft = btmLeft;
        repaint();
    }

    public void setRoundBtmRight(int btmRight) {
        this.btmRight = btmRight;
        repaint();
    }

    public int getTopLeft(){
        return topLeft;
    }

    public int getTopRight(){
        return topRight;
    }

    public int getBottomLeft(){
        return btmLeft;
    }

    public int getBottomRight(){
        return btmRight;
    }

    public void setRoundAll(int r){
        topLeft = r;
        topRight = r;
        btmLeft = r;
        btmRight = r;
        repaint();
    }



    @Override
    protected void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        Area a = new Area(createRoundTopLeft());
        if(topRight > 0) a.intersect(new Area(createRoundTopRight()));
        if(btmLeft > 0) a.intersect(new Area(createRoundBottomLeft()));
        if(btmRight > 0) a.intersect(new Area(createRoundBottomRight()));
        g2.fill(a);
        g2.dispose();
        super.paintComponent(g);
    }

    private Shape createRoundTopLeft(){
        int width = getWidth();
        int height = getHeight();
        int roundX = Math.min(width, topLeft);
        int roundY = Math.min(height, topLeft);

        Area a = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundX, roundY));
        a.add(new Area(new Rectangle2D.Double(roundX/2, 0, width - roundX/2, height)));
        a.add(new Area(new Rectangle2D.Double(0, roundY/2, width, height - roundY/2)));
        return a;
    }

    private Shape createRoundTopRight(){
        int width = getWidth();
        int height = getHeight();
        int roundX = Math.min(width, topRight);
        int roundY = Math.min(height, topRight);

        Area a = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundX, roundY));
        a.add(new Area(new Rectangle2D.Double(0, 0, width - roundX/2, height)));
        a.add(new Area(new Rectangle2D.Double(0, roundY/2, width, height - roundY/2)));
        return a;
    }

    private Shape createRoundBottomLeft(){
        int width = getWidth();
        int height = getHeight();
        int roundX = Math.min(width, btmLeft);
        int roundY = Math.min(height, btmLeft);

        Area a = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundX, roundY));
        a.add(new Area(new Rectangle2D.Double(roundX/2, 0, width - roundX/2, height)));
        a.add(new Area(new Rectangle2D.Double(0, 0, width, height - roundY/2)));
        return a;
    }

    private Shape createRoundBottomRight(){
        int width = getWidth();
        int height = getHeight();
        int roundX = Math.min(width, btmRight);
        int roundY = Math.min(height, btmRight);

        Area a = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundX, roundY));
        a.add(new Area(new Rectangle2D.Double(0, 0, width - roundX/2, height)));
        a.add(new Area(new Rectangle2D.Double(0, 0, width, height - roundY/2)));
        return a;
    }
}
