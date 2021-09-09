package cryptography;

/**
 * This class represents a hexadecimal
 * number system. This class is identical to
 * an instance of {@linkplain Base} with a
 * base of 16.
 * @see Base
 */
public class Hexadecimal extends Base {

    ///////////////////////////////////
    ///// constructor
    ///////////////////////////////////

    /**
     * The default constructor.
     */
    public Hexadecimal() {
        super(16);
    }

    /**
     * Creates an instance of {@code Hexadecimal} and sets the
     * text that should be worked with.
     * @param txt The text
     */
    public Hexadecimal(String txt) {
        super(txt, 16);
    }

}
