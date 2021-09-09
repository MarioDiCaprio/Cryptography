package cryptography;

/**
 * This class is an optional utility for ASCII.
 * It converts a given integer to its corresponding
 * ASCII value and vice versa.
 */
public class ASCII {

    ///////////////////////////////////
    ///// constructor
    ///////////////////////////////////

    /**
     * Let no-one instantiate this class.
     */
    private ASCII() {

    }



    ///////////////////////////////////
    ///// methods
    ///////////////////////////////////

    /**
     * Converts the given integer to its corresponding ASCII
     * value. This is the same as: {@code (char) n}.
     * @param n The integer to convert
     * @return The corresponding ASCII character
     */
    public static char get(int n) {
        return (char) n;
    }


    /**
     * Converts the given ASCII character to its corresponding integer.
     * This is the same as: {@code (int) c}.
     * @param c The character to convert
     * @return The corresponding ASCII character
     */
    public static int get(char c) {
        return c;
    }

}
