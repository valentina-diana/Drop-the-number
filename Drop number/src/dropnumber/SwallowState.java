
package dropnumber;

public class SwallowState implements State {
    @Override
    public void doAction(Controller cntrl) {
        double x, y, limit;
        int row, col;
        double mw = BoardMarble.getMarbleWidth();
        double mh = BoardMarble.getMarbleHeight();
        
        ActiveMarble am = (ActiveMarble) MarbleFactory.getMarble("ActiveMarble");
        SwallowedMarble sm = (SwallowedMarble) MarbleFactory.getMarble("SwallowedMarble");
        DropNumber dn = DropNumber.getInstance();
        row = (int) ((am.getY() + 1.0) / mh);
        col = (int) ((am.getX() + 1.0) / mw);
        
        if (sm.getDir() == SwallowedMarble.Direction.UP) {
            y = sm.getY() - sm.getSpeed() * cntrl.getDt();
            limit = am.getY();
            if (y <= limit) {
                y = limit;
                sm.setVisible(false);
                cntrl.setState(new MediumMoveState());
            }
            sm.setY(y);
        } else if (sm.getDir() == SwallowedMarble.Direction.LEFT) {
            x = sm.getX() - sm.getSpeed() * cntrl.getDt();
            limit = am.getX();
            if (x <= limit) {
                x = limit;
                sm.setVisible(false);
                row--;
                col++;
                while (dn.getCellValue(row, col) > -1) {
                    cntrl.addFallingCell(dn.getCell(row, col).copy());
                    row--;
                }
                cntrl.setState(new CheckSwallowState());
            }
            sm.setX(x);
        }  else {
            assert (sm.getDir() == SwallowedMarble.Direction.RIGHT);
            x = sm.getX() + sm.getSpeed() * cntrl.getDt();
            limit = am.getX();
            if (x >= limit) {
                x = limit;
                sm.setVisible(false);
                row--;
                col--;
                while (dn.getCellValue(row, col) > -1) {
                    cntrl.addFallingCell(dn.getCell(row, col).copy());
                    row--;
                }
                cntrl.setState(new CheckSwallowState());
            }
            sm.setX(x);
        } 
    }
}

