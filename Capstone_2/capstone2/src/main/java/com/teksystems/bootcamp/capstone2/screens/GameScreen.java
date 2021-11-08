package com.teksystems.bootcamp.capstone2.screens;

import com.teksystems.bootcamp.capstone2.GameState;
import com.teksystems.bootcamp.capstone2.device.ConsoleDevice;
import com.teksystems.bootcamp.capstone2.device.GameDevice;

public abstract class GameScreen {
    private final GameDevice device = ConsoleDevice.getInstance();
    protected GameState gameState = GameState.getInstance();
    protected String prompt;
    protected String screenText;

    protected GameScreen(String screenText, String prompt) {
        this.prompt = prompt;
        this.screenText = screenText;
    }

    public GameScreen play(){
        draw();
        String input = getInput();
        return input != null ? nextScreen(input) : this;
    }


    protected abstract GameScreen nextScreen(String input);

    public String getInput() {
        return prompt != null ? device.getInput(prompt) : null;
    }

    public void draw() {
        device.draw(this);
    }

    @Override
    public String toString() {
        return screenText;
    }
}
