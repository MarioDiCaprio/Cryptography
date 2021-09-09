package cryptography.cracking;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * This interface provides ciphers the ability
 * to crack an unknown encrypted message.
 * Methods to decipher a message are frequency
 * and semantic analysis.
 */
@FunctionalInterface
public interface Crackable {

    /**
     * Attempts to crack a message by using brute force.
     * The array returned by this method contains all
     * possible solutions for the encrypted message.
     * @return An array containing all possible solutions for the message
     */
    String[] crack();


    /**
     * Attempts to crack a message by using brute force.
     * This approach uses both semantic and frequency analysis.
     * It is a concatenation of the {@code semanticAnalysis()}
     * and the {@code frequencyAnalysis()} method. The solutions
     * are ordered by correctness in descending order.
     * @param spellChecker The {@link SpellChecker} containing the semantic logic
     * @param frequency The characters to filter by
     * @return An array containing all possible solutions for the message
     */
    default String[] analyse(SpellChecker spellChecker, String frequency) {
        Crackable c = () -> this.semanticAnalysis(spellChecker);
        return  c.frequencyAnalysis(frequency);
    }


    /**
     * Attempts to crack a message by using brute force. In comparison
     * to the {@code crack()} method, this method utilizes semantic logic
     * to interpret each key's spelling. The returned array is sorted by
     * likeliness of being correct descending order. The semantic logic
     * is determined by the given {@link SpellChecker}.
     * @param spellChecker The {@link SpellChecker} containing the semantic logic
     * @return An array containing all possible solutions for the message
     */
    default String[] semanticAnalysis(SpellChecker spellChecker) {
        if (spellChecker == null)
            spellChecker = new SpellChecker();

        String[] crack = crack();
        List<Candidate> list = new LinkedList<>();
        List<String> result = new LinkedList<>();

        for (String str : crack) {
            Candidate c = new Candidate(str);
            list.add(c);

            spellChecker.setText(str);
            if (spellChecker.possibleMistakes() > 0) {
                c.addErrors(1);
            }
        }

        list.stream().sorted().forEach(candidate -> result.add( candidate.getText() ));
        return result.toArray(new String[0]);
    }


    /**
     * Attempts to crack a message by frequency analysis.
     * Each character in the decrypted message that is contained
     * in the given String earns one point. Finally, orders
     * all ciphers based on those points, in descending order.
     * @param chars The characters to filter by
     * @return An array containing all possible solutions for the message
     */
    default String[] frequencyAnalysis(String chars) {
        List<Candidate> list = new LinkedList<>();
        List<String> result = new LinkedList<>();
        char[] arr = chars.toCharArray();
        String[] crack = crack();

        for (String str : crack) {
            Candidate candidate = new Candidate(str);
            list.add(candidate);

            for (char c : arr)
                candidate.addErrors( frequency(str, c) );
        }

        list.stream().sorted(Comparator.reverseOrder()).forEach( candidate -> result.add(candidate.getText() ));
        return result.toArray(new String[0]);
    }


    private static int frequency(String str, char c) {
        str = str.toLowerCase();
        c = Character.toLowerCase(c);
        char[] arr = str.toCharArray();
        int count = 0;

        for (char x : arr)
            count += (x == c)? 1 : 0;

        return count;
    }


    /**
     * Calculates the frequency of each character in the given
     * String. The result is returned as a {@link Map}.
     * @param txt The String to analyze
     * @return A {@link Map} containing the frequency of each character in the given String
     */
    static Map<Character, Integer> characterFrequency(String txt) {
        return
                txt
                .chars()
                .mapToObj(c -> (char) c)
                .collect(
                        Collectors.groupingBy(
                                Function.identity(), Collectors.summingInt(c -> 1)
                        )
                );
    }


    /**
     * Splits the given String into graphs of n characters.
     * For example, the String {@code "Hello, World!"} can
     * be split into: {@code "Hel", "lo,", "Wor", "ld!"}, where
     * n=3. Also, calculates the frequency of each graph in the
     * String and returns the result as a {@link Map}.
     * @param txt The String to analyze
     * @param n The length of each graph
     * @return A {@link Map} containing each graph and their frequency in the String
     */
    static Map<String, Integer> nGraph(String txt, int n) {
        int len = txt.length();
        Map<String, Integer> map = new HashMap<>();
        List<String> list = new LinkedList<>();

        for (int i = 0; i < len; i += n) {
            String s = txt.substring( i, Math.min(i+n, len) );
            list.add(s);
        }

        list.forEach(graph -> {
            int frequency = ( len - txt.replace(graph, "").length() ) / len;
            map.put(graph, frequency);
        });

        return map;
    }


}
