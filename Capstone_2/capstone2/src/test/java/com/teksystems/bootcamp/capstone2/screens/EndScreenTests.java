package com.teksystems.bootcamp.capstone2.screens;

import org.testng.Assert;
import org.testng.annotations.Test;

public class EndScreenTests {
    @Test
    public void endScreenTest(){

        Class actual = EndScreen.getInstance("Hello").getClass();
        Class expected = EndScreen.class;
        Assert.assertEquals(actual, expected);
    }
}
