
package dropnumber;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

// View
public class PanelPickColumn extends JPanel {
    private final static Color color1 = new Color(50, 48, 48);
    private final static Color color2 = new Color(58, 55, 56);
    private final static Color color3 = new Color(81, 78, 79);
    
    public PanelPickColumn() {
        super();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
        int i;
        int h = this.getHeight();
        int w = this.getWidth();
        double dw = w / (double)DropNumber.BOARD_WIDTH;
        for (i = 0; i < DropNumber.BOARD_WIDTH; i++) {
            if (i % 2 == 0)
                g2d.setColor(color1);
            else
               g2d.setColor(color2);
            g2d.fillRect((int)(i * dw), 0, (int)dw + 1, h);
        }        
        int xPoints[] = new int[3];
        int yPoints[] = new int[3];
        yPoints[0] = h / 4;
        yPoints[1] = 3 * h / 4;
        yPoints[2] = h / 4;
        g2d.setColor(color3);
        for (i = 0; i < DropNumber.BOARD_WIDTH; i++) {
            xPoints[0] = (int)(i * dw) + (int)dw / 4;
            xPoints[1] = (int)(i * dw) + (int)dw / 2;
            xPoints[2] = (int)(i * dw) + 3 * (int)dw / 4;
            g2d.fillPolygon(xPoints, yPoints, 3);
        }
    }
}


