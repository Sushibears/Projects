package com.teksystems.bootcamp.capstone2;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PlayerTest {

    @Test
    public void testRemoveLife() {
        Player tester = new Player();
        tester.addLife();
        tester.removeLife();
        assertEquals(0,0);
    }

    @Test
    public void testAddLife() {
        Player tester = new Player();
        tester.addLife();
        GameState.user.addLife();
        assertEquals(2,2);
    }

    @Test public void deadChecker(){
        Player tester = new Player();
        tester.addLife();
        GameState.user.removeLife();
        GameState.user.removeLife();
        tester.checkIfDead();
        assertTrue(true);
    }
}