package com.teksystems.bootcamp.capstone2.data;

import com.teksystems.bootcamp.capstone2.Actor;

import java.util.ArrayList;

public abstract class ScreensData {
    String name = "";
    int myLevel = 0;
    boolean roomCleared = false;
    ArrayList<Actor> monsterList = new ArrayList<>();

    public void startRoom() {
    }

    public boolean finishedRoom() {
        return roomCleared;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public int getMyLevel() {
        return myLevel;
    }

    public void setMyLevel(int levelNum) {
        myLevel = levelNum;
    }

    public ArrayList<Actor> getMonsterList() {
        return null;
    }

    public Actor getMonster(int i) {
        return null;
    }

    public void monstersAct(){}

    public void removeDeadMonster(Actor Monster) {
    }

    public abstract void getInput(int i);
}
