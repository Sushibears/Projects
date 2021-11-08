package com.teksystems.bootcamp.capstone2;

import org.testng.Assert;
import org.testng.annotations.Test;

public class GameStateTests {

    @Test
    public void getGameStateInstanceTest(){
        GameState actual = GameState.getInstance();
        GameState expected = GameState.getInstance();
        Assert.assertEquals(actual, expected);
    }
}
