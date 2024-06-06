
package dropnumber;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;

public class BoardMarbleFactory {
    private static final int DEFAULT_VALUE = 1048576;
    
    // Flyweight Design Pattern (Structural)
    private static final Map<Integer, Color> COLORS_MAP = new HashMap();
    private static Map<Integer, Marble> marbleMap = new HashMap();
    private static Map<Integer, Integer> fontSizeMap = new HashMap();
    
    static {   
        COLORS_MAP.put(2, new Color(221, 98, 193));
        COLORS_MAP.put(4, new Color(107, 216, 82));
        COLORS_MAP.put(8, new Color(68, 208, 208));
        COLORS_MAP.put(16, new Color(61, 143, 219));
        COLORS_MAP.put(32, new Color(233, 108, 84));
        COLORS_MAP.put(64, new Color(151, 134, 254));
        COLORS_MAP.put(128, new Color(161, 149, 142));
        COLORS_MAP.put(256, new Color(250, 180, 65));
        COLORS_MAP.put(512, new Color(246, 96, 124));
        COLORS_MAP.put(1024, new Color(122, 50, 244));
        COLORS_MAP.put(2048, new Color(24, 131, 38));
        COLORS_MAP.put(4096, new Color(193, 36, 147));
        COLORS_MAP.put(8192, new Color(72, 56, 214));
        COLORS_MAP.put(16384, new Color(92, 180, 80));
        COLORS_MAP.put(32768, new Color(175, 131, 48));
        COLORS_MAP.put(65536, new Color(96, 96, 128));
        COLORS_MAP.put(131072, new Color(74, 149, 79));
        COLORS_MAP.put(262144, new Color(192, 107, 31));
        COLORS_MAP.put(524288, new Color(43, 181, 181));
        COLORS_MAP.put(1048576, new Color(184, 218, 46));
    }
    
    public static Color getColor(int value) {
        if (COLORS_MAP.containsKey(value)) {
            return COLORS_MAP.get(value);
        }
        return COLORS_MAP.get(DEFAULT_VALUE);
    }
    
    public static void calculateFontSizes() {
        // 1 digit
        double fszmax = 0.5 * BoardMarble.getMarbleHeight();
        fontSizeMap.put(1, (int)Math.round(fszmax));
        // 7 digits
        double fszmin = 0.2 * BoardMarble.getMarbleHeight();
        fontSizeMap.put(7, (int)Math.round(fszmin));
        double fsz;
        for (int nc = 2; nc <= 6; nc++) {
            fsz = fszmin + (7.0 - nc) * (fszmax - fszmin) / 6.0;
            fontSizeMap.put(nc, (int)Math.round(fsz));
        }    
    }
            
    private static int noDigits(int value) {
        if (value == 0)
            return 1;
        else if (value == -1)
            return 2;
        int nd = 0;
        while (value > 0) {
            nd++;
            value /= 10;
        }
        return nd;
    }
    
    private static int getFontSize(int value) {
        int nd = noDigits(value);
        return fontSizeMap.get(nd);
    }
    
    // Flyweight Design Pattern (Structural)
    public static Marble getMarble(int value) {
        if (marbleMap.containsKey(value)) {
            return marbleMap.get(value);
        }
        // Presupunem ca constructia unui nou BoardMarble este costisitoare
        BoardMarble mrb;
        mrb = new BoardMarble();
        mrb.setValue(value);
        mrb.setColor(BoardMarbleFactory.getColor(value));
        mrb.setFntsz(getFontSize(value));
        marbleMap.put(value, mrb);
        return mrb;
    }
}

