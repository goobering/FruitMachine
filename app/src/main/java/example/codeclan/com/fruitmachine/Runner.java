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

        int creditAmount = UI.getAmountToAdd();
        fruitMachine.addCreditFromPlayerBank(creditAmount);

        while(true)
        {
            if(fruitMachine.getPlayerCredit() > 0)
            {
                //Initial play UI
                UI.showPlayerCredits(fruitMachine.getPlayerCredit());
                UI.showStartGame();
                //User pulls lever
                UI.getPullLeverKey();

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
                UI.showPullLever();
                UI.showSymbols(fruitMachine.getPreviousSymbols(), fruitMachine.getCurrentSymbols(), fruitMachine.getNextSymbols());

                //User chooses whether to nudge reels
                UI.showChooseNudgeReels();
                HashSet<Integer> nudgeReels = UI.getReelNums();
                fruitMachine.nudgeReels(nudgeReels);
                UI.showSymbols(fruitMachine.getPreviousSymbols(), fruitMachine.getCurrentSymbols(), fruitMachine.getNextSymbols());

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
                UI.showPullLever();
                //Show post-hold symbols
                UI.showSymbols(fruitMachine.getPreviousSymbols(), fruitMachine.getCurrentSymbols(), fruitMachine.getNextSymbols());
                //Check post-hold reels and payout if win
                fruitMachine.checkReelsAndPayout();

                if(fruitMachine.lastTurnWon())
                {
                    UI.showWin(fruitMachine.getLastAmountWon());
                }
                else
                {
                    UI.showLose();
                }
            }
            else
            {
                UI.showOutOfCredit();
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

                    creditAmount = UI.getAmountToAdd();
                    fruitMachine.addCreditFromPlayerBank(creditAmount);
                }
            }
        }
    }
}
