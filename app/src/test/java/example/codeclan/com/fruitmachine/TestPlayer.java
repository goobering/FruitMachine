package example.codeclan.com.fruitmachine;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by user on 30/06/2017.
 */

public class TestPlayer
{
    private Player player;

    @Before
    public void before()
    {
        player = new Player();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddZeroToBank()
    {
        player.addToBank(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNegativeToBank()
    {
        player.addToBank(-1);
    }

    @Test
    public void testAddToBank()
    {
        player.addToBank(10);
        assertEquals(10, player.getBank());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveZeroFromBank()
    {
        player.removeFromBank(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNegativeFromBank()
    {
        player.removeFromBank(-1);
    }

    @Test
    public void testRemoveFromBank()
    {
        player.addToBank(10);
        player.removeFromBank(5);
        assertEquals(5, player.getBank());
    }
}
