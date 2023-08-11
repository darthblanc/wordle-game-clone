import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.Arrays;

public class WordleSolverz{
    public static void main (String[] args){

        //Intro
        System.out.println("Hello there!\nI am Andi's WordleSolver v1.0");
        System.out.println("I will be helping you solve your wordle puzzle today :)");

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
        //Read File content to ArrayList
        ArrayList<String> letters = new ArrayList<String>();
        int z = 0;
        while ( scanz.hasNext() ) {
            String stuff = scanz.nextLine();
            letters.add(stuff);
            z++;
        }

        //Random Number Generator
        Random rnd = new Random();
        int first = rnd.nextInt(z);
        System.out.println("Automatic first guess: " + letters.get(first));

        //Read User Input
        Scanner scan = new Scanner(System.in);
        String word = scan.nextLine();

        //Removing all Blanks
        word = word.replaceAll("\s", "");

        //Method Usage
        String t = builder(word);
        String ta = checker(word);
        String tb = subchecker(word);

        //Inclusion Array
        String[] j = new String[ta.length()];
        for(int i = 0; i < ta.length(); i++ ){
            j[i] = ta.substring(i, i+1);
        }

        //Exclusion Array
        String[] k = new String[tb.length()];
        for(int i = 0; i < tb.length(); i++ ){
            k[i] = tb.substring(i, i+1);
        }

        //ArrayList to contain values that meet all set conditions
        ArrayList<String> correct = new ArrayList<String>();

        //Loop through the ArrayList: letters and testing all possible cases
        for(int i = 0; i < z; i++){
            
            if(k.length==0){
                if(letters.get(i).matches(t) && j.length == 0 ){
                   correct.add(letters.get(i));
                } 
            if(letters.get(i).matches(t) && j.length == 1 ){
                if(letters.get(i).contains(j[0])){
               correct.add(letters.get(i));
                }
            } 
            if(letters.get(i).matches(t) && j.length == 2 ){
                if(letters.get(i).contains(j[0]) && letters.get(i).contains(j[1])){
               correct.add(letters.get(i));
                }
            } 
            if(letters.get(i).matches(t) && j.length == 3 ){
                if(letters.get(i).contains(j[0]) && letters.get(i).contains(j[1])  && letters.get(i).contains(j[2])){
               correct.add(letters.get(i));
                }
            } 
            if(letters.get(i).matches(t) && j.length == 4 ){
                if(letters.get(i).contains(j[0]) && letters.get(i).contains(j[1])  && letters.get(i).contains(j[2]) && letters.get(i).contains(j[3])){
               correct.add(letters.get(i));
                }
            } 
            if(letters.get(i).matches(t) && j.length == 5 ){
                if(letters.get(i).contains(j[0]) && letters.get(i).contains(j[1])  && letters.get(i).contains(j[2]) && letters.get(i).contains(j[3]) && letters.get(i).contains(j[4])){
               correct.add(letters.get(i));
                }
            } 
        }
        if(k.length==1){
            if(letters.get(i).matches(t) && j.length == 0 ){
                correct.add(letters.get(i));
            }
            if(letters.get(i).matches(t) && j.length == 1 ){
                if(letters.get(i).contains(j[0]) && ! letters.get(i).contains(k[0])){
               correct.add(letters.get(i));
                }
            } 
            if(letters.get(i).matches(t) && j.length == 2 ){
                if(letters.get(i).contains(j[0]) && letters.get(i).contains(j[1])  && ! letters.get(i).contains(k[0])){
               correct.add(letters.get(i));
                }
            } 
            if(letters.get(i).matches(t) && j.length == 3 ){
                if(letters.get(i).contains(j[0]) && letters.get(i).contains(j[1])  && letters.get(i).contains(j[2]) && ! letters.get(i).contains(k[0])){
               correct.add(letters.get(i));
                }
            } 
            if(letters.get(i).matches(t) && j.length == 4 ){
                if(letters.get(i).contains(j[0]) && letters.get(i).contains(j[1])  && letters.get(i).contains(j[2]) && letters.get(i).contains(j[3]) && ! letters.get(i).contains(k[0])){
               correct.add(letters.get(i));
                }
            } 
            if(letters.get(i).matches(t) && j.length == 5 ){
                if(letters.get(i).contains(j[0]) && letters.get(i).contains(j[1])  && letters.get(i).contains(j[2]) && letters.get(i).contains(j[3]) && letters.get(i).contains(j[4]) && ! letters.get(i).contains(k[0])){
               correct.add(letters.get(i));
                }
            } 
        }
        if(k.length==2){
            if(letters.get(i).matches(t) && j.length == 0 ){
                correct.add(letters.get(i));
             } 
            if(letters.get(i).matches(t) && j.length == 1 ){
                if(letters.get(i).contains(j[0]) && ! letters.get(i).contains(k[0]) && ! letters.get(i).contains(k[1])){
               correct.add(letters.get(i));
                }
            } 
            if(letters.get(i).matches(t) && j.length == 2 ){
                if(letters.get(i).contains(j[0]) && letters.get(i).contains(j[1]) && ! letters.get(i).contains(k[0]) && ! letters.get(i).contains(k[1])){
               correct.add(letters.get(i));
                }
            } 
            if(letters.get(i).matches(t) && j.length == 3 ){
                if(letters.get(i).contains(j[0]) && letters.get(i).contains(j[1])  && letters.get(i).contains(j[2]) && ! letters.get(i).contains(k[0]) && ! letters.get(i).contains(k[1])){
               correct.add(letters.get(i));
                }
            } 
            if(letters.get(i).matches(t) && j.length == 4 ){
                if(letters.get(i).contains(j[0]) && letters.get(i).contains(j[1])  && letters.get(i).contains(j[2]) && letters.get(i).contains(j[3]) && ! letters.get(i).contains(k[0]) && ! letters.get(i).contains(k[1])){
               correct.add(letters.get(i));
                }
            } 
            if(letters.get(i).matches(t) && j.length == 5 ){
                if(letters.get(i).contains(j[0]) && letters.get(i).contains(j[1])  && letters.get(i).contains(j[2]) && letters.get(i).contains(j[3]) && letters.get(i).contains(j[4]) && ! letters.get(i).contains(k[0]) && ! letters.get(i).contains(k[1])){
               correct.add(letters.get(i));
                }
            } 
        }
        if(k.length==3){
            if(letters.get(i).matches(t) && j.length == 0 ){
                correct.add(letters.get(i));
             } 
            if(letters.get(i).matches(t) && j.length == 1 ){
                if(letters.get(i).contains(j[0]) && ! letters.get(i).contains(k[0]) && ! letters.get(i).contains(k[1]) && ! letters.get(i).contains(k[2]) ){
               correct.add(letters.get(i));
                }
            } 
            if(letters.get(i).matches(t) && j.length == 2 ){
                if(letters.get(i).contains(j[0]) && letters.get(i).contains(j[1])  && ! letters.get(i).contains(k[0]) && ! letters.get(i).contains(k[1]) && ! letters.get(i).contains(k[2])){
               correct.add(letters.get(i));
                }
            } 
            if(letters.get(i).matches(t) && j.length == 3 ){
                if(letters.get(i).contains(j[0]) && letters.get(i).contains(j[1])  && letters.get(i).contains(j[2]) && ! letters.get(i).contains(k[0]) && ! letters.get(i).contains(k[1]) && ! letters.get(i).contains(k[2])){
               correct.add(letters.get(i));
                }
            } 
            if(letters.get(i).matches(t) && j.length == 4 ){
                if(letters.get(i).contains(j[0]) && letters.get(i).contains(j[1])  && letters.get(i).contains(j[2]) && letters.get(i).contains(j[3]) && ! letters.get(i).contains(k[0]) && ! letters.get(i).contains(k[1]) && ! letters.get(i).contains(k[2])){
               correct.add(letters.get(i));
                }
            } 
            if(letters.get(i).matches(t) && j.length == 5 ){
                if(letters.get(i).contains(j[0]) && letters.get(i).contains(j[1])  && letters.get(i).contains(j[2]) && letters.get(i).contains(j[3]) && letters.get(i).contains(j[4]) && ! letters.get(i).contains(k[0]) && ! letters.get(i).contains(k[1]) && ! letters.get(i).contains(k[2])){
               correct.add(letters.get(i));
                }
            } 
        }
        if(k.length==4){
            if(letters.get(i).matches(t) && j.length == 0 ){
                correct.add(letters.get(i));
             } 
            if(letters.get(i).matches(t) && j.length == 1 ){
                if(letters.get(i).contains(j[0]) && ! letters.get(i).contains(k[0]) && ! letters.get(i).contains(k[1]) && ! letters.get(i).contains(k[2])   && ! letters.get(i).contains(k[3])){
               correct.add(letters.get(i));
                }
            } 
            if(letters.get(i).matches(t) && j.length == 2 ){
                if(letters.get(i).contains(j[0]) && letters.get(i).contains(j[1])  && ! letters.get(i).contains(k[0]) && ! letters.get(i).contains(k[1]) && ! letters.get(i).contains(k[2])   && ! letters.get(i).contains(k[3])){
               correct.add(letters.get(i));
                }
            } 
            if(letters.get(i).matches(t) && j.length == 3 ){
                if(letters.get(i).contains(j[0]) && letters.get(i).contains(j[1])  && letters.get(i).contains(j[2])  && ! letters.get(i).contains(k[0]) && ! letters.get(i).contains(k[1]) && ! letters.get(i).contains(k[2])   && ! letters.get(i).contains(k[3])){
               correct.add(letters.get(i));
                }
            } 
            if(letters.get(i).matches(t) && j.length == 4 ){
                if(letters.get(i).contains(j[0]) && letters.get(i).contains(j[1])  && letters.get(i).contains(j[2]) && letters.get(i).contains(j[3]) && ! letters.get(i).contains(k[0]) && ! letters.get(i).contains(k[1]) && ! letters.get(i).contains(k[2])   && ! letters.get(i).contains(k[3]) ){
               correct.add(letters.get(i));
                }
            } 
            if(letters.get(i).matches(t) && j.length == 5 ){
                if(letters.get(i).contains(j[0]) && letters.get(i).contains(j[1])  && letters.get(i).contains(j[2]) && letters.get(i).contains(j[3]) && letters.get(i).contains(j[4]) && ! letters.get(i).contains(k[0]) && ! letters.get(i).contains(k[1]) && ! letters.get(i).contains(k[2])   && ! letters.get(i).contains(k[3]) ){
               correct.add(letters.get(i));
                }
            } 
            
           
        }
        if(k.length==5){
            correct.add(letters.get(i));
        }
        
        }

        //Getting output (The Guess)
        Random snd = new Random();
            int sec = snd.nextInt(correct.size());
        System.out.println("Try " + correct.get(sec));
    }    
    
    //Method to generate Regex
    public static String builder(String word ){
        String a = "";

        for(int i = 0; i < word.length(); i+=1){
               
            if(word.substring(i, i+1).equals("|")){
                a += word.substring(i+1,i+2);
                i = i + 2;
            }
            else if(word.substring(i, i+1).equals("*")){
                a += "[^" + word.substring(i+1,i+2) + "]";
                i = i + 2;
            } else { 
                a += "[^" + word.substring(i+1,i+2) + "]" ;
                i+=2;
                a=a.replace("\s", "");
            }
        }
        return a;
    }

    //Method to create Inclusion Array
    public static String checker(String word ){
        String b = "";
        for(int i = 0; i < word.length(); i+=1){
            if(word.substring(i, i+1).equals("*")){
                b += word.substring(i+1,i+2) ;
                i = i + 2;
            } 
        }
        return b;
    }

    //Method to create Exclusion Array
    public static String subchecker(String word ){
        String p = "";
        for(int i = 0; i < word.length(); i+=1){
            if(word.substring(2, 3).equals("_") || word.substring(i, i+1).equals("_")){
                p += word.substring(i+1,i+2) ;
                i = i + 2;
            } 
        }
        return p;
    }

}