package cryptography;

/**
 * This class contains different alphabets.
 * Note that this class cannot be instantiated.
 * The different alphabets can be retrieved with
 * this class' methods.
 */
public final class Alphabet {

    /////////////////////////////////////////////////
    ///// constructor
    /////////////////////////////////////////////////

    /**
     * Do not instantiate this class
     */
    private Alphabet() {

    }


    /////////////////////////////////////////////////
    ///// methods
    /////////////////////////////////////////////////

    /**
     * Returns the lowercase english alphabet.
     * @return The english alphabet
     */
    public static String english() {
        return "abcdefghijklmnopqrstuvwxyz";
    }


    /**
     * Returns the complete ASCII alphabet. All characters
     * are determined by the {@code ASCII.get(int i)} method.
     * @return The complete ASCII alphabet
     */
    public static String ascii() {
        int i=0;
        StringBuilder b = new StringBuilder();

        while (i < 128) {
            b.append( ASCII.get(i) );
            i++;
        }

        return b.toString();
    }

}
