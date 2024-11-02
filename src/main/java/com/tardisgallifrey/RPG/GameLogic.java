package com.tardisgallifrey.RPG;

import java.util.Scanner;

public class GameLogic {
    Scanner input = new Scanner(System.in);
    
    public int readInt(String prompt, int userChoice){
        int val;
        
        do{
            System.out.println(prompt);
            try {
                val = this.input.nextInt();
            }catch(Exception e){
                val = -1;
                System.out.println("Remember to enter an integer!");
            }
        }while(val < 1 || val > userChoice);
        return val;
    }

    public void clearConsole(){
        for(int i=0; i < 100; i++){
            System.out.println();
        }
    }

    public void printSeparator(int n){
        for(int i = 0; i < n; i++){
            System.out.print("-");
        }
        System.out.println();
    }

    public void printHeading(String title){
        this.printSeparator(30);
        System.out.println(title);
        this.printSeparator(30);
    }

    public void anythingToContinue(){
        System.out.println("\nEnter anything to continue...");
        this.input.next();
    }
}
