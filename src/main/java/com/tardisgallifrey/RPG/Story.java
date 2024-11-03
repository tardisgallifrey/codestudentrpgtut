package com.tardisgallifrey.RPG;

public class Story {

    public static void printIntro(){
        GameLogic.clearConsole();
        GameLogic.printSeparator(30);
        System.out.println("Introduction");
        GameLogic.printSeparator(30);

        //TODO: print story contents here

        GameLogic.anythingToContinue();
    }

    public static void printFirstActIntro(){
        GameLogic.clearConsole();
        GameLogic.printSeparator(30);
        System.out.println("ACT I - INTRO");
        GameLogic.printSeparator(30);

        //TODO: print story contents here

        GameLogic.anythingToContinue();
    }

    public static void printFirstActOutro(){
        GameLogic.clearConsole();
        GameLogic.printSeparator(30);
        System.out.println("ACT I - CONCLUSION");
        GameLogic.printSeparator(30);

        //TODO: print story contents here

        GameLogic.anythingToContinue();
    }

    public static void printSecondActIntro(){
        GameLogic.clearConsole();
        GameLogic.printSeparator(30);
        System.out.println("ACT II - INTRO");
        GameLogic.printSeparator(30);

        //TODO: print story contents here

        GameLogic.anythingToContinue();
    }

    public static void printSecondActOutro(){
        GameLogic.clearConsole();
        GameLogic.printSeparator(30);
        System.out.println("ACT II - CONCLUSION");
        GameLogic.printSeparator(30);

        //TODO: print story contents here

        GameLogic.anythingToContinue();
    }

    public static void printThirdActIntro(){
        GameLogic.clearConsole();
        GameLogic.printSeparator(30);
        System.out.println("ACT III - INTRO");
        GameLogic.printSeparator(30);

        //TODO: print story contents here

        GameLogic.anythingToContinue();
    }

    public static void printThirdActOutro(){
        GameLogic.clearConsole();
        GameLogic.printSeparator(30);
        System.out.println("ACT III - CONCLUSION");
        GameLogic.printSeparator(30);

        //TODO: print story contents here

        GameLogic.anythingToContinue();
    }

    public static void printFourthActIntro(){
        GameLogic.clearConsole();
        GameLogic.printSeparator(30);
        System.out.println("ACT IV - INTRO");
        GameLogic.printSeparator(30);

        //TODO: print story contents here

        GameLogic.anythingToContinue();
    }

    public static void printFourthActOutro(){
        GameLogic.clearConsole();
        GameLogic.printSeparator(30);
        System.out.println("STORY CONCLUSION");
        GameLogic.printSeparator(30);

        //TODO: print story contents here

        GameLogic.anythingToContinue();
    }


    public static void printEnd(Player player) {
        System.out.println("CONGRATULATIONS! You defeated THE EVIL EMPEROR!");
        GameLogic.characterInfo();
        System.out.println("The name of "+player.name+" will become legendary!");
    }
}
