package example.codeclan.com.fruitmachine;

import java.util.PriorityQueue;

/**
 * Created by user on 30/06/2017.
 */

public class Symbol
{
    private String image;
    private int score;

    public Symbol(String image, int score)
    {
        this.image = image;
        this.score = score;
    }

    public String getImage()
    {
        return image;
    }

    public int getScore()
    {
        return score;
    }
}
