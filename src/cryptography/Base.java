package cryptography;

/**
 * This cipher represents base encoding.
 * Here, the number system's length is equal
 * to the given base. For example, if we have
 * a base of 10 (decimal), then our number system is:
 * [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]. However, if we have
 * a base of 2 (binary), then our number system changes
 * to: [0, 1]. To encode and decode, the digits are
 * converted using the {@link ASCII} class. <br>
 * <b>NOTE: </b> Bases may only be chosen arbitrarily
 * within a range of {@code Character.MIN_RADIX} and
 * {@code Character.MAX_RADIX}, that is: from 2-36.
 */
public class Base extends Cipher {

    ///////////////////////////////////
    ///// constructor
    ///////////////////////////////////

    /**
     * The of this cipher. The base represents
     * the length of the number system used to
     * encode and decode this cipher. For example,
     * a base of 2 (binary) has a number system of:
     * [0, 1]. A base of 10 (decimal) has a number
     * system of: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9].
     * Special characters (i.e. letters) are used
     * to represent numbers for ciphers with a
     * base above 10.
     */
    public final int BASE;

    /**
     * The amount of digits that make up one
     * byte. This length is given by the
     * formula: <br>
     * {@code Math.ceil( 8 / ( Math.log(BASE) / Math.log(2) ) );}
     * For example, each byte in the binary system (base 2) is made
     * up of 8 bits.
     */
    public final int DIGITS;

    ///////////////////////////////////
    ///// constructor
    ///////////////////////////////////

    /**
     * The default constructor.
     */
    public Base(int base) {
        super();
        BASE = base;
        DIGITS = (int) Math.ceil( 8 / ( Math.log(BASE) / Math.log(2) ) );
    }

    /**
     * Creates an instance of {@code Binary} and sets the
     * text that should be worked with.
     * @param txt The text
     */
    public Base(String txt, int base) {
        this(base);
        setText(txt);
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
        StringBuilder str = new StringBuilder();
        char[] arr = getText().toCharArray();
        for (char c : arr) {
            StringBuilder tmp = new StringBuilder(Integer.toString(ASCII.get(c), BASE));
            while (tmp.length() < DIGITS) {
                tmp.insert(0, 0);
            }
            str.append(tmp);
        }
        String result = str.toString();
        setText(result);
        return result;
    }

    /**
     * Decrypts the text that is being
     * worked with.
     *
     * @return The decrypted text
     */
    @Override
    public String decrypt() {
        StringBuilder str = new StringBuilder();
        String[] bytes = getText().split("(?<=\\G.{" + DIGITS + "})");
        for (String bits : bytes) {
            str.append( (char) Integer.parseInt(bits, BASE) );
        }
        String result = str.toString();
        setText(result);
        return result;
    }


}
