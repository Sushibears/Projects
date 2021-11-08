package com.teksystems.bootcamp.capstone2;

public class Player extends Actor{

    public Player() {
        super ("Masil",3);
    }

    public void removeLife(){
        lives -= 1;
    }

    public void addLife(){
        lives += 1;
    }

    public int getLives(){
        return lives;
    }
}
