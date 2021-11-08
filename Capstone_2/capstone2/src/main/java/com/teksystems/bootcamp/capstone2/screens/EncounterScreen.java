package com.teksystems.bootcamp.capstone2.screens;

public class EncounterScreen extends GameScreen {

    private static final String defaultText = "";
    private static final String defaultPrompt = "";
    private static String winLoseMsg;

    protected EncounterScreen() {
        super(defaultText, defaultPrompt);
        switch (gameState.getEncounterType()) {
            case "AmbushData":
                screenText = "AMBUSHED! Masil is surrounded by bugs!";
                prompt = "Should Masil fight or run!?  \n" +
                        "1 - FIGHT | 2 - RUN";
                break;
            case "BattleData":
                screenText = "Masil encountered some nasty bugs!";
                prompt = "Should Masil fight or run? \n" +
                        "1 - FIGHT | 2 - RUN";
                break;
            case "BossData":
                screenText = "Uh oh! There's a HUGE Bug in front of Masil!";
                prompt = "Should Masil fight or run?  \n" +
                        "1 - FIGHT | 2 - RUN";
                break;
            case "TreasureData":
                screenText = "Woah! There's two boxes!";
                prompt = "Should Masil open one or leave them alone? \n" +
                        "1 - OPEN A BOX | 2 - LEAVE THEM";
                break;
            default:
                screenText = "We don't know what's going to happen next..";
                prompt = "1 - QUIT";
                break;
        }
    }

    public static EncounterScreen getInstance() {
        return new EncounterScreen();
    }

    @Override
    protected GameScreen nextScreen(String input) {
        if(!input.equalsIgnoreCase("1") && !input.equalsIgnoreCase("2")){
            System.out.println("Please choose option 1 or 2!");
            return this;
        } else {
            gameState.playerInput(input);
            gameState.removeEncounter();
            if(gameState.didPlayerWin()){
                winLoseMsg =  "! Game Over ! \n Masil got the necklace & high tailed it out of there!";
            } else {
                winLoseMsg =  "! Game Over ! \n Masil fainted & had to be dragged out of the basement by her roommates...";
            }
            return gameState.didPlayerWin() ? EndScreen.getInstance(winLoseMsg) : EncounterScreen.getInstance();
        }
    }
}
