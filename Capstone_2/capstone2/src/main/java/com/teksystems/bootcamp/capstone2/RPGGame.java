package com.teksystems.bootcamp.capstone2;

import com.teksystems.bootcamp.capstone2.device.ConsoleDevice;
import com.teksystems.bootcamp.capstone2.screens.EndScreen;
import com.teksystems.bootcamp.capstone2.screens.GameScreen;
import com.teksystems.bootcamp.capstone2.screens.WelcomeScreen;

public class RPGGame {

    private GameScreen gamescreen  = WelcomeScreen.getInstance();

    public static RPGGame getInstance() {
        return new RPGGame();
    }

    public void play() {
        GameState.getInstance().gameStart();
        do{
            gamescreen = gamescreen.play();
        } while (!EndScreen.class.equals(gamescreen.getClass()));
        gamescreen.draw();
        ConsoleDevice.quitInstance();
    }
}
