
package dropnumber;

public class CheckSwallowState implements State {
    @Override
    public void doAction(Controller cntrl) {   
        double x, y;
        int row, col;
        double mw = BoardMarble.getMarbleWidth();
        double mh = BoardMarble.getMarbleHeight();
      
        ActiveMarble am = (ActiveMarble) MarbleFactory.getMarble("ActiveMarble");
        SwallowedMarble sm = (SwallowedMarble) MarbleFactory.getMarble("SwallowedMarble");
        DropNumber dn = DropNumber.getInstance();
        // check neighbouts
        x = am.getX();
        y = am.getY();
        row = (int) ((y + 1.0) / mh);
        col = (int) ((x + 1.0) / mw);
        int value = am.getValue(), newval;
        if (dn.getCellValue(row + 1, col) == value
                || (dn.getCellValue(row, col - 1) == value && dn.hasSupport(row, col - 1))
                || (dn.getCellValue(row, col + 1) == value && dn.hasSupport(row, col + 1))) {
            newval = 2 * value;
            cntrl.addHistoryValue(newval);
            am.setMarble(newval);
            dn.addToScore(newval);
            // afisare
            GameForm.getActiveGameForm().setScore(dn.getScore());
            if (dn.updateBestScore()) // afisare
            {
                GameForm.getActiveGameForm().setBestScore(dn.getBestScore());
            }
            sm.setMarble(value);
            sm.setVisible(true);
            if (dn.getCellValue(row + 1, col) == value) {
                // down
                dn.setCellValue(row + 1, col, -1);
                sm.setX(x);
                sm.setY(y + mh);
                sm.setDir(SwallowedMarble.Direction.UP);
            }
            else if (dn.getCellValue(row, col - 1) == value) {
                // left
                dn.setCellValue(row, col - 1, -1);
                sm.setX(x - mw);
                sm.setY(y);
                sm.setDir(SwallowedMarble.Direction.RIGHT);
            }
            else if (dn.getCellValue(row, col + 1) == value) {
                // right
                dn.setCellValue(row, col + 1, -1);
                sm.setX(x + mw);
                sm.setY(y);
                sm.setDir(SwallowedMarble.Direction.LEFT);
            }
            cntrl.setState(new SwallowState());
        }
        else {
            dn.setCellValue(row, col, am.getValue());
            Cell cell = cntrl.getFallingCell();
            if (cell != null) {
                am.setMarble(cell.value);
                am.setX(cell.column * mw);
                am.setY(cell.row * mh);
                dn.setCellValue(cell.row, cell.column, -1);
                cntrl.setState(new MediumMoveState());
            } 
            else {
                am.setVisible(false);
                cntrl.setState(new StopState());
            }
        }
    }
}

