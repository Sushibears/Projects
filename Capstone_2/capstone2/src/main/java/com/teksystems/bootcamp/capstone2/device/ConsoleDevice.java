package com.teksystems.bootcamp.capstone2.device;

import com.teksystems.bootcamp.capstone2.screens.GameScreen;

import java.util.Iterator;
import java.util.Scanner;

public class ConsoleDevice implements GameDevice {
    private static Scanner instance;

    public static ConsoleDevice getInstance() {
        return new ConsoleDevice();
    }

    public static void quitInstance() {
        if (instance == null) {
            return;
        }
        instance.close();
        instance = null;
    }

    @Override
    public String getInput(String prompt) {
        System.out.println(prompt);
        return getScanner().next();
    }

    @Override
    public void draw(GameScreen gameScreen) {
        clearConsole();
        System.out.println(gameScreen);
    }

    private void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private Scanner getScanner() {
        if(instance == null){
            instance = new Scanner(System.in);
        }
        return instance;
    }
}
