package com.tardisgallifrey.RPG;

import java.util.Scanner;

public class GameLogic {
    static Scanner input = new Scanner(System.in);
    static Player player;
    public static boolean isRunning;

    //Story elements
    public static int place = 0, act = 1;
    public static String[] places = {"Everlasting Mountains", "Haunted Landlines", "Castle of the Evil Emperor", "Throne Room"};

    public static String[] encounters = {"Battle", "Battle", "Battle", "Rest", "Rest"};
    public static String[] enemies = {"Ogre", "Ogre", "Goblin", "Goblin", "Stone Elemental"};

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
        checkAct();
        if(act != 4){
            randomEncounter();
        }

    }

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

    private static void shop() {
    }

    private static void takeRest() {

    }

    private static void randomBattle() {
        clearConsole();
        printHeading("You encountered an evil minded creature.  You'll have to fight it!");
        anythingToContinue();

        //This gave fewer problems with lint
        Enemy enemy = new Enemy(enemies[(int)(Math.random()* enemies.length)], player.xp );
        battle(enemy);

    }

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
                    printHeading("You defeathed the "+enemy.name+"!");
                    player.xp += enemy.xp;
                    System.out.println("You earned "+enemy.xp+" XP!");
                    anythingToContinue();
                    break;
                }
                
            }else if(input == 2){
                    //use potion
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

    private static void playerDied() {
        clearConsole();
        printHeading("You died...");
        printHeading("You earned "+player.xp+" XP on your journey.  Try to earn more next time!");
        System.out.println("Thank you for playing my game.  I hope you enjoyed it!");
        isRunning = false;
    }


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
            finalBattle();
        }
    }

    private static void finalBattle() {
    }


}
