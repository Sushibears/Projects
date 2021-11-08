package com.teksystems.bootcamp.capstone2;

public abstract class Actor {

    protected int lives;
    protected String name;
    protected boolean isDead = false;

    public Actor(String name, int lives) {
        this.name = name;
        this.lives = lives;
    }

    public boolean checkIfDead(){
        if(lives == 0){
            isDead = true;
            return isDead;
        }
        return isDead;
    }

}
