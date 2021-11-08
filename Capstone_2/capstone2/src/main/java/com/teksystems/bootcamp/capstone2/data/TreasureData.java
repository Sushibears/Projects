package com.teksystems.bootcamp.capstone2.data;

import com.teksystems.bootcamp.capstone2.GameState;

public class TreasureData extends ScreensData{
    String name = "";
    int myLevel = 0;
    boolean roomCleared = false;

    public void startRoom() {
    }

    public void getInput(int choice){
            System.out.println("You leave the boxes alone & move on to the next room");
            roomCleared = true;
    }

    public void boxRoll(){
        int dice = (int) (Math.random()*10);
        roomCleared = true;
    }
}
