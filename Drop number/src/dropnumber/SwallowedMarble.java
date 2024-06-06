
package dropnumber;

public class SwallowedMarble extends ActiveMarble {
    public enum Direction { UP, LEFT, RIGHT }
    
    private Direction dir;
    
    // unique object
    private static SwallowedMarble instance = null;
    
    private SwallowedMarble() {
        super();
        dir = Direction.UP;
    }

    // Singleton Design Pattern (Creational)
    // Get the only object available
    public static SwallowedMarble getInstance() {
        if (instance == null)
            instance = new SwallowedMarble();
        return instance;
    }
    
    @Override
    public double getSpeed() {
        return MEDIUM_SPEED;
    }
    
    public Direction getDir() {
        return dir;
    }

    public void setDir(Direction dir) {
        this.dir = dir;
    }
}

