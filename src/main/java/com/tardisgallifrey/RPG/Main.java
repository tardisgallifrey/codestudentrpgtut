
package com.tardisgallifrey.RPG;

public class Main{

    public static void main(String[] args){
        Main.go();
    }

    private static void go(){

        GameLogic game = new GameLogic();

        game.printHeading("Testing helper methods");
        game.anythingToContinue();
        game.clearConsole();
        int input = game.readInt("Enter 1, 2, or 3: ", 3);
    }

}