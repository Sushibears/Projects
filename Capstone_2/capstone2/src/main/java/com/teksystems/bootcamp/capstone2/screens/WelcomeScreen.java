package com.teksystems.bootcamp.capstone2.screens;

public class WelcomeScreen extends GameScreen {
    private static final String initialtext =  "   **** *-*-*-*-* **** \n** BASEMENT BEAT DOWN ** \n" +
            "   **** *-*-*-*-* ****";
    private static final String prompt = "-- For instructions, check the README file (Capstone 2 -> capstone 2 -> src -> README) -- \n" +
            "1 - START GAME | 2 - QUIT GAME";

    public WelcomeScreen() {
        super(initialtext,prompt);
    }

    public static WelcomeScreen getInstance() {
        return new WelcomeScreen();
    }

    @Override
    protected GameScreen nextScreen(String input) {
        if(input.equalsIgnoreCase("1")) {
            return BeginningScreen.getInstance();
        }
        if(input.equalsIgnoreCase("2")){
            String resultMessage = "Shutting game down.....";
            String endMessage = String.format("%s%n%s",resultMessage,"See you next time!");
            return EndScreen.getInstance(endMessage);
        } else {
            System.out.println("Please press 1 or 2 on your keyboard!");
        }
        return this;
    }
}
