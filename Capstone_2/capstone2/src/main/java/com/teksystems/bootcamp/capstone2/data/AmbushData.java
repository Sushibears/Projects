package com.teksystems.bootcamp.capstone2.data;


public class AmbushData extends ScreensData {
    String name = "";
    int myLevel = 0;
    int fightChance;
    boolean roomCleared = false;

    public void getInput(int choice){
        if(choice == 1){

        } else if (choice == 2){

        } else {
            System.out.println("You have to FIGHT or RUN!");
        }
    }


}
