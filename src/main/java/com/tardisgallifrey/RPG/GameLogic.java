package com.tardisgallifrey.RPG;

import java.util.Scanner;

//Fred uses a very large class with all static methods.  It is
//logical to do so, but many of these methods could be
//codes as objects.  Battle especially.
public class GameLogic {
    //class fields and main variables
    static Scanner input = new Scanner(System.in);
    static Player player;
    public static boolean isRunning;

    //Story elements needed, locations, encounter types, enemy types
    public static int place = 0, act = 1;
    public static String[] places = {"Everlasting Mountains", "Haunted Landlines", "Castle of the Evil Emperor", "Throne Room"};
    public static String[] encounters = {"Battle", "Battle", "Battle", "Rest", "Rest"};
    public static String[] enemies = {"Ogre", "Ogre", "Goblin", "Goblin", "Stone Elemental"};

    //get user/player input according to integer controlled menu options
    public static int readInt(String prompt, int userChoice){
        int val;

        //print is better than println here.
        do{
            //print prompt
            System.out.print(prompt);
            //get user value in try/catch block
            try {
                val = input.nextInt();
            }catch(Exception e){
                val = -1;
                System.out.println("Remember to enter an integer!");
            }
        }while(val < 1 || val > userChoice);

        //send value back to caller
        return val;
    }

    //to clear the console.
    public static void clearConsole(){
        for(int i=0; i < 100; i++){
            System.out.println();
        }
    }

    //a print separator to mark off text items
    public static void printSeparator(int n){
        for(int i = 0; i < n; i++){
            System.out.print("-");
        }
        System.out.println();
    }

    //print a heading using separators
    //Heading and separators could be moved to a static class by itself
    public static void printHeading(String title){
        printSeparator(30);
        System.out.println(title);
        printSeparator(30);
    }

    //stop to let player decide or read
    public static void anythingToContinue(){
        System.out.println("\nEnter anything to continue...");
        input.next();
    }

    //start the game by setting defaults, etc.
    public static void startGame() {
        boolean nameSet = false;
        String name;

        clearConsole();
        printSeparator(40);
        printSeparator(30);
        System.out.println("The Evil Empire");
        System.out.println("TEXT RPG GAME BY Fred");
        printSeparator(30);
        printSeparator(40);

        anythingToContinue();

        //get player name
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

        //start the story
        Story.printIntro();

        //keeps game running until it doesn't
        isRunning = true;

        //create player object
        player = new Player(name);

        //begin the story
        Story.printFirstActIntro();

        //run the game loop
        gameLoop();
    }

    //main game loop
    //I prefer switch/case on integer choices, even for just 2 sometimes
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

