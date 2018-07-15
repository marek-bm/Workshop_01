package pl.MarekJast.Week1.guessNumber03;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main3 {

    public static void main(String[] args) {

        // method asking player for number, can be used stand alone
        //provideNumber();

        //method where computer has 10 round to guess given number x
        // x can enter direclty or using method 'provide number'
        compuerGuess(500);

        //main method contains methods 'provide number' and 'computer Guess'
        //computerGuessNumber();



    }

    static void computerGuessNumber(){
       int number= provideNumber();
       compuerGuess(number);

    }

// Wersja moja
//    static int provideNumber(){
//        Scanner sc=new Scanner(System.in);
//        System.out.println("Enter a number from 1 to 1000");
//        int number=sc.nextInt();
//
//        try {
//            while(number<1 || number>1000){
//                System.out.println("You entered invalid number");
//                number=sc.nextInt();
//                if(true){
//                    break;
//                }
//            }
//
//        }
//        catch (InputMismatchException e) {
//            System.out.println("You must enter a number");
//        }
//
//        return number;
//    }


// wersja Darka
    static int provideNumber(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter a number from 1 to 1000");

        int number=-1;
        while(number<1 || number>1000){
            try {
                int tmpNumber=sc.nextInt();
                number = tmpNumber;

            }
            catch (InputMismatchException e) {
                System.out.println("You must enter a valid number");
                sc.next();
            }

        }
        return number;
    }


    //method for computer to guess
    static void compuerGuess(int x){
        int lowLimit=1;
        int upLimit=1000;

        Random rand=new Random();
        int compGuess=-1;

        int roundCounter=1;

        for(int i=0; i<roundCounter;i++){
            System.out.println("Round "+(roundCounter));
            compGuess=rand.nextInt(upLimit)+lowLimit;
            System.out.println("Computer guess is " +compGuess);
            System.out.println("Is it correct?");
            String answer=dialogue();

            if (answer.equals("correct")){
                System.out.println("Computer won!");
                break;
            } else if(answer.equals("less")){
                lowLimit=compGuess;
                roundCounter++;
            } else {
                upLimit=compGuess;
                roundCounter++;
            }
            if (roundCounter==11){
                System.out.println("You are the winner!");
                break;
            }
        }




    }


    //method providing answer to computer
    static String dialogue() {
        Scanner sc = new Scanner(System.in);


        System.out.println("Enter less, more or correct");
        String st = " ";
        while (!(st.equals("less")) && !(st.equals("more")) && !(st.equals("correct"))) {
            try {
                String tmp = sc.nextLine();
                st = tmp;
            } catch (InputMismatchException e) {
                System.out.println("Your enter is invalid");
                sc.next();
            }
        }
        System.out.println(st);
        return st;

    }



}
