package com.teksystems.bootcamp.capstone2.device;

import com.teksystems.bootcamp.capstone2.screens.GameScreen;

public interface GameDevice {
    String getInput(String prompt);

    void draw(GameScreen gameScreen);
}
