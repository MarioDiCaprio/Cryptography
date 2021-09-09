package cryptography.cracking;


/**
 * This class represents a spell checker. This class takes a text and
 * analyses its potential mistakes based on semantic correctness. This
 * correctness can be manipulated by adjusting a tolerance for consecutive
 * consonants, which is interrupted by a vowel.
 * <br><br>
 * <h1>Idea</h1>
 * The idea is that, for a word to be pronounced fluently, there needs to be
 * a number of vowels (e.g. 'aeiouy') interrupting a chain of consonants;
 * Suppose we have the word 'crptgrph', which is very hard and unnatural to
 * pronounce. But if we add some vowels, we get: 'cryptography', which is much easier
 * to pronounce.
 * <br><br>
 * <h1>Example</h1>
 * We want to analyse a text in standard english. We first need to create an instance of
 * {@code SpellChecker}:
 * <br><br>
 * {@code SpellChecker spelling = new SpellChecker(myEnglishText);}
 * <br><br>
 * Next, we have to specify the settings for our spell checker:
 * <br><br>
 * {@code // vowels} <br>
 * {@code spelling.setVowels("aeiou");} <br><br>
 * {@code // number of consecutive consonants that are not counted as spelling mistakes} <br>
 * {@code spelling.setConsonantTolerance(3);} <br><br>
 * {@code // characters to exempt from consonant tolerance} <br>
 * {@code spelling.setExcludes(" .;,:\n\t\r");}
 * <br><br>
 * The number of potential spelling mistakes based on the above settings can now
 * be calculated with the following method:
 * <br><br>
 * {@code int mistakes = spelling.possibleMistakes();}
 */
public class SpellChecker {

    ///////////////////////////////////////////////////////////
    ///// fields
    ///////////////////////////////////////////////////////////

    private String text;
    private String excludes = " .;,:-+*/?!%&$_\n\t\r";
    private String vowels = "aeiouy";
    private int consonantTolerance = 3;


    ///////////////////////////////////////////////////////////
    ///// constructor
    ///////////////////////////////////////////////////////////

    /**
     * The default constructor.
     */
    public SpellChecker() {

    }

    /**
     * Builds a SpellChecker and sets the text to analyse.
     * @param text The text to analyse
     */
    public SpellChecker(String text) {
        setText(text);
    }

    ///////////////////////////////////////////////////////////
    ///// methods
    ///////////////////////////////////////////////////////////

    /**
     * Retrieves the text to analyse.
     * @return The text to analyse
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the text to analyse.
     * @param text The text to analyse
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Returns the consonant tolerance. It is the number of consecutive consonants.
     * If this number is exceeded, it is counted as a spelling mistake.
     * @return The consonant tolerance
     */
    public int getConsonantTolerance() {
        return consonantTolerance;
    }

    /**
     * Sets consonant tolerance. It is the number of consecutive consonants.
     * If this number is exceeded, it is counted as a spelling mistake.
     * @param consonantTolerance The consonant tolerance
     */
    public void setConsonantTolerance(int consonantTolerance) {
        this.consonantTolerance = consonantTolerance;
    }

    /**
     * Retrieves the characters to exempt from the consonant tolerance.
     * @return The exempted characters as a String
     */
    public String getExcludes() {
        return excludes;
    }

    /**
     * Sets the characters to exempt from the consonant tolerance.
     * @param excludes The exempted characters as a String
     */
    public void setExcludes(String excludes) {
        this.excludes = excludes;
    }

    /**
     * Retrieves the vowels of the alphabet. By default, they are 'aeiou'.
     * @return The vowels as a String
     */
    public String getVowels() {
        return vowels;
    }

    /**
     * Sets the vowels of the alphabet. By default, they are 'aeiou'.
     * @param vowels The vowels as a String
     */
    public void setVowels(String vowels) {
        this.vowels = vowels;
    }


    /**
     * Returns the number of potential spelling mistakes based on semantic
     * correctness.
     * @return The number of potential mistakes
     */
    public int possibleMistakes() {
        int mistakes = 0;
        int consonant = 0;
        char[] arr = getText().toLowerCase().toCharArray();

        for (char c : arr) {
            if ( vowels.contains("" + c) || excludes.contains("" + c) ) {
                consonant = 0;
            } else {
                consonant++;
            }

            if (consonant > consonantTolerance)
                mistakes++;
        }

        return mistakes;
    }

}
