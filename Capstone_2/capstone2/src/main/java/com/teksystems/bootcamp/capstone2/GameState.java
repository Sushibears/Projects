package com.teksystems.bootcamp.capstone2;

import com.teksystems.bootcamp.capstone2.data.*;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class GameState {

    public static ScreensData currentData;
    public static ArrayList<ScreensData> dataList = new ArrayList<>();
    static Player user = new Player();
    static String inputVal;
    private static GameState instance;
    private static int currentLevel = 0;
    public boolean playerWins = false;

    public GameState() {
        //   gameStart();
    }

    public static GameState getInstance() {
        if (instance == null) {
            instance = new GameState();
        }
        return instance;
    }

    public static void removeLife() {
        user.removeLife();
    }

    public static void addLife() {
        user.addLife();
    }

    public static void playerInput(String input) {
        if (getEncounterType().equalsIgnoreCase("BattleData") || getEncounterType().equalsIgnoreCase("BossData")) {
            switch (input) {
                case "1":
                case "2":
                    processBattle(input);
                    break;
            }
        } else if (getEncounterType().equalsIgnoreCase("AmbushData")) {
            switch (input) {
                case "1":
                case "2":
                    processAmbush(input);
            }
        } else if (getEncounterType().equalsIgnoreCase("TreasureData")) {
            switch (input) {
                case "1":
                case "2":
                    processTreasure(input);
                    break;
            }
        }
        System.out.println("Current lives: " + user.getLives());
    }

    public static void putPlayerInRoom() {
        currentData = dataList.get(currentLevel);
        dataList.get(currentLevel).startRoom();
    }

    public static String getEncounterType() {
        return dataList.get(0).getClass().getSimpleName();
    }

    public void gameStart() {
        spawnRooms();
        putPlayerInRoom();
    }

    protected void spawnRooms() {
        Random random = new Random();
        int x = 0;
        int nameNumB = 1;
        int nameNumA = 1;
        int nameNumT = 1;
        while (x <= 5) {
            int spawnType = random.nextInt(10);
            if (spawnType <= 4) {
                BattleData battleData = new BattleData();
                battleData.setName("battleRoom" + nameNumB);
                nameNumB++;
                battleData.setMyLevel(x);
                dataList.add(x, battleData);
            } else if (spawnType == 5 || spawnType <= 7) {
                AmbushData ambushData = new AmbushData();
                ambushData.setName("ambushRoom" + nameNumA);
                nameNumA++;
                ambushData.setMyLevel(x);
                dataList.add(x, ambushData);
            } else {
                TreasureData treasureData = new TreasureData();
                treasureData.setName("treasureRoom" + nameNumT);
                nameNumT++;
                treasureData.setMyLevel(x);
                dataList.add(x, treasureData);
            }
            x++;
        }
        if (x == 6) {
            BossData bossData = new BossData();
            bossData.setMyLevel(x);
            dataList.add(x, bossData);
        }
    }

    public boolean awaitingInput() {
        String input = inputVal;
        if (input.equalsIgnoreCase("6")) {
            return false;
        }
        playerInput(input);
        return true;
    }

    private static void processBattle(String input) {
        int dice = (int) (Math.random() * 10);
        switch (input) {
            case "1":
                System.out.println("Masil decided to fight...");
                try {
                    TimeUnit.SECONDS.sleep(2);
                    if (dice <= 5) {
                        System.out.println("Masil won the fight!");
                    } else {
                        System.out.println("OUCH! Masil got beat up! ;_;");
                        removeLife();
                    }
                } catch (InterruptedException ex){
                    System.out.println("GAME ERROR! Masil high tailed it out of the basement!");
                    System.exit(0);
                }
                break;
            case "2":
                System.out.println("Masil decided to run!...");
                try {
                    TimeUnit.SECONDS.sleep(2);
                    if (dice <= 7) {
                        System.out.println("Masil got away safely!");
                    } else {
                        System.out.println("OUCH! Masil got beat up! ;_;");
                        removeLife();
                    }
                } catch (InterruptedException ex){
                    System.out.println("GAME ERROR! Masil high tailed it out of the basement!");
                    System.exit(0);
                }
                break;
        }
        System.out.println("Moving on.... \n");
    }

    private static void processAmbush(String input) {
        int dice = (int) (Math.random() * 10);
        if (input.equalsIgnoreCase("1")) {
            System.out.println("Masil decided to fight!");
            try {
                TimeUnit.SECONDS.sleep(2);
                if (dice <= 6) {
                    System.out.println("Masil beat those bugs up without a scratch!");
                } else {
                    System.out.println("OUCH! Masil got beat up! ;_;");
                    GameState.removeLife();
                }
            } catch (InterruptedException ex) {
                System.out.println("GAME ERROR! Masil high tailed it out of the basement!");
                System.exit(0);
            }
        } else if (input.equalsIgnoreCase("2")) {
            System.out.println("Masil decided to run!");
            try {
                TimeUnit.SECONDS.sleep(2);
                if (dice <= 7) {
                    System.out.println("Masil got away!");
                } else {
                    System.out.println("OUCH! Masil got beat up! ;_;");
                    GameState.removeLife();
                }
            } catch (InterruptedException ex) {
                System.out.println("GAME ERROR! Masil high tailed it out of the basement!");
                System.exit(0);
            }
            System.out.println("Moving on.... \n");
        }
    }

    private static void processTreasure(String input){
        int dice = (int) (Math.random() * 10);
        if(input.equalsIgnoreCase("1")){
            System.out.println("Masil decided to open one of the boxes....");
            try {
                TimeUnit.SECONDS.sleep(2);
                if (dice <= 6) {
                    System.out.println("Hmm...the box was empty.");
                } else if (dice == 7 || dice == 8) {
                    System.out.println("Wow! There was a potion inside! Masil's lives increased by 1!");
                    GameState.addLife();
                } else {
                    System.out.println("Ew there was a bug inside! Masil lost 1 life!");
                    GameState.removeLife();
                }
            } catch (InterruptedException ex){
                System.out.println("GAME ERROR! Masil high tailed it out of the basement!");
                System.exit(0);
            }
        } else if (input.equalsIgnoreCase("2")){
            System.out.println("Masil left the boxes alone. She has other things to do!");
        }
        System.out.println("Moving on.... \n");
    }

    public boolean didPlayerWin() {
        if (dataList.size() == 0 && !user.checkIfDead()) {
            playerWins = true;
        }
        return playerWins;
    }

    public void removeEncounter() {
        if (dataList.size() > 0) {
            ScreensData encounter = dataList.get(0);
            dataList.remove(encounter);
            Collections.shuffle(dataList);
        }
    }
}
