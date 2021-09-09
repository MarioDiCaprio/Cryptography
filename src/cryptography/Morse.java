package cryptography;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * This class represents the Morse cipher.
 * This cipher does not extend from {@link SubstitutionCipher}
 * simply because it does not use a concrete alphabet, as
 * each character corresponds to a sequence of characters,
 * rather than a single one. Encryption is done with a
 * {@link java.util.HashMap} that links each character in
 * the latin alphabet to a unique sequence of characters.
 */
public class Morse extends Cipher {

    ///////////////////////////////////
    ///// constructor
    ///////////////////////////////////


    // english -> morse
    private static final Map<Character, String> from = Map.ofEntries(
            Map.entry('a', ".-"),
            Map.entry('b', "-..."),
            Map.entry('c', "-.-."),
            Map.entry('d', "-.."),
            Map.entry('e', "."),
            Map.entry('f', "..-."),
            Map.entry('g', "--."),
            Map.entry('h', "...."),
            Map.entry('i', ".."),
            Map.entry('j', ".---"),
            Map.entry('k', "-.-"),
            Map.entry('l', ".-.."),
            Map.entry('m', "--"),
            Map.entry('n', "-."),
            Map.entry('o', "---"),
            Map.entry('p', ".--."),
            Map.entry('q', "--.-"),
            Map.entry('r', ".-."),
            Map.entry('s', "..."),
            Map.entry('t', "-"),
            Map.entry('u', "..-"),
            Map.entry('v', "...-"),
            Map.entry('w', ".--"),
            Map.entry('x', "-..-"),
            Map.entry('y', "-.--"),
            Map.entry('z', "--.."),

            Map.entry('0', ".----"),
            Map.entry('1', "..---"),
            Map.entry('2', "...--"),
            Map.entry('3', "....-"),
            Map.entry('4', "....."),
            Map.entry('5', "-...."),
            Map.entry('6', "--..."),
            Map.entry('7', "---.."),
            Map.entry('8', "----."),
            Map.entry('9', "-----"),

            Map.entry(' ', "/")
    );


    // morse -> english
    private static final Map<String, Character> to =
                                                from
                                                .entrySet()
                                                .stream()
                                                .collect(
                                                        Collectors.toMap(
                                                                Map.Entry::getValue,
                                                                Map.Entry::getKey
                                                        )
                                                );

    ///////////////////////////////////
    ///// constructor
    ///////////////////////////////////

    /**
     * The default constructor.
     */
    public Morse() {
        super();
    }

    /**
     * Creates an instance of {@code Morse} and sets the
     * text that should be worked with.
     * @param txt The text
     */
    public Morse(String txt) {
        super(txt);
    }


    ///////////////////////////////////
    ///// methods
    ///////////////////////////////////

    /**
     * Encrypts the text that is being worked with.
     *
     * @return The encrypted text
     */
    @Override
    public String encrypt() {
        StringBuilder result = new StringBuilder();
        char[] arr = getText().toCharArray();
        for (char c : arr) {
            c = Character.toLowerCase(c);
            String str = from.get(c);
            if (str == null) {
                continue;
            }
            result.append(str).append(" ");
        }
        result = new StringBuilder(result.substring(0, result.length() - 1));
        setText(result.toString());
        return result.toString();
    }

    /**
     * Decrypts the text that is being
     * worked with.
     *
     * @return The decrypted text
     */
    @Override
    public String decrypt() {
        StringBuilder result = new StringBuilder();
        StringBuilder tmp = new StringBuilder();
        char[] arr = (getText() + " ").toCharArray();
        for (char c : arr) {
            switch (c) {
                case ' ':
                    if (!tmp.toString().equals("")) {
                        result.append(to.get(tmp.toString()));
                        tmp = new StringBuilder();
                    }
                    break;
                case '/':
                    result.append(" ");
                    tmp = new StringBuilder();
                    break;
                default:
                    tmp.append(c);
            }
        }
        setText(result.toString());
        return result.toString();
    }

}