    //prints character information
    //could be moved to overriden toString() method in Player or Character
    public static void characterInfo(){
        clearConsole();
        printHeading("CHARACTER INFO");
        System.out.println(player.name + "\tHP: " + player.hp + "/" + player.maxHp);
        printSeparator(20);
        System.out.println("XP: " + player.xp+"\tGold: "+player.gold);
        printSeparator(20);
        System.out.println("# of Potions: "+player.pots);
        printSeparator(20);

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

    //print the game menu
    public static void printMenu(){
        clearConsole();
        printHeading(places[place]);
        System.out.println("Choose an action:");
        printSeparator(20);
        System.out.println("(1) Continue on your journey");
        System.out.println("(2) Character Info");
        System.out.println("(3) Exit Game");

    }

    //keep game going until act 4
    public static void continueJourney(){
        checkAct();
        if(act != 4){
            randomEncounter();
        }

    }

    //provide random encounters
    private static void randomEncounter() {

        int encounter = (int) (Math.random() * encounters.length);

        if (encounters[encounter].equals("Battle")) {
            randomBattle();
        } else if (encounters[encounter].equals("Rest")){
            takeRest();
        }else{
            shop();
        }
    }

    //buy stuff
    private static void shop() {
        clearConsole();
        printHeading("You meet a mysterious stranger.\nHe offers you something:");
        int price = (int) (Math.random() * 10) + (player.pots * 3) + 10 + player.pots;
        System.out.println("- Magic Potion: "+price+" gold.");
        printSeparator(20);
        System.out.println("Do you want to buy one?\n(1) Yes!\n(2) No thanks.");
        int input = readInt("-> ", 2);
        //check if player wants to buy
        if(input == 1){
            clearConsole();
            //does player have enough gold?
            if(player.gold >= price){
                printHeading("You bought a magical potion for "+price+" gold.");
                player.pots++;
                player.gold -= price;
            }else{
                printHeading("You don't have enough gold to buy this...");
            }
            anythingToContinue();
        }
    }

    //heal health/hp
    private static void takeRest() {
        clearConsole();
        if(player.restsLeft >= 1){
            printHeading("Do you want to take a rest? ("+player.restsLeft+" rest(s) left).");
            System.out.println("(1) Yes\n(2) No, not now.");
            int input = readInt("-> ", 2);
            if(input == 1){
                //player takes rest
                clearConsole();
                if(player.hp < player.maxHp){
                    int hpRestored = (int) ((Math.random() * ((double) player.xp / 4) + 1) + 10);
                    player.hp += hpRestored;
                    if(player.hp > player.maxHp){
                        player.hp = player.maxHp;
                    }
                    System.out.println("You took a rest and restored up to "+hpRestored+" health.");
                    System.out.println("You are now at "+player.hp+" health.");
                    player.restsLeft--;
                }
            }else{
                System.out.println("You're at full health. You don't need to rest now!");
            }
            anythingToContinue();
        }
    }

    //engage in random battles
    private static void randomBattle() {
        clearConsole();
        printHeading("You encountered an evil minded creature.  You'll have to fight it!");
        anythingToContinue();

        //This gave fewer problems with lint
        Enemy enemy = new Enemy(enemies[(int)(Math.random()* enemies.length)], player.xp );
        battle(enemy);

    }

    //combat resolution
    private static void battle(Enemy enemy) {

        //not using switch/case here because it's too convoluted
        while(true){
            clearConsole();
            printHeading(enemy.name + "\nHP: "+enemy.hp+"/"+enemy.maxHp);
            printHeading(player.name+"\nHP: "+player.hp+"/"+player.maxHp);
            System.out.println("Choose an action:");
            printSeparator(20);
            System.out.println("(1) Fight\n(2) Use Potion\n(3) Run Away");
            int input=readInt("-> ", 3);
            if(input == 1) {
                int dmg = player.attack() - enemy.defend();
                int dmgTook = enemy.attack() - player.defend();

                //don't allow player to have negative dmgTook
                //or zero enemy damage
                if (dmgTook < 0) {
                    dmg -= dmgTook / 2;
                    dmgTook = 0;
                }

                if (dmg < 0) dmg = 0;
                player.hp -= dmgTook;
                enemy.hp -= dmg;
                clearConsole();
                printHeading("BATTLE");
                System.out.println("You dealt "+dmg+" damage to the "+enemy.name+".");
                System.out.println("The "+enemy.name+" dealt "+dmgTook+" damage to you.");
                anythingToContinue();
                //check if player is still alive or dead
                if(player.hp <= 0){
                    playerDied();
                    break;
                }else if(enemy.hp <= 0){
                    clearConsole();
                    printHeading("You defeated the "+enemy.name+"!");
                    player.xp += enemy.xp;
                    System.out.println("You earned "+enemy.xp+" XP!");
                    //random drops
                    boolean addRest = (Math.random()*5 + 1 <= 2.25);
                    int goldEarned = (int)(Math.random()*enemy.xp);
                    if(addRest){
                        player.restsLeft++;
                        System.out.println("You earned the chance to get an additional rest.");
                    }
                    if(goldEarned < 0){
                        player.gold += goldEarned;
                        System.out.println("You collect " + goldEarned + " gold from the " + enemy.name+"'s corpse");
                    }
                    anythingToContinue();
                    break;
                }
                
            }else if(input == 2){
                    //use potion
                clearConsole();
                if(player.pots > 0 && player.hp < player.maxHp){
                    //player can take a potion
                    //make sure player wants to drink a potion
                    printHeading("Do you want to drink a potion? (1) Yes\n(2) No, maybe later");
                    input = readInt("-> ", 2);
                    if(input == 1){
                        //player took it
                        player.hp = player.maxHp;
                        clearConsole();
                        printHeading("You drank a magic potion.  It restored your health to "+player.maxHp);
                        anythingToContinue();
                    }
                }else{
                    //player can't take potion
                    printHeading("You don't have any potions or you're at full health.");
                    anythingToContinue();
                }
                break;
            }else{
                //Run Away
                clearConsole();
                if(act != 4){
                    if(Math.random()*10 <= 3.5){
                        printHeading("You ran away from the "+enemy.name+"!");
                        anythingToContinue();
                        break;
                    }else{
                        printHeading("You didn't manage to escape.");
                        //calculate player damage
                        int dmgTook = enemy.attack();
                        System.out.println("In your hurry, you took "+dmgTook+" damage!");
                        anythingToContinue();
                        //check if player died
                        if(player.hp <= 0){
                            playerDied();
                        }
                    }
                }else{
                    printHeading("YOU CANNOT ESCAPE THE EVIL EMPEROR!!!");
                    anythingToContinue();
                }

            }
        }
    }

    //You died...
    private static void playerDied() {
        clearConsole();
        printHeading("You died...");
        printHeading("You earned "+player.xp+" XP on your journey.  Try to earn more next time!");
        System.out.println("Thank you for playing my game.  I hope you enjoyed it!");
        isRunning = false;
    }


    //check to see which act we are in and do accordingly
    private static void checkAct() {

        if(player.xp >= 10 && act == 1){
            act = 2;
            place = 1;

            Story.printFirstActOutro();

            player.chooseTrait();

            Story.printSecondActIntro();
            enemies[0] = "Evil Mercenary";
            enemies[1] = "Goblin";
            enemies[2] = "Wolf Pack";
            enemies[3] = "Henchman of the Evil Emperor";
            enemies[4] = "Scary Stranger";

            encounters[0] = "Battle";
            encounters[1] = "Battle";
            encounters[2] = "Battle";
            encounters[3] = "Rest";
            encounters[4] = "Shop";


        }else if(player.xp >= 50 && act == 2){
            act = 3;
            place = 2;
            Story.printSecondActOutro();
            player.chooseTrait();
            Story.printThirdActIntro();

            //At this point, change things
            //to those closer to castle
            enemies[0] = "Evil Mercenary";
            enemies[1] = "Evil Mercenary";
            enemies[2] = "Henchman of the Evil Emperor";
            enemies[3] = "Henchman of the Evil Emperor";
            enemies[4] = "Henchman of the Evil Emperor";

            encounters[0] = "Battle";
            encounters[1] = "Battle";
            encounters[2] = "Battle";
            encounters[3] = "Battle";
            encounters[4] = "Shop";

            player.hp = player.maxHp;

        }else if(player.xp >= 100 && act == 3){
            act = 4;
            place = 3;
            Story.printThirdActOutro();
            player.chooseTrait();
            Story.printFourthActIntro();
            player.hp = player.maxHp;

            //It's the final c...no it's not
            //Boss Battle
            finalBattle();
        }
    }

    //Boss Battle
    private static void finalBattle() {
        battle(new Enemy("THE EVIL EMPEROR", 300));
        Story.printEnd(player);
        isRunning = false;
    }


}
