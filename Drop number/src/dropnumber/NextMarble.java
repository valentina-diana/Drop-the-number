
package dropnumber;

public class NextMarble extends BoardMarble {
    // unique object
    private static NextMarble instance = null;
    
    protected boolean visible;
    
    private NextMarble() {
        super();
        visible = false;
    }
    
    // Singleton Design Pattern (Creational)
    // Get the only object available
    public static NextMarble getInstance() {
        if (instance == null)
            // first time it is accessed
            instance = new NextMarble();
        return instance;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }    
}

