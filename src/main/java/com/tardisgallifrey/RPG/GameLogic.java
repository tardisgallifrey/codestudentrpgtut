package com.tardisgallifrey.RPG;

import java.util.Scanner;

public class GameLogic {
    static Scanner input = new Scanner(System.in);
    static Player player;
    public static boolean isRunning;

    //Story elements
    public static int place = 0, act;
    public static String[] places = {"Everlasting Mountains", "Haunted Landlines", "Castle of the Evil Emperor", "Throne Room"};

    public static int readInt(String prompt, int userChoice){
        int val;

        
        do{
            System.out.println(prompt);
            try {
                val = input.nextInt();
            }catch(Exception e){
                val = -1;
                System.out.println("Remember to enter an integer!");
            }
        }while(val < 1 || val > userChoice);
        return val;
    }

    public static void clearConsole(){
        for(int i=0; i < 100; i++){
            System.out.println();
        }
    }

    public static void printSeparator(int n){
        for(int i = 0; i < n; i++){
            System.out.print("-");
        }
        System.out.println();
    }

    public static void printHeading(String title){
        printSeparator(30);
        System.out.println(title);
        printSeparator(30);
    }

    public static void anythingToContinue(){
        System.out.println("\nEnter anything to continue...");
        input.next();
    }

    public static void startGame() {
        boolean nameSet = false;
        String name;

        clearConsole();
        printSeparator(40);
        printSeparator(30);
        System.out.println("MY TEXT RPG GAME IS NULL");
        System.out.println("TEXT RPG GAME BY ME");
        printSeparator(30);
        printSeparator(40);

        anythingToContinue();

        do {
            clearConsole();
            printHeading("What's your name?");
            name = input.next();
            clearConsole();
            printHeading("Your name is " + name + ".\nIs that correct?");
            System.out.println("(1) Yes");
            System.out.println("(2) No, I want to change my name.");
            int input = readInt("->", 2);
            if (input == 1) {
                nameSet = true;
            }
        }while (!nameSet) ;

        Story.printIntro();

        isRunning = true;
        player = new Player(name);

        Story.printFirstActIntro();

        gameLoop();
    }

    public static void gameLoop(){
        while(isRunning){
            printMenu();
            int input = readInt("-> ", 3);
            switch(input){
                case 1 -> continueJourney();
                case 2 -> characterInfo();
                case 3 -> isRunning = false;
            }
        }
    }

    public static void characterInfo(){
        clearConsole();
        printHeading("CHARACTER INFO");
        System.out.println(player.name + "\tHP: " + player.hp + "/" + player.maxHp);
        printSeparator(20);
        System.out.println("XP: " + player.xp);

        if(player.numAtkUpgrades > 0){
            System.out.println("Offensive trait: " + player.atkUpGrades[player.numAtkUpgrades]);
            printSeparator(20);
        }

        if(player.numDefUpgrades > 0){
            System.out.println("Defensive trait: " + player.defUpgrades[player.numDefUpgrades]);
            printSeparator(20);
        }

        anythingToContinue();
    }

    public static void printMenu(){
        clearConsole();
        printHeading(places[place]);
        System.out.println("Choose an action:");
        printSeparator(20);
        System.out.println("(1) Continue on your journey");
        System.out.println("(2) Character Info");
        System.out.println("(3) Exit Game");

    }

    public static void continueJourney(){

    }


}
