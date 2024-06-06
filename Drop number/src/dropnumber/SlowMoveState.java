
package dropnumber;

public class SlowMoveState implements State {
    private int cnt = 0;
    
    @Override
    public void doAction(Controller cntrl) {
        State.super.doAction(cntrl);
        cnt++;
        if (cnt == 1) {
            GameForm.getActiveGameForm().getPanelNext().repaint();
        }
    }
}

