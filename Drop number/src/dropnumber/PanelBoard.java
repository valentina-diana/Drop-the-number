
package dropnumber;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

// MVC View
public class PanelBoard extends JPanel {
    private final static Color color1 = new Color(50, 48, 48);
    private final static Color color2 = new Color(58, 55, 56);
    
    private boolean firstPaint = true;
    private boolean debug = false;
    
    public PanelBoard() {
        super();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        int i, j;
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        if (firstPaint) {
            int w = this.getWidth();
            int h = this.getHeight();
            double mw = (double)w / DropNumber.BOARD_WIDTH;
            DropNumber.getInstance().setBoardWidth(w);
            DropNumber.getInstance().setBoardHeight(h);
            double mh = (double)h / DropNumber.BOARD_HEIGHT;
            BoardMarble.setMarbleWidth(mw);
            BoardMarble.setMarbleHeight(mh);
            double left = 0.0, right;
            for (i = 0; i < DropNumber.BOARD_WIDTH; i++) {
                right = left + mw;
                DropNumber.getInstance().setColumnLeft(i, left);
                DropNumber.getInstance().setColumnRight(i, right);
                left = right;
            }
            BoardMarbleFactory.calculateFontSizes();
            DropNumber.setColumnHeight(h);
            DropNumber.setColumnWidth(w);
            firstPaint = false;
        }
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
        DropNumber dn = DropNumber.getInstance();
        for (i = 0; i < DropNumber.BOARD_WIDTH; i++) {
            if (i % 2 == 0)
                g2d.setColor(color1);
            else
                g2d.setColor(color2);
            g2d.fillRect((int)dn.getColumnLeft(i), 0,
                (int)(dn.getColumnRight(i) - dn.getColumnLeft(i)) + 1,
                dn.getBoardHeight());
        }
        for (i = 0; i < DropNumber.BOARD_HEIGHT; i++) {
            for (j = 0; j < DropNumber.BOARD_WIDTH; j++) {
                int value = DropNumber.getInstance().getCellValue(i, j);
                if (value > 0 || debug) {
                    Marble bm = BoardMarbleFactory.getMarble(value);
                    bm.draw(g2d, (int)(j * BoardMarble.getMarbleWidth()),
                            (int)(i * BoardMarble.getMarbleHeight()));
                }
            }
        }
        SwallowedMarble sm = SwallowedMarble.getInstance();
        if (sm.isVisible()) {
            sm.draw(g2d, (int)sm.getX(), (int)(sm.getY()));
        }
        ActiveMarble am = ActiveMarble.getInstance();
        if (am.isVisible()) {
            am.draw(g2d, (int)(am.getX()), (int)(am.getY()));
        }
    }
}

