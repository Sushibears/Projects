package com.teksystems.bootcamp.capstone2.screens;

import com.teksystems.bootcamp.capstone2.GameState;

public class EndScreen extends GameScreen {

    private static final String prompt = " ! GAME OVER !";
    private static String initialtext = "";


    public EndScreen(String message) {
        super(message, prompt);
    }

    public static EndScreen getInstance(String message) {
        return new EndScreen(message);
    }

    @Override
    protected GameScreen nextScreen(String input) {
        return null;
    }
}
