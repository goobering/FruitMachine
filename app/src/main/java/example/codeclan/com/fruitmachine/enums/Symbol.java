package example.codeclan.com.fruitmachine.enums;

/**
 * Created by user on 30/06/2017.
 */

public enum Symbol
{
    BELL(1),
    ORANGE(2),
    MELON(3),
    CHERRY(4),
    SHAMROCK(5),
    BAR(6),
    DIAMOND(7),
    SEVEN(8),
    HORSESHOE(9),
    COIN(10);

    private int score;

    Symbol(int score)
    {
        this.score = score;
    }

    public int getScore()
    {
        return score;
    }

    public String getName()
    {
        return name();
    }

    public example.codeclan.com.fruitmachine.Symbol getSymbol()
    {
        return new example.codeclan.com.fruitmachine.Symbol(name(), score);
    }
}
