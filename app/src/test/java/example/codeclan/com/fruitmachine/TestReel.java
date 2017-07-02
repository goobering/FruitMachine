package example.codeclan.com.fruitmachine;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by user on 30/06/2017.
 */

public class TestReel
{
    private Reel reel;
    private ArrayList<Symbol> symbols;

    @Before
    public void before()
    {
        reel = new Reel();

        symbols = new ArrayList<Symbol>();
        for(example.codeclan.com.fruitmachine.enums.Symbol symbol : example.codeclan.com.fruitmachine.enums.Symbol.values())
        {
            symbols.add(new Symbol(symbol.getName(), symbol.getScore()));
        }
    }

    @Test
    public void testCreateReel()
    {
        int i = 0;

        for(Symbol symbol : symbols)
        {
            assertEquals(true, reel.getSymbols().get(i).getImage().equals(symbol.getImage()));
            assertEquals(true, reel.getSymbols().get(i).getScore() == symbol.getScore());
            i++;
        }
    }

    @Test
    public void testGetHighestScore()
    {
        assertEquals(10, reel.getHighestScore());
    }

    @Test
    public void testNudge()
    {
        Symbol preNudgeCurrentSymbol = reel.getCurrentSymbol();
        Symbol postNudgeCurrentSymbol = reel.getNextSymbol();

        reel.nudge();

        assertEquals(postNudgeCurrentSymbol, reel.getCurrentSymbol());
        assertEquals(preNudgeCurrentSymbol, reel.getPreviousSymbol());
    }

    @Test
    public void testSpin()
    {
        Symbol preSpinSymbol = reel.getCurrentSymbol();
        reel.spin();
        assertTrue(reel.getSymbols().contains(reel.getCurrentSymbol()));
        assertFalse(reel.getCurrentSymbol().getImage().equals(preSpinSymbol.getImage()));
    }
}
