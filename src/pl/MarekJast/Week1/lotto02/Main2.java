package pl.MarekJast.Week1.lotto02;

import java.lang.reflect.Array;
import java.util.*;

public class Main2 {

    public static void main(String[] args) {

        lottoGame();

    }

    static void lottoGame(){
        int[] lotto=tableGenerator();
        int[] user=getUserNumbers();

        int counter=0;

        for(int i=0; i<lotto.length; i++){
            for(int j=0; j<user.length; j++){
                if (lotto[i]==user[j]){
                        counter++;
                }
            }
        }

        if(counter==3){
            System.out.println("You guesses 3 number");
        } else if (counter==4){
            System.out.println("You guesses 4 numbers");
        } else  if (counter==5){
            System.out.println( "You guesses 5 numbers");
        } else if (counter==6){
            System.out.println("You guessed 6 numbers");
        }
        
    }


    //method generating 6 random numbers
    static int[] tableGenerator(){
        int[] table=new int[6];

        Random rand=new Random();

        for(int i=0; i<table.length; i++){
            int draw=rand.nextInt(49)-1;
            table[i]=draw;

            for(int j=0; j<table.length; j++ ){
                if (draw==table[j]);
                rand.nextInt();
            }
        }
        System.out.println(Arrays.toString(table));
        return table;
    }

    //method asking for 6 numbers input by user
    static int[] getUserNumbers(){

        int[] userInput=new int[6];
        int tmp=0;

        for(int i=0; i<userInput.length; i++){
            while(true){
                tmp=getNumber();
                if( checkDuplicates(userInput,tmp)  == false){
                    break;
                }
            }
            userInput[i]=tmp;
        }

        System.out.println(Arrays.toString(userInput));
        return userInput;
    }

    //Method asking user for input
    static int getNumber(){
        Scanner sc=new Scanner(System.in);
        int num= 0;

            while (num<1 || num>49){
                System.out.println("Enter a number 1-49");
                try {
                    num = sc.nextInt();
                    if (num<1 || num>49){
                        System.out.println("Enter a valid number");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("You must enter a number");
                    sc.next();
                }
            }
        return num;
    }

    //method checking duplicates
    static boolean checkDuplicates(int[] tab, int x){
        boolean decision=true;

        for(int i=0; i<tab.length; i++){
            if (x==tab[i]){
                return decision=true;
            }else return decision=false;
        }
        return decision;
    }
}



