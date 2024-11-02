package com.tardisgallifrey.RPG;

public class Player extends Character{

    public int numAtkUpgrades, numDefUpgrades;
    
    public String[] atkUpGrades = {"Strength", "Power", "Might", "Godlike Strength"};
    public String[] defUpgrades = {"Heavy Bones", "Stoneskin", "Scale Armor", "Holy Aura"};
    
    public Player(String name){
        super(name, 100, 0);
        this.numAtkUpgrades = 0;
        this.numDefUpgrades = 0;
        chooseTrait();
    }

    private void chooseTrait() {
        GameLogic.clearConsole();
        GameLogic.printHeading("Choose a Trait:");
        System.out.println("(1) " + atkUpGrades[numAtkUpgrades]);
        System.out.println("(2) " + defUpgrades[numDefUpgrades]);

        int input = GameLogic.readInt("-> ", 2);
        GameLogic.clearConsole();

        //IF-ELse here really should be a switch/case
        switch (input) {
            case 1 -> {
                GameLogic.printHeading("You chose " + atkUpGrades[numAtkUpgrades] + "!");
                numAtkUpgrades++;
            }
            case 2 -> {
                GameLogic.printHeading("You chose " + defUpgrades[numDefUpgrades] + "!");
                numDefUpgrades++;
            }
        }
        GameLogic.anythingToContinue();
    }


    @Override
    public int attack() {
        return 0;
    }

    @Override
    public int defend() {
        return 0;
    }
}