package example.codeclan.com.fruitmachine.enums;

/**
 * Created by user on 02/07/2017.
 */

public enum ASCIIFruitMachine
{
    TOP(" ___________________________________"),
    EMPTYROW("|           |           |           |"),
    DIVIDERROW("|-----------|-----------|-----------|"),
    BOTTOM(" ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾"),
    NUMBERS("      1           2           3");

    private String name;

    ASCIIFruitMachine(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
}