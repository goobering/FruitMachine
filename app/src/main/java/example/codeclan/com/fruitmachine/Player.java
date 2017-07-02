package example.codeclan.com.fruitmachine;

/**
 * Created by user on 30/06/2017.
 */

public class Player
{
    private int bank;

    public Player()
    {
        bank = 0;
    }

    public void addToBank(int amount)
    {
        if(amount <= 0)
        {
            throw new IllegalArgumentException("Amount must be greater than zero.");
        }

        bank += amount;
    }

    public void removeFromBank(int amount)
    {
        if(amount <= 0)
        {
            throw new IllegalArgumentException("Amount must be greater than zero.");
        }

        if(amount > bank)
        {
            throw new IllegalArgumentException("Amount must be less than bank.");
        }

        bank -= amount;
    }

    public int getBank()
    {
        return bank;
    }
}
