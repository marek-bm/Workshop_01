package computerGuessing;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main3a {

    public static void main(String[] args) {
        System.out.println("Welcome in guess number game");
        System.out.println("Think about number 1 to 1000 and computer will try to guess it in 10 turns");
        System.out.println();
        compGuess();

    }


    static String compGuess() {
        int min = 1;
        int max = 1000;
        int counter = 1;
        String result="";

        while (counter < 11) {

            int guess = (max - min) / 2 + min;

            System.out.println("Round " + counter);
            System.out.println("I guess your number is " + guess + " is it correct? (yes/no)");
            String answer = dialogue();

            if (answer.equals("yes")) {
                result="Computer won!";
                System.out.println(result);
                break;
            }
            else {
                System.out.println("Is your number higher? (yes/no)");
                String answer2 = dialogue();

                if (answer2.equals("yes")) {
                    min = guess;
                    counter++;

                } else {
                    System.out.println("So I guess your number must be lower? (yes/no)");
                    String answer3 = dialogue();
                    while(answer3.equals("no")){
                        System.out.println("Don't cheat!");
                        System.out.println("I guess your number is " + guess + " is it correct? (yes/no)");
                        answer3=dialogue();

                    }
                    if(answer3.equals("yes")){
                        max=guess;
                        counter++;
                    }

                }
            }
        }
        return result;
    }



        //method providing answer to computer
        static String dialogue(){
            Scanner sc = new Scanner(System.in);

            String st = " ";
            while (!(st.equals("yes")) && !(st.equals("no"))) {
                try {
                    String tmp = sc.nextLine().toLowerCase();
                    st = tmp;
                } catch (InputMismatchException e) {
                    System.out.println("Your enter is invalid");

                }
            }
            //System.out.println(st);
            return st;
        }

    }

