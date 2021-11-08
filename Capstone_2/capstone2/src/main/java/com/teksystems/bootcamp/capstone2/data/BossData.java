package com.teksystems.bootcamp.capstone2.data;


public class BossData extends ScreensData{
    String name = "BOSS ROOM";
    int myLevel = 7;
    boolean roomCleared = false;

    @Override
    public void startRoom() {

    }

    public boolean finishedRoom() {
        return roomCleared;
    }

    @Override
    public void getInput(int i) {

    }

}
