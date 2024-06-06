
package dropnumber;

public class StopState implements State {
    @Override
    public void doAction(Controller cntrl) {
        
        ActiveMarble am = (ActiveMarble)MarbleFactory.getMarble("ActiveMarble");
        if (am.getY() < 1.0) {
            GameForm.getActiveGameForm().stopGame();
            cntrl.setState(new EndState()); // end of game
        }
        else {
            double mw = BoardMarble.getMarbleWidth();
            double mh = BoardMarble.getMarbleHeight();
            
            NextMarble nm = (NextMarble)MarbleFactory.getMarble("NextMarble");
            am.setY(-mh);
            // last click column 
            am.setX(cntrl.getStartColumn() * mw);
            am.setVisible(true);
            am.setMarble(nm.getValue());
            // next marble value
            int val = cntrl.getNextMarble();
            nm.setMarble(val);
            cntrl.setState(new SlowMoveState());
        }
    }
}

