
package dropnumber;

// MVC Controller Design Pattern (Game Actions (logic))

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.Timer;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;

public class Controller {    
    class TimerHandler implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            if (bPause) {
                return;
            }
            long timeCurrent = System.currentTimeMillis();
            dt = timeCurrent - timeOld;
            timeOld = timeCurrent;
            state.doAction(instance);
            //
            PanelBoard pnlb = GameForm.getActiveGameForm().getPanelBoard();
            pnlb.repaint();
            //
            PanelNext pnln = GameForm.getActiveGameForm().getPanelNext();
            pnln.repaint();
        }
    }

    private final static int TIME_INTERVAL = 100;
    
    // unique object
    private static Controller instance = null;
    
  
    
    private State state = null;
    private Queue<Cell> fallingCells = new LinkedList<>();
    private int startColumn = 0;
    private Random rnd = new Random();
    private Timer timer;
    private boolean bPause = false;
    private long timeOld = 0L;
    private TimerHandler timerHandler;
    private ArrayList<Integer> historyValues = new ArrayList();
    private long dt;
    
    private Controller() {
        super();
        timerHandler = new TimerHandler();
        timer = new Timer(TIME_INTERVAL, timerHandler);
        rnd.setSeed(100);
    }
    
    // Singleton Design Pattern (Creational)
    // Get the only object available
    public static Controller getInstance() {
        if (instance == null)
            instance = new Controller();
        return instance;
    }
    
    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }
    
    public long getDt() {
        return dt;
    }
    
    public void resetHistoryValues() {
        historyValues.clear();
    }
    
    public int getNextMarble() {
        int ix = rnd.nextInt(historyValues.size());
        int val = historyValues.get(ix);
        historyValues.add(val);
        return val;
    }
            
    public void timerAction() {
        timerHandler.actionPerformed(null);
    }
    
    public void stopTimer() {
        timer.stop();
    }
    
    public int getStartColumn() {
        return startColumn;
    }
    
    public void addFallingCell(Cell cell) {
        fallingCells.add(cell);
    }
    
    public Cell getFallingCell() {
        if (fallingCells.size() > 0)
            return fallingCells.remove();
        return null;
    }
    
    public void setStartColumn(int col) {
        startColumn = col;
    }
    
    public void addHistoryValue(int val) {
        historyValues.add(val);
    }

    public void startGame() {
        startColumn = rnd.nextInt(DropNumber.BOARD_WIDTH);
        ActiveMarble am = ActiveMarble.getInstance();
        NextMarble nm = NextMarble.getInstance(); 
        am.setMarble(2);
        nm.setMarble(2);
        nm.setVisible(true);
        historyValues.add(2);
        am.setX(startColumn * BoardMarble.getMarbleWidth());
        am.setY(-BoardMarble.getMarbleHeight());
        am.setVisible(true);
        SwallowedMarble.getInstance().setVisible(false);
        bPause = false;
        setState(new SlowMoveState());
        timeOld = System.currentTimeMillis();
        timer.start();
    }
    
    public void stopGame() {
        bPause = false;
        timer.stop();
        NextMarble.getInstance().setVisible(false);
    }
    
    public void pauseGame() {
        bPause = true;
    }
    
    public void runGame() {
        bPause = false;
        timeOld = System.currentTimeMillis();
    }
}

