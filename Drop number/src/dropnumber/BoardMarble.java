
package dropnumber;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.geom.Rectangle2D;

public class BoardMarble implements Marble {    
    protected static double marbleWidth;
    protected static double marbleHeight;
    protected int value;
    protected int fntsz;
    protected Color color;

    protected BoardMarble() {
        super();
    }
   
    public static double getMarbleWidth() {
        return marbleWidth;
    }

    public static double getMarbleHeight() {
        return marbleHeight;
    }
    
    public static void setMarbleWidth(double w) {
        BoardMarble.marbleWidth = w;
    }

    public static void setMarbleHeight(double h) {
        BoardMarble.marbleHeight = h;
    }
    
    @Override
    public int getValue() {
        return value;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public int getFntsz() {
        return fntsz;
    }

    @Override
    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void setFntsz(int fntsz) {
        this.fntsz = fntsz;
    }
    
    @Override
    public void setMarble(int value) {
        Marble mb = BoardMarbleFactory.getMarble(value);
        this.value = value;
        this.color = mb.getColor();
        this.fntsz = mb.getFntsz();
    }
    
    @Override
    public void draw(Graphics2D g2d, double x, double y) {
        g2d.setColor(color);
        double dx = (1.0 - MARBLE_SIZE) * marbleWidth / 2.0;
        double dy = (1.0 - MARBLE_SIZE) * marbleHeight / 2.0;
        g2d.fillRoundRect((int)(x + dx), (int)(y + dy), (int)(MARBLE_SIZE * marbleWidth),
            (int)(MARBLE_SIZE * marbleHeight), (int)(marbleWidth/7.0), (int)(marbleHeight/7.0));
        g2d.setColor(Color.WHITE);
        Font fnt = new Font(Font.DIALOG, Font.BOLD, fntsz);
        FontMetrics fm = g2d.getFontMetrics(fnt);
        g2d.setFont(fnt);
        double ws, hs;
        Rectangle2D rect = fm.getStringBounds("" + value, g2d);
        ws = rect.getWidth();
        hs = rect.getHeight();
        dx = (marbleWidth - ws) / 2.0;
        dy = (marbleHeight - hs) / 2.0;
        g2d.drawString("" + value, (int)(x + dx), (int)(y + dy + hs - fm.getDescent()));
    }
}

