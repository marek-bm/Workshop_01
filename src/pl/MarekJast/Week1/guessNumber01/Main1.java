package pl.MarekJast.Week1.guessNumber01;

import java.util.InputMismatchException;
import java.util.MissingFormatArgumentException;
import java.util.Random;
import java.util.Scanner;

public class Main1 {

    public static void main(String[] args) {
        guessNumber();

    }

    static void guessNumber(){
        Random rand=new Random();
        Scanner scan=new Scanner(System.in);

        int randomInt=rand.nextInt(100)-1;
        Integer guess=null;
        int guessCounter=0;

        System.out.println("Guess integer number from 1-100");

        while(true){

            try {
                guess=scan.nextInt();
                if(guess==randomInt){
                    guessCounter++;
                    System.out.println("Congratulations, You win");
                    System.out.println("Your results is "+guessCounter + "attempts");
                    break;
                } else if (guess<randomInt){
                    System.out.println("Try again. You must input higher integer");
                    guessCounter++;
                } else {
                    System.out.println("Try again. You must input lower number");
                    guessCounter++;
                }
            } catch (InputMismatchException e) {
                System.out.println("Input should be integer");
                scan.next();
            }
        }

    }

}
