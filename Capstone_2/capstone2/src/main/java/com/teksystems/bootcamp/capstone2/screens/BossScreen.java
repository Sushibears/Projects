package com.teksystems.bootcamp.capstone2.screens;

public class BossScreen extends GameScreen {
    private static final String prompt = " [a]TTACK "; //change to menu
    private static final String initialtext = "BRASHER APPEARED";
//            "\n ================== \n " +
//            "Masil has finally reached the end of the basement. \n" +
//            "The floor here is REALLY dirty....it's pretty gross...\n" +
//            "........!!!!! \n" +
//            "There's a HUGE bug down here! \n" +
//            "Get ready to fight!"; // move to different screen

    protected BossScreen() {
        super(initialtext, prompt);
    }

    public static BossScreen getInstance() {
        return new BossScreen();
    }


    @Override
    protected GameScreen nextScreen(String input) {

      //  String playerHealthmsg = String.format("You have %d health left", playerHealth);
      //  String monsterHealthmsg = monsterHealth == 0 ? "there is no monster" : String.format("the monster has %d health left", monsterHealth);
      //  screenText = String.format("%s, %s",playerHealthmsg,monsterHealthmsg);
        String endResultMessage = gameState.didPlayerWin() ? "You won" : "You lost";
        String endMessage = String.format("%s%n%s",endResultMessage,"See you next time!");
        return gameState.didPlayerWin() ? EndScreen.getInstance(endMessage) : this;
    }
}
