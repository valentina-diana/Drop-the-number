
package dropnumber;

public class ActiveMarble extends BoardMarble {    
    protected static final double SLOW_SPEED = 0.08;
    protected static final double MEDIUM_SPEED = 0.16;
    protected static final double FAST_SPEED = 1.00;
    
    protected double x;
    protected double y;
    protected boolean visible;
    protected double speed;
    
    // unique object
    private static ActiveMarble instance = null;
    
    protected ActiveMarble() {
        super();
        visible = false;
    }
    
    // Singleton Design Pattern (Creational)
    // Get the only object available
    public static ActiveMarble getInstance() {
        if (instance == null)
            // first time it is accessed
            instance = new ActiveMarble();
        return instance;
    }
    
    public boolean fastDrop(int x) {
        if (instance != null) {
            Controller ctrl = Controller.getInstance();
            State state = ctrl.getState();
            if (!(state instanceof SlowMoveState))
                return false;
            int col = (int) (x / marbleWidth);
            Controller.getInstance().setStartColumn(col);
            setX(col * marbleWidth);
            ctrl.setState(new FastMoveState());
            return true;
        } else {
            return false;
        }
    }
        
    public double getSpeed() {
        double speed;
        State state = Controller.getInstance().getState();
        if (state instanceof SlowMoveState)
            speed = SLOW_SPEED;
        else if (state instanceof MediumMoveState)
            speed = MEDIUM_SPEED;
        else if (state instanceof FastMoveState)
            speed = FAST_SPEED;
        else
            speed = 0.0;
        return speed;
    }
    
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
    
    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }    
}

