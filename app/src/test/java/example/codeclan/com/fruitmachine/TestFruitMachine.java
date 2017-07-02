package example.codeclan.com.fruitmachine;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by user on 30/06/2017.
 */

public class TestFruitMachine
{
    private FruitMachine fruitMachine;
    private ArrayList<Reel> reels;
    private Player player;

    @Before
    public void before()
    {
        player = new Player();
        player.addToBank(10);

        ArrayList<Reel> reels = new ArrayList<Reel>();
        for(int i = 0; i < 3; i++)
        {
            reels.add(new Reel());
        }

        fruitMachine = new FruitMachine(reels);
        fruitMachine.addToBank(200);
        fruitMachine.addPlayer(player);
    }

    @Test
    public void testCantCoverBetWithOne()
    {
        fruitMachine.removeFromBank(199);
        assertFalse(fruitMachine.canCoverBet());
    }

    @Test
    public void testCantCoverBetWithZero()
    {
        fruitMachine.removeFromBank(200);
        assertFalse(fruitMachine.canCoverBet());
    }

    @Test
    public void testCanCoverBet()
    {
        assertTrue(fruitMachine.canCoverBet());
    }

    @Test
    public void testSpinAllReels()
    {
        ArrayList<Symbol> preSpinSymbols = fruitMachine.getCurrentSymbols();
        fruitMachine.spinReels(new HashSet<Integer>());

        for(int i = 0; i < 100000; i++)
        {
            for(int j = 0; j < preSpinSymbols.size(); j++)
            {
                //After spinning reels, new symbols should not equal old symbols
                assertNotEquals(preSpinSymbols.get(j).getImage(), fruitMachine.getCurrentSymbols().get(j).getImage());
                assertNotEquals(preSpinSymbols.get(j).getScore(), fruitMachine.getCurrentSymbols().get(j).getScore());
            }
        }
    }

    @Test
    public void testSpinOneReel()
    {
        ArrayList<Symbol> preSpinSymbols = fruitMachine.getCurrentSymbols();
        //reelsToHold is zero indexed
        HashSet<Integer> reelsToHold = new HashSet<Integer>();
        reelsToHold.add(1);
        reelsToHold.add(2);

        fruitMachine.spinReels(reelsToHold);

        for(int i = 0; i < 100000; i++)
        {
            //After spinning reels, new symbols should not equal old symbols on spun reels
            assertNotEquals(preSpinSymbols.get(0).getScore(), fruitMachine.getCurrentSymbols().get(0).getScore());
            assertNotEquals(preSpinSymbols.get(0).getImage(), fruitMachine.getCurrentSymbols().get(0).getImage());
            assertEquals(preSpinSymbols.get(1).getScore(), fruitMachine.getCurrentSymbols().get(1).getScore());
            assertEquals(preSpinSymbols.get(1).getImage(), fruitMachine.getCurrentSymbols().get(1).getImage());
            assertEquals(preSpinSymbols.get(2).getScore(), fruitMachine.getCurrentSymbols().get(2).getScore());
            assertEquals(preSpinSymbols.get(2).getImage(), fruitMachine.getCurrentSymbols().get(2).getImage());
        }
    }

    @Test
    public void testSpinTwoReels()
    {
        ArrayList<Symbol> preSpinSymbols = fruitMachine.getCurrentSymbols();
        //reelsToHold is zero indexed
        HashSet<Integer> reelsToHold = new HashSet<Integer>();
        reelsToHold.add(2);

        fruitMachine.spinReels(reelsToHold);

        for(int i = 0; i < 100000; i++)
        {
            //After spinning reels, new symbols should not equal old symbols on spun reels
            assertNotEquals(preSpinSymbols.get(0).getScore(), fruitMachine.getCurrentSymbols().get(0).getScore());
            assertNotEquals(preSpinSymbols.get(0).getImage(), fruitMachine.getCurrentSymbols().get(0).getImage());
            assertNotEquals(preSpinSymbols.get(1).getScore(), fruitMachine.getCurrentSymbols().get(1).getScore());
            assertNotEquals(preSpinSymbols.get(1).getImage(), fruitMachine.getCurrentSymbols().get(1).getImage());
            assertEquals(preSpinSymbols.get(2).getScore(), fruitMachine.getCurrentSymbols().get(2).getScore());
            assertEquals(preSpinSymbols.get(2).getImage(), fruitMachine.getCurrentSymbols().get(2).getImage());
        }
    }

    @Test
    public void testSpinNoReels()
    {
        ArrayList<Symbol> preSpinSymbols = fruitMachine.getCurrentSymbols();
        //reelsToHold is zero indexed
        HashSet<Integer> reelsToHold = new HashSet<Integer>();
        reelsToHold.add(0);
        reelsToHold.add(1);
        reelsToHold.add(2);

        fruitMachine.spinReels(reelsToHold);

        for(int i = 0; i < 100000; i++)
        {
            //After spinning reels, new symbols should not equal old symbols on spun reels
            assertEquals(preSpinSymbols.get(0).getScore(), fruitMachine.getCurrentSymbols().get(0).getScore());
            assertEquals(preSpinSymbols.get(0).getImage(), fruitMachine.getCurrentSymbols().get(0).getImage());
            assertEquals(preSpinSymbols.get(1).getScore(), fruitMachine.getCurrentSymbols().get(1).getScore());
            assertEquals(preSpinSymbols.get(1).getImage(), fruitMachine.getCurrentSymbols().get(1).getImage());
            assertEquals(preSpinSymbols.get(2).getScore(), fruitMachine.getCurrentSymbols().get(2).getScore());
            assertEquals(preSpinSymbols.get(2).getImage(), fruitMachine.getCurrentSymbols().get(2).getImage());
        }
    }

    @Test
    public void testAddToBank()
    {
        fruitMachine.addToBank(100);
        assertTrue(fruitMachine.getBank() == 300);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddToBankThrowsOnZero()
    {
        fruitMachine.addToBank(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddToBankThrowsOnNegative()
    {
        fruitMachine.addToBank(-1);
    }

    @Test
    public void testRemoveFromBank()
    {
        fruitMachine.removeFromBank(100);
        assertTrue(fruitMachine.getBank() == 100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveFromBankThrowsOnZero()
    {
        fruitMachine.removeFromBank(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveFromeBankThrowsOnNegative()
    {
        fruitMachine.removeFromBank(-1);
    }

    @Test
    public void testAddToPlayerCredit()
    {
        fruitMachine.addToPlayerCredit(10);
        assertTrue(fruitMachine.getPlayerCredit() == 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddToPlayerCreditThrowsOnZero()
    {
        fruitMachine.addToPlayerCredit(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddToPlayerCreditThrowsOnNegative()
    {
        fruitMachine.addToPlayerCredit(0);
    }

    @Test
    public void testRemoveFromPlayerCredit()
    {
        fruitMachine.addCreditFromPlayerBank(5);
        fruitMachine.removeFromPlayerCredit(5);
        assertTrue(fruitMachine.getPlayerCredit() == 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveFromPlayerCreditThrowsOnZero()
    {
        fruitMachine.removeFromPlayerCredit(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveFromPlayerCreditThrowsOnNegative()
    {
        fruitMachine.removeFromPlayerCredit(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveFromPlayerCreditThrowsOnGreaterThanAvailable()
    {
        fruitMachine.removeFromPlayerCredit(1);
    }

    @Test
    public void testRemoveCashFromBank()
    {
        fruitMachine.removeFromBank(1);
        assertEquals(199, fruitMachine.getBank());
    }
}
