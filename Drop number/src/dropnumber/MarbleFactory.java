
package dropnumber;

// Factory Method Design Pattern (Creational)
public class MarbleFactory {
    public static Marble getMarble(String marbleType, int ...args) {
        if (marbleType == null) {
            return null;
        }
        if (marbleType.equalsIgnoreCase("BoardMarble")) {
            if (args.length > 0) {
                return BoardMarbleFactory.getMarble(args[0]);
            }
            else
                return null;
        }
        else if (marbleType.equalsIgnoreCase("ActiveMarble")) {
            return ActiveMarble.getInstance();
        }
        else if (marbleType.equalsIgnoreCase("SwallowedMarble")) {
            return SwallowedMarble.getInstance();
        }
        else if (marbleType.equalsIgnoreCase("NextMarble")) {
            return NextMarble.getInstance();
        }
        return null;
    }
}

