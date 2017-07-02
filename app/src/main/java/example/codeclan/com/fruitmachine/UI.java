package example.codeclan.com.fruitmachine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;

import example.codeclan.com.fruitmachine.enums.ASCIIFruitMachine;

/**
 * Created by user on 30/06/2017.
 */

public class UI
{
    private static Scanner sc = new Scanner(System.in);

    public UI()
    {
    }

    public static void showBlankLine()
    {
        Viewer.printLine("");
    }

    public static void showWelcome()
    {
        Viewer.printLine("Welcome to the puggies!");
    }

    public static void showStartGame()
    {
        Viewer.printLine("Please press enter to pull the lever. Costs 1 credit.");
    }

    public static void showPlayerCredits(int amount)
    {
        Viewer.printLine(String.format("You currently have %d credits.", amount));
    }

    public static void showPrompt()
    {
        Viewer.print("> ");
    }

    public static int getAmountToAdd()
    {
        int input = 0;

        while(true)
        {
            input = getIntInput();

            if(input < 1)
            {
                Viewer.printLine("Whoops! Please enter an integer greater than zero.");
                showPrompt();
                continue;
            }

            break;
        }

        return input;
    }

    public static int getIntInput()
    {
        while(true)
        {
            try
            {
                return Integer.parseInt(getInput());
            }
            catch(NumberFormatException ex)
            {
                Viewer.printLine("Whoops! Please enter an integer value.");
                showPrompt();
            }
        }
    }

    public static String getInput()
    {
        return sc.nextLine();
    }

    public static void showPullLever()
    {
        Viewer.printLine("<spinspinspinspinspinspinspin>");
    }

    public static void getPullLeverKey()
    {
        while(true)
        {
            String input = null;
            input = getInput();

            if(!input.equals(""))
            {
                Viewer.printLine("Whoops - not the enter key! Please try again.");
                input = null;
            }
            else
            {
                break;
            }
        }
    }

    public static String padStringToLength(String string, int length)
    {
        StringBuilder sb = new StringBuilder();
        sb.append(string);
        while(sb.length() < length)
        {
            sb.append(" ");
        }

        return sb.toString();
    }

    public static String buildSymbolRow(ArrayList<Symbol> symbols)
    {
        StringBuilder row = new StringBuilder();

        for(Symbol symbol : symbols)
        {
            String padded = padStringToLength(symbol.getImage(), 9);

            StringBuilder sb = new StringBuilder();
            sb.append("| ");
            sb.append(padded);
            sb.append(" ");
            row.append(sb.toString());
        }

        row.append("|");

        return row.toString();
    }

    public static void showSymbols(ArrayList<Symbol> previousSymbols, ArrayList<Symbol> currentSymbols, ArrayList<Symbol> nextSymbols)
    {
        Viewer.printLine(ASCIIFruitMachine.TOP.getName());
        Viewer.printLine(ASCIIFruitMachine.EMPTYROW.getName());
        Viewer.printLine(buildSymbolRow(previousSymbols));
        Viewer.printLine(ASCIIFruitMachine.EMPTYROW.getName());
        Viewer.printLine(ASCIIFruitMachine.DIVIDERROW.getName());
        Viewer.printLine(ASCIIFruitMachine.EMPTYROW.getName());
        Viewer.printLine(buildSymbolRow(currentSymbols));
        Viewer.printLine(ASCIIFruitMachine.EMPTYROW.getName());
        Viewer.printLine(ASCIIFruitMachine.DIVIDERROW.getName());
        Viewer.printLine(ASCIIFruitMachine.EMPTYROW.getName());
        Viewer.printLine(buildSymbolRow(nextSymbols));
        Viewer.printLine(ASCIIFruitMachine.EMPTYROW.getName());
        Viewer.printLine(ASCIIFruitMachine.BOTTOM.getName());
        Viewer.printLine(ASCIIFruitMachine.NUMBERS.getName());
    }

    public static void showWin(int amount)
    {
        Viewer.printLine(String.format("You won %d.", amount));
    }

    public static void showLose()
    {
        Viewer.printLine("You lost.");
    }

    public static void showChooseHoldReels()
    {
        Viewer.printLine("Please input the reel numbers you'd like to hold, or 0 to hold none.");
    }

    public static HashSet<Integer> getReelNums()
    {
        //HashSet ensures no duplicates
        HashSet<Integer> resultHash = new HashSet<Integer>();

        while(true)
        {
            String input = getInput();

            //Acceptable values
            char[] wanted = new char[]{'0', '1', '2', '3'};
            boolean hit;
            for(char wantedChar : wanted)
            {
                hit = input.indexOf(wantedChar) >= 0;
                if(hit)
                {
                    resultHash.add(Integer.parseInt(Character.toString(wantedChar)) - 1);
                }
            }

            //If anything input other than 0, 1, 2, 3
            if(resultHash.size() == 0)
            {
                Viewer.printLine("Whoops! Please enter numbers between 0 and 3.");
                showPrompt();
                continue;
            }

            //If user chooses 0/no reels
            if(resultHash.contains(-1))
            {
                resultHash.clear();
            }

            return resultHash;
        }
    }

    public static void showRespin()
    {
        Viewer.printLine("Press enter to respin.");
    }

    public static void showOutOfCredit()
    {
        Viewer.printLine("You've run out of credit. Would you like to (T)op up or (Q)uit?");
    }

    public static char getTopUpOrQuit()
    {
        while(true)
        {
            String input = getInput();

            char[] wanted = new char[]{'Q', 'T'};
            boolean hit;
            for(char wantedChar : wanted)
            {
                hit = input.indexOf(wantedChar) >= 0;
                if(hit)
                {
                    return wantedChar;
                }
            }

            Viewer.printLine("Whoops! Please enter T to top up or Q to quit.");
        }
    }

    public static void showQuit()
    {
        Viewer.printLine("Thanks for playing!");
    }

    public static void showTopUp(int bank)
    {
        Viewer.printLine(String.format("You currently have %d credits in your wallet.", bank));
        Viewer.printLine("How many credits would you like to play with? (1 credit per play/non-refundable)");
    }

    public static void showChooseNudgeReels()
    {
        Viewer.printLine("Please input the reel numbers you'd like to nudge, or 0 to nudge none.");
    }

    public static void showError(String message)
    {
        Viewer.printLine("Something went wrong!");
        Viewer.printLine(message);
        UI.showBlankLine();
    }
}
