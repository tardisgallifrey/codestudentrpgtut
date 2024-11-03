package com.tardisgallifrey.RPG;

public class Story {

    public static void printIntro(){
        GameLogic.clearConsole();
        GameLogic.printSeparator(30);
        System.out.println("Introduction");
        GameLogic.printSeparator(30);

        String introtext = """
        You are the last man standing after your village was raided by the henchmen
        of the evil emperor.  Every single one of your friends, family, and
        neighbors were murdered.  You are standing in the burning ruins of
        this once great village.  All you want now is revenge, so you begin
        planning your journey to defeat the evil emperor and free the lands!
        """;
        System.out.println(introtext);

        GameLogic.anythingToContinue();
    }

    public static void printFirstActIntro(){
        GameLogic.clearConsole();
        GameLogic.printSeparator(30);
        System.out.println("ACT I - INTRO");
        GameLogic.printSeparator(30);
        String act1intro = """
        As you begin your journey you start travelling through the nearby woods
        to reach the everlasting mountains.  THe everlasting mountains are a very
        dangerous place.  It says nobody comes back alive from there.  After a
        long day of walking through the woods, you finally reach the everlasting
        mountains.  You don't care about the things you've heard about this dangerous
        place and start your journey to defeat the evil emperor.
        """;

        System.out.println(act1intro);

        GameLogic.anythingToContinue();
    }

    public static void printFirstActOutro(){
        GameLogic.clearConsole();
        GameLogic.printSeparator(30);
        System.out.println("ACT I - CONCLUSION");
        GameLogic.printSeparator(30);
        String act1ending = """
        You did it!  You crossed the everlasting mountains and you're still
        alive!  As you climb down the last hill, you're more than happy to feel
        hard ground underneath your feet again.
        """;

        System.out.println(act1ending);

        GameLogic.anythingToContinue();
    }

    public static void printSecondActIntro(){
        GameLogic.clearConsole();
        GameLogic.printSeparator(30);
        System.out.println("ACT II - INTRO");
        GameLogic.printSeparator(30);
        String act2intro = """
        You begin travelling across the landlines of this once well populated
        countryside.  You collected some gold from the monsters you killed
        along the way.  Luckily, you know that every once in a while a travelling
        trader comes across these landlines.  You know exactly where the
        castle of the evil emperor is, but you have to cross these
        haunted landlines first.
        """;
        System.out.println(act2intro);
        GameLogic.anythingToContinue();
    }

    public static void printSecondActOutro(){
        GameLogic.clearConsole();
        GameLogic.printSeparator(30);
        System.out.println("ACT II - CONCLUSION");
        GameLogic.printSeparator(30);

        String act2ending = """
        You managed to cross these haunted landlines and you're still alive!
        You are just a few hours away from your final destination; the castle
        of the Evil Emperor!  You know that you probably can't rest there,
        so you make a last break to restore some health.  After all you've
        seen you feel empowered to learn another trait.
        """;
        System.out.println(act2ending);
        GameLogic.anythingToContinue();
    }

    public static void printThirdActIntro(){
        GameLogic.clearConsole();
        GameLogic.printSeparator(30);
        System.out.println("ACT III - INTRO");
        GameLogic.printSeparator(30);
        String act3intro = """
        You came so far.  You defeated all of the Evil Emperor's minions.
        As you stand in front of the door to his throne room, you know
        there is no going back.  You recall your lost power and restore
        your health.  You get the chance to learn a last trait before
        entering the throne room.
        """;

        System.out.println(act3intro);
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
        String act4intro = """
        You enter the throne room of the Evil Emperor.  He stares you dead in the
        eyes.  You feel the darkness all round you.  He takes out the holy
        sword of darkness, the mightiest weapon known to man.  All you can do
        now is fight for your ife and pray to come out as the winner...
        """;
        System.out.println(act4intro);
        GameLogic.anythingToContinue();
    }

    public static void printEnd(Player player) {
        GameLogic.clearConsole();
        GameLogic.printSeparator(30);
        System.out.println("Congratulations, "+player.name+"!  You defeated the Evil Emperor and saved the world!");
        GameLogic.printSeparator(30);
        System.out.println("This game was developed by Fred Diekmann for codestudent.net");
        System.out.println("I hope you enjoyed it!");
    }
}
