package example.codeclan.com.fruitmachine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by user on 30/06/2017.
 */

public class Reel
{
    ArrayList<Symbol> symbols;

    public Reel()
    {
        symbols = new ArrayList<Symbol>();
        makeReel();
    }

    private void makeReel()
    {
        for(example.codeclan.com.fruitmachine.enums.Symbol symbol : example.codeclan.com.fruitmachine.enums.Symbol.values())
        {
            symbols.add(symbol.getSymbol());
        }
    }

    public ArrayList<Symbol> getSymbols()
    {
        return symbols;
    }

    private int getSize()
    {
        return symbols.size();
    }

    public void nudge()
    {
        Collections.rotate(symbols, -1);
    }

    public void spin()
    {
        Random rand = new Random();

        int spinDistance = rand.nextInt(getSize());
        for(int i = 0; i < spinDistance; i++)
        {
            nudge();
        }
    }

    public Symbol getCurrentSymbol()
    {

        return symbols.get(0);
    }

    public Symbol getNextSymbol()
    {
        return symbols.get(1);
    }

    public Symbol getPreviousSymbol()
    {
        return symbols.get(symbols.size() - 1);
    }

    public int getHighestScore()
    {
        int highest = 0;

        for(Symbol symbol : symbols)
        {
            if(symbol.getScore() > highest)
            {
                highest = symbol.getScore();
            }
        }

        return highest;
    }
}
