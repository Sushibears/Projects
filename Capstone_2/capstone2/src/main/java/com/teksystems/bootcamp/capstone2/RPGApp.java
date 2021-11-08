package com.teksystems.bootcamp.capstone2;

public class RPGApp {
    public static void main(String[] args) {
       RPGGame game = RPGGame.getInstance();
       game.play();
    }
}
