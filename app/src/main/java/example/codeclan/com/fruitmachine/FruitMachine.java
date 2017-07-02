package example.codeclan.com.fruitmachine;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by user on 30/06/2017.
 */

public class FruitMachine
{
    private int bank;
    private ArrayList<Reel> reels;
    private Player player;
    private boolean lastTurnWon;
    private int playerCredit;
    private int lastAmountWon;

    public FruitMachine(ArrayList<Reel> reels)
    {
        this.bank = 0;
        this.reels = reels;
        lastTurnWon = false;
        playerCredit = 0;
        lastAmountWon = 0;
    }

    public boolean canCoverBet()
    {
        if (bank > reels.get(0).getHighestScore() * reels.size())
        {
            return true;
        } else
        {
            return false;
        }
    }

    public void addPlayer(Player player)
    {
        this.player = player;
    }

    public void spinReels(HashSet<Integer> holdSet)
    {
        for (Reel reel : reels)
        {
            if(!holdSet.contains(reels.indexOf(reel)))
            {
                reel.spin();
            }
        }
    }

    public void nudgeReels(HashSet<Integer> nudgeSet)
    {
        for (Reel reel : reels)
        {
            if(nudgeSet.contains(reels.indexOf(reel)))
            {
                reel.nudge();
            }
        }
    }

    public ArrayList<Symbol> getPreviousSymbols()
    {
        ArrayList<Symbol> result = new ArrayList<Symbol>();
        for (Reel reel : reels)
        {
            result.add(reel.getPreviousSymbol());
        }

        return result;
    }

    public ArrayList<Symbol> getCurrentSymbols()
    {
        ArrayList<Symbol> result = new ArrayList<Symbol>();
        for (Reel reel : reels)
        {
            result.add(reel.getCurrentSymbol());
        }

        return result;
    }

    public ArrayList<Symbol> getNextSymbols()
    {
        ArrayList<Symbol> result = new ArrayList<Symbol>();
        for (Reel reel : reels)
        {
            result.add(reel.getNextSymbol());
        }

        return result;
    }

    public void addToBank(int amount)
    {
        if (amount > 0)
        {
            bank += amount;
        } else
        {
            throw new IllegalArgumentException("Amount must be greater than zero:");
        }
    }

    public void removeFromBank(int amount)
    {
        if (amount <= 0)
        {
            throw new IllegalArgumentException("Amount must be greater than zero.");
        }

        if (amount > bank)
        {
            throw new IllegalArgumentException("Amount must be less than bank.");
        }

        bank -= amount;
    }

    public int getPlayerCredit()
    {
        return playerCredit;
    }

    public void addToPlayerCredit(int amount)
    {
        if (amount <= 0)
        {
            throw new IllegalArgumentException("Amount must be greater than zero.");
        }

        playerCredit += amount;
    }

    public void removeFromPlayerCredit(int amount)
    {
        if (amount <= 0)
        {
            throw new IllegalArgumentException("Amount must be greater than zero.");
        }
        if (amount > playerCredit)
        {
            throw new IllegalArgumentException("Amount must be less than playerCredit");
        }

        playerCredit -= amount;
    }

    public int getBank()
    {
        return bank;
    }

    public boolean symbolsMatch(ArrayList<Symbol> symbols)
    {
        boolean symbolsMatch = true;

        String firstSymbolImage = symbols.get(0).getImage();

        for (Symbol symbol : symbols)
        {
            if (!symbol.getImage().equals(firstSymbolImage))
            {
                symbolsMatch = false;
                break;
            }
        }

        return symbolsMatch;
    }

    public void moveCashToPlayer(int amount)
    {
        if (player == null)
        {
            throw new NullPointerException("Player doesn't exist.");
        }

        if (amount < 0)
        {
            throw new IllegalArgumentException("Amount must be greater than zero.");
        }

        try
        {
            removeFromBank(amount);
        }
        catch (IllegalArgumentException ex)
        {
            throw ex;
        }

        try
        {
            player.addToBank(amount);
        }
        catch (IllegalArgumentException ex)
        {
            throw ex;
        }
    }

    public void addCreditFromPlayerBank(int amount)
    {
        try
        {
            player.removeFromBank(amount);
        }
        catch (IllegalArgumentException ex)
        {
            throw ex;
        }

        try
        {
            addToPlayerCredit(amount);
        }
        catch (IllegalArgumentException ex)
        {
            throw ex;
        }
    }

    public boolean lastTurnWon()
    {
        return lastTurnWon;
    }

    public boolean canPlay()
    {
        return playerCredit > 0;
    }

    public void moveCreditToBank(int amount)
    {
        if(amount > playerCredit)
        {
            throw new IllegalArgumentException("Amount can't be greater than player credit.");
        }

        removeFromPlayerCredit(1);
        addToBank(1);
    }

    public void pullLever(HashSet<Integer> holdSet)
    {
        if (!canCoverBet())
        {
            throw new IllegalArgumentException("Machine doesn't have enough credit to cover payout.");
        }
        spinReels(holdSet);
    }

    public void checkReelsAndPayout()
    {
        ArrayList<Symbol> currentSymbols = getCurrentSymbols();

        if (symbolsMatch(currentSymbols))
        {
            lastTurnWon = true;
            int amount = 0;

            for (Symbol symbol : currentSymbols)
            {
                amount += symbol.getScore();
            }

            lastAmountWon = amount;
            moveCashToPlayer(lastAmountWon);
        }
        else
        {
            lastTurnWon = false;
            lastAmountWon = 0;
        }
    }

    public int getLastAmountWon()
    {
        return lastAmountWon;
    }

    public void sweepCredit()
    {
        bank += playerCredit;
        playerCredit = 0;
    }
}
