
package dropnumber;

import org.junit.*;
import static org.junit.Assert.*;


public class JUnitTests {
    public JUnitTests() {
    }
    
    @Before
    public void setUp() {
        BoardMarbleFactory.calculateFontSizes();
    }
    
    @Test
    public void test1() {
        // Test Active Marble Speeds
        Controller cntrl = Controller.getInstance();
        ActiveMarble am = ActiveMarble.getInstance();
        cntrl.setState(new SlowMoveState());
        assertEquals("Test Slow Speed", 0.08, am.getSpeed(), 10);
        cntrl.setState(new MediumMoveState());
        assertEquals("Test Medium Speed", 0.16, am.getSpeed(), 10);
        cntrl.setState(new FastMoveState());
        assertEquals("Test Fast Speed", 1.00,am.getSpeed(), 10);
        cntrl.setState(new SwallowState());
        assertEquals("Test No Speed",0.0, am.getSpeed(), 10);
    }

    @Test
    public void test2() {
        // Test Marble Factory
        Marble mb = MarbleFactory.getMarble("BoardMarble", 16);
        assertTrue(mb instanceof BoardMarble);
        mb = MarbleFactory.getMarble("ActiveMarble");
        assertTrue(mb instanceof ActiveMarble);
        mb = MarbleFactory.getMarble("SwallowedMarble");
        assertTrue(mb instanceof SwallowedMarble);
        mb = MarbleFactory.getMarble("NextMarble");
        assertTrue(mb instanceof NextMarble);
    }
    
    @Test
    public void test3() {
        // Test Best Score update
        DropNumber dn = DropNumber.getInstance();
        int bestScore = dn.getBestScore();
        dn.setScore(bestScore + 1);
        assertTrue(dn.updateBestScore());
        bestScore = dn.getBestScore();
        dn.setScore(bestScore - 1);
        assertTrue(!dn.updateBestScore());
    }
    
    @Test
    public void test4() {
        // Test States
        Controller cntrl = Controller.getInstance();
        cntrl.setState(new SwallowState());
        State st = cntrl.getState();
        assertTrue(st instanceof SwallowState);
    } 
    }


