import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.*;

public class solver {
    String input;
    ArrayList<String> word_bank;
    public solver(String input, ArrayList<String> word_bank){
        this.input = input;
        this.word_bank = word_bank;
    }

    private String find_answer(){
        String[] clues = this.input.split(" ");
        String regex_ = "";
        ArrayList<String> answers = new ArrayList<>();
        String clean_input = "";
        for (String clue : clues) {
            if (clue.charAt(0) == '|') {
                regex_ += clue.charAt(1);
                clean_input += clue.charAt(1);
            } else if (clue.charAt(0) == '-') {
                regex_ += "[^" + clue.charAt(1) + "]";
            } else {
                clean_input += clue.charAt(1);
                regex_ += "[^" + clue.charAt(1) + "]";
            }
        }
        Pattern pattern = Pattern.compile(regex_);

        for (String word: this.word_bank){
            Matcher matcher = pattern.matcher(word);
            if (matcher.find() && !word.equals(this.input)){
                answers.add(word);
            }
        }

        if (answers.size() != 0) {
            filter(answers, clean_input);
        }

        return this.word_bank.get(new Random().nextInt(this.word_bank.size()));
    }

    private HashMap<Character, Integer> vectorize(String word){
        HashMap<Character, Integer> rv = new HashMap<>();

        for (int i = 0; i < word.length(); i++) {
            if (rv.containsKey(word.charAt(i))){
               rv.replace(word.charAt(i), rv.get(word.charAt(i)) + 1);
            } else {
                rv.put(word.charAt(i), 1);
            }
        }
        return rv;
    }
    private void filter(ArrayList<String> answers, String clean_input){
        ArrayList<HashMap<Character, Integer>> answers_vectors = new ArrayList<>();
        HashMap<Character, Integer> input_vector = vectorize(clean_input);
        ArrayList<String> rv = new ArrayList<>();

        for (String word: answers){
            answers_vectors.add(vectorize(word));
        }

        for (int i = 0; i < answers_vectors.size(); i++){
            if (compare_vectors(input_vector, answers_vectors.get(i))){
                rv.add(answers.get(i));
            }
        }
        this.word_bank = rv;
    }

    private boolean compare_vectors(HashMap<Character, Integer>input_vector, HashMap<Character, Integer>answer_vector){
        for (Map.Entry<Character, Integer> entry: input_vector.entrySet()){
            if (answer_vector.containsKey(entry.getKey())){
                if (!Objects.equals(answer_vector.get(entry.getKey()), entry.getValue())){
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }
    public ArrayList<String> solve() throws FileNotFoundException {
        System.out.println("Try: " + find_answer());
        return this.word_bank;
    }
}
