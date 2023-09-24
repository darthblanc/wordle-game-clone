import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.*;

public class game {
    private final String answer;
    public ArrayList<String> word_bank = new ArrayList<>();

    public game() throws FileNotFoundException {
        this.answer = get_random_word();
    }

    private String get_random_word() throws FileNotFoundException {
        Scanner file_scan = new Scanner(new File("...\\wordle.txt"));

        while (file_scan.hasNextLine()){
            word_bank.add(file_scan.nextLine());
        }

        int random = new Random().nextInt(word_bank.size());
        return word_bank.get(random);
    }

    private String infer_greeting(){
        LocalDateTime dateTime = LocalDateTime.now();
        int hour = dateTime.getHour();
        if (hour < 12 ){
            return "Morning";
        } else if (hour < 17) {
            return "Afternoon";
        }
        return "Evening";
    }

    private void intro(){
        System.out.println();
        System.out.println("Rules:\nGuess the 5 letter word chosen at runtime");
        System.out.println("|...| indicates correctness of a letter's position\n*...* indicates that the letter exists in the chosen word but has been placed in a wrong position");
        System.out.println("-...- indicates that the letter is not in the word");
        System.out.println("You have 6 tries to get the answer");
        System.out.println("If you guess a word that is not of length 5, it would not count");
        System.out.println("You may only guess real words, or at least words that exist in the muy dictionary");
    }
    public void game_manager() throws FileNotFoundException {
        System.out.println("Good " + infer_greeting() + ",\nI am Andi's Wordle game");
        System.out.print("Would you want to learn the rules? [Y]/[]");
        Scanner scanner = new Scanner(System.in);
        if (scanner.next().equals("Y")){
            intro();
        }
        System.out.println("It's game time... Goodluck!");
        if (play_game(1)){
            System.out.println("Congratulations, Make sure to share your progress with friends");
        }
        else{
            System.out.println("Try again tomorrow");
        }
    }
    private boolean play_game(int tries) throws FileNotFoundException {
        if (tries > 6){
            System.out.println("The word of the day was " + answer);
            return false;
        }
        System.out.print("Enter a word: ");
        Scanner scan = new Scanner(System.in);
        String guess = scan.next();

        if (guess.length() != 5){
            System.out.println("Improper length");
            return play_game(tries);
        } else if (!word_bank.contains(guess)) {
            System.out.println("Word does not exist");
            return play_game(tries);
        }

        String word_correctness = word_correctness_transformation(guess);
        System.out.println(word_correctness);

        if (check_word(guess)){
            System.out.println("You have solved the puzzle in " + tries + " tries");
            return true;
        }
        else {
            System.out.println("Would you like to use the solver [Y]/[]");
            Scanner response = new Scanner(System.in);
            if (response.next().equals("Y")){
                solver solver1 = new solver(word_correctness, word_bank);
                word_bank = solver1.solve();
            }
            return play_game(tries + 1);
        }
    }

    private boolean check_word(String user_guess){
        return user_guess.equals(answer);
    }

    private String word_correctness_transformation(String user_guess){
        StringBuilder rv = new StringBuilder();
        HashMap<Character, Integer> occurrence_map = get_letter_occurrence_map();
        String[] correctness_array = new String[5];
        ArrayList<Integer> neglected_indexes = new ArrayList<>();
        for (int i = 0; i < user_guess.length(); i++) {
            if (user_guess.charAt(i) == answer.charAt(i)){
                correctness_array[i] = "|" + user_guess.charAt(i) + "| ";
                occurrence_map.replace(user_guess.charAt(i), occurrence_map.get(user_guess.charAt(i)) - 1);
                if (occurrence_map.get(user_guess.charAt(i)) == 0){
                    occurrence_map.remove(user_guess.charAt(i));
                }
            }
            else {
                neglected_indexes.add(i);
            }
        }

        for (int index: neglected_indexes){
            if (occurrence_map.containsKey(user_guess.charAt(index))){
                occurrence_map.replace(user_guess.charAt(index), occurrence_map.get(user_guess.charAt(index)), occurrence_map.get(user_guess.charAt(index)) - 1);
                correctness_array[index] = "*" + user_guess.charAt(index) + "* ";
                if (occurrence_map.get(user_guess.charAt(index)) <= 0){
                    occurrence_map.remove(user_guess.charAt(index));
                }
            }
            else {
                correctness_array[index] = "-" + user_guess.charAt(index) + "- ";
            }
        }

        for (String s : correctness_array) {
            rv.append(s);
        }
        return rv.toString();
    }

    private HashMap<Character, Integer> get_letter_occurrence_map(){
        HashMap<Character, Integer> occurrence_map = new HashMap<>();

        for (int i = 0; i < answer.length(); i++) {
            char letter = answer.charAt(i);
            if (occurrence_map.containsKey(letter)){
                occurrence_map.replace(letter, occurrence_map.get(letter), occurrence_map.get(letter) + 1);
            }
            else {
                occurrence_map.put(letter, 1);
            }
        }
        return occurrence_map;
    }
}
