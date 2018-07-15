package pl.MarekJast.Week1.diceNumber04;

import java.util.Random;

public class Main4 {

    public static void main(String[] args) {
        System.out.println("Welcome to dice simulator. Enter input according following rules");
        System.out.println("xDy+z where:");
        System.out.println("x = number of throws");
        System.out.println("y = type of dice. For cube 6 (D6), hex 8 (D8), etc");
        System.out.println("+z number added or substracted (optional entry)");
        System.out.println();

        String dice="D10";
        System.out.print(dice+" = ");
        throwDice(dice);

    }

    //method recognizing selected dice and return display thrown number
    static void throwDice(String dice){

        if (!dice.contains("D")){
            System.out.println("Invalid dice format. Please follow the instruction");
        }
        else{
            Random r = new Random();
            int x=recognizeThrows(dice);
            int y=r.nextInt(recognizeDice(dice)+1);
            int z=recognizeSuffix(dice);
            char operator=recognizeOperator(dice);
            int result=0;

            if(operator=='+'){
                result=x*y+z;
                System.out.println(result);
            } else if (operator=='-'){
                result=x*y-z;
                System.out.println(result);
            } else {
                result=x*y;
                System.out.println(result);
            }
        }


    }

    //method recognizing number of throws (parameter x)
    static int recognizeThrows(String str){
        String diceThrows="";
        char a=' ';
        int counter=0;
        int nr=1;

        while(str.charAt(counter)!='D'){
            if(str.charAt(counter)=='D'){
                a='1';
                break;
            }
            else{
                a=str.charAt(counter);
                diceThrows=diceThrows+a;
                counter++;
            }
            nr=Integer.parseInt(diceThrows);
        }
        return nr;
    }

    // method regognizing the dice cube, hex etc  (parameter y)
    static int recognizeDice(String st) {
        String[] splited = st.split("D");
        char tmp = ' ';
        String nr = "";
        int index = 0;
        int dice = 0;

        try {
            if (splited[1].contains("+") || splited[1].contains("-")) {
                for (int i = 0; i < splited[1].length(); i++) {
                    while (splited[1].charAt(index) != '+' && splited[1].charAt(index) != '-') {
                        nr = nr + splited[1].charAt(index);
                        index++;
                    }
                }
            } else {
                for (int i = 0; i < splited[1].length(); i++) {
                    nr = nr + splited[1].charAt(index);
                    index++;
                }
            }

            dice=Integer.parseInt(nr);

        } catch (Exception e) {
            System.out.println("Dice size format incorrect");;
        }


        return dice;

    }



    //method recognizing suffix (parameter z)
    static int recognizeSuffix(String str){
        int suffix=0;

        try {
            if (str.contains("+") || str.contains("-")) {
                String[] splitted = str.split("D");
                String x = splitted[0];
                String y = splitted[1];
                String tmpNr = "";

                if (y.contains("+")) {
                    String[] subSplit = y.split("\\+");

                    tmpNr = subSplit[1];
                } else if (str.contains("-")) {
                    String[] subSplit = y.split("\\-");
                    tmpNr = subSplit[1];
                }
                suffix = Integer.parseInt(tmpNr);
            } else {
                suffix = 0;
            }
        }
        catch (Exception e) {
            System.out.println("Repeat the throw or remove math operator at suffix");
            System.out.print("Current throw shows: ");
        }

            return suffix;
    }




    // method recognizing operator (+/-) for dice modifier 'z'
    static char recognizeOperator(String str){
        char operator=' ';
        int index=0;

        if (str.contains("+")||str.contains("-")) {
            while(str.charAt(index)!='+' && str.charAt(index)!='-') {
                for (int i = 0; i < str.length(); i++) {
                    if (str.charAt(index) == '+' || str.charAt(index) == '-') {
                        char tmpChar = str.charAt(index);
                        operator = tmpChar;
                        break;
                    } else {
                        index++;
                    }
                }
            }
        }
        else {
            operator=' ';
        }

        return operator;
    }
}



