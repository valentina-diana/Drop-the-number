
package dropnumber;

// State Design Pattern (Behavioral)
public interface State {
    default void doAction(Controller cntrl) {
        double x, y, limit;
        int row, row0, row1, col;
        double mw = BoardMarble.getMarbleWidth();
        double mh = BoardMarble.getMarbleHeight();
        
        ActiveMarble am = (ActiveMarble)MarbleFactory.getMarble("ActiveMarble");
        DropNumber dn = DropNumber.getInstance();
        row0 = (int) (am.getY() / mh);
        y = am.getY() + am.getSpeed() * cntrl.getDt();
        limit = dn.getBoardHeight() - mh;
        
        if (y > limit) {
            y = limit;
        }
        row1 = (int) ((y + mh) / mh);
        col = (int) (am.getX() / mw);
        for (row = row0 + 1; row <= row1; row++) {
            if (dn.getCellValue(row, col) > 0) {
                y = (row - 1) * mh;
                cntrl.setState(new CheckSwallowState());
                break;
            }
        }
        if (y > limit - 1.0) {
            cntrl.setState(new CheckSwallowState());
        }
        am.setY(y);
    }
}

