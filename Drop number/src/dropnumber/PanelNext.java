
package dropnumber;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

public class PanelNext extends JPanel {
    private final static Color color2 = new Color(58, 55, 56);
    
    public PanelNext() {
        super();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
        int h = this.getHeight();
        int w = this.getWidth();
        g2d.setColor(color2);
        g2d.fillRect(0, 0, w, h);
        NextMarble nm = NextMarble.getInstance();
        if (nm.isVisible())
            nm.draw(g2d, 0, 0);
    }
}

