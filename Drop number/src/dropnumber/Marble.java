
package dropnumber;

import java.awt.Color;
import java.awt.Graphics2D;

public interface Marble {
    static final double MARBLE_SIZE = 0.95;
    
    int getValue();
    Color getColor();
    int getFntsz();
    void setValue(int value);
    void setColor(Color color);
    void setFntsz(int fntsz);
    void setMarble(int value);
    void draw(Graphics2D g2d, double x, double y);
}

