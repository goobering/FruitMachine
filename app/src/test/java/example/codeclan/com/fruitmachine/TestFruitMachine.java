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
        ArrayList<Symbol> currentSymbols = fruitMachine.getCurrentSymbols();
        fruitMachine.spinReels(new HashSet<Integer>());

        for(int i = 0; i < currentSymbols.size(); i++)
        {
            assertNotEquals(currentSymbols.get(i).getImage(), fruitMachine.getCurrentSymbols().get(i).getImage());
            assertNotEquals(currentSymbols.get(i).getScore(), fruitMachine.getCurrentSymbols().get(i).getScore());
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
