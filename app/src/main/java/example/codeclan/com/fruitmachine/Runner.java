package example.codeclan.com.fruitmachine;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by user on 30/06/2017.
 */

public class Runner
{
    public static void main(String[] args)
    {
        //Set up Player
        Player player = new Player();
        player.addToBank(100);

        //Set up Reels
        ArrayList<Reel> reels = new ArrayList<Reel>();
        for(int i = 0; i < 3; i++)
        {
            reels.add(new Reel());
        }

        //Set up FruitMachine
        FruitMachine fruitMachine = new FruitMachine(reels);
        fruitMachine.addToBank(200);
        fruitMachine.addPlayer(player);

        //Begin UI
        UI.showWelcome();
        UI.showTopUp(player.getBank());
        UI.showPrompt();

        int creditAmount = UI.getAmountToAdd();
        fruitMachine.addCreditFromPlayerBank(creditAmount);

        while(true)
        {
            //Initial play UI
            UI.showPlayerCredits(fruitMachine.getPlayerCredit());
            UI.showBlankLine();
            UI.showStartGame();
            UI.showBlankLine();
            //User pulls lever
            UI.getPullLeverKey();
            UI.showBlankLine();
            if(fruitMachine.getPlayerCredit() > 0)
            {
                fruitMachine.moveCreditToBank(1);

                try
                {
                    fruitMachine.pullLever(new HashSet<Integer>());
                }
                catch(IllegalArgumentException ex)
                {
                    UI.showError(ex.getMessage());
                    continue;
                }
                UI.showBlankLine();
                UI.showPullLever();
                UI.showBlankLine();
                UI.showSymbols(fruitMachine.getPreviousSymbols(), fruitMachine.getCurrentSymbols(), fruitMachine.getNextSymbols());
                UI.showBlankLine();

                //User chooses whether to nudge reels
                UI.showChooseNudgeReels();
                HashSet<Integer> nudgeReels = UI.getReelNums();
                fruitMachine.nudgeReels(nudgeReels);
                UI.showSymbols(fruitMachine.getPreviousSymbols(), fruitMachine.getCurrentSymbols(), fruitMachine.getNextSymbols());
                UI.showBlankLine();

                //User chooses whether to hold reels
                UI.showChooseHoldReels();
                HashSet<Integer> holdReels = UI.getReelNums();
                UI.showRespin();
                //User pulls lever for hold respin
                UI.getPullLeverKey();
                try
                {
                    fruitMachine.pullLever(holdReels);
                }
                catch(IllegalArgumentException ex)
                {
                    UI.showError(ex.getMessage());
                    continue;
                }
                UI.showBlankLine();
                UI.showPullLever();
                UI.showBlankLine();
                //Show post-hold symbols
                UI.showSymbols(fruitMachine.getPreviousSymbols(), fruitMachine.getCurrentSymbols(), fruitMachine.getNextSymbols());
                UI.showBlankLine();
                //Check post-hold reels and payout if win
                fruitMachine.checkReelsAndPayout();

                if(fruitMachine.lastTurnWon())
                {
                    UI.showWin(fruitMachine.getLastAmountWon());
                    UI.showBlankLine();
                }
                else
                {
                    UI.showLose();
                    UI.showBlankLine();
                }
            }
            else
            {
                UI.showOutOfCredit();
                UI.showPrompt();
                char choice = UI.getTopUpOrQuit();
                if(choice == 'Q')
                {
                    fruitMachine.sweepCredit();
                    UI.showQuit();
                    System.exit(0);
                }
                else
                {
                    UI.showTopUp(player.getBank());
                    UI.showPrompt();

                    creditAmount = UI.getAmountToAdd();
                    fruitMachine.addCreditFromPlayerBank(creditAmount);
                }
            }
        }
    }
}
