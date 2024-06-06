
package dropnumber;

public class EndState implements State {
    @Override
    public void doAction(Controller cntrl) {
        cntrl.stopTimer();
    }
}

