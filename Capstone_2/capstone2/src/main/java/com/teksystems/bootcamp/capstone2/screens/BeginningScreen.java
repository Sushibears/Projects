package com.teksystems.bootcamp.capstone2.screens;

public class BeginningScreen extends GameScreen{

    private static final String initialText =  "! GAME START ! \n" +
            "Masil is ready to visit her friend today! She has her purse & sword.... \n" +
            "...! Oh no she forgot the necklace she borrowed! \n" +
            "Where is it.......? Not here...Not there...Did She leave it in the basement? \n" +
            "It's the last place to check....but nobody's cleaned the basement in a while.... \n" +
            "Masil quickly goes to the basement!";
    private static final String prompt = "Press any key to continue.";


    protected BeginningScreen() {
        super(initialText, prompt);
    }

    public static BeginningScreen getInstance() {
        return new BeginningScreen();
    }

    @Override
    protected GameScreen nextScreen(String input) {

        return EncounterScreen.getInstance();
    }
}
