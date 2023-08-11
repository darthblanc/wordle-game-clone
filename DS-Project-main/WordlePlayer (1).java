import java.io.File;
import java.util.Scanner;
import java.util.Random;
import java.io.FileNotFoundException;

public class WordlePlayer{
    public static void main (String[] args){
        
        //Intro
        System.out.println("Hello there!\nI am Andi's WordlePlayer v1.0");
        System.out.println("You have six tries to get the word I have guessed\n Goodluck!");

        //File Import
        File inputFile = new File("C:/Users/Andi/Downloads/Wordle/wordle.txt");

        //File Reading
        Scanner scanz = null;
        try {
            scanz = new Scanner(inputFile);
        } catch (FileNotFoundException e) {
            System.err.println(e);
            System.exit(1);
        }
        //Read File Input to Array
        int z = 0;
        String[] letters = new String [20000];
        while ( scanz.hasNext() ) {
            String wordz = scanz.next();
            
            letters[z] = wordz;
            z++;
        }

        Random rnd = new Random();
        int guess = rnd.nextInt(z);


        String mall = letters[guess];
        String stuff = "";

        Boolean wonGame = false;

        //Loop for six tries user gets
        for(int p = 0; p <= 6; p++){

            //User Input
            Scanner scan = new Scanner(System.in);
            String word = "";

            Boolean wordInList = false;

            //Checking if Input meets conditions plus Feedback
            while (!wordInList) {
                word = scan.nextLine();
                for (int i = 0; i < letters.length; i++) {
                    if (word.equals(letters[i])) {
                        wordInList = true;
                        break;
                    }
                }
                if (!wordInList) {
                    System.out.println("Word is not in the list / Not correct length!");
                }
            }

            if (mall.equals(word)){
                wonGame = true;
                System.out.println("Congratulations! You Win!");
                System.out.println("The word of the day is " + mall);
                break;
            }

            
            if(word.length()==5) {
                for(int i = 0; i < 5; i++){
                    stuff = word.substring(i, i+1);

                    if(mall.substring(i,i+1).equals(stuff)){
                    
                        System.out.print("|" + stuff + "|" + " ");
                    }
                    if(! mall.substring(i,i+1).equals(stuff) ){
                        if(mall.contains(word.substring(i,i+1))){
                        stuff = word.substring(i, i+1);
                        System.out.print("*" + stuff + "*" + " ");
                        }
                        else{
                            stuff = word.substring(i, i+1);
                            System.out.print("_" + stuff + "_" + " ");
                        }
                    }
                }
                System.out.println(" ");
            } else {
                System.out.println("Improper length!");
                p-=1;
            }

            if(p == 6){
                System.out.println("The correct word is " + " " + mall);
            }
           
        }
    }
}