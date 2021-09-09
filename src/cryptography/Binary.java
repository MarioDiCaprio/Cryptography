package cryptography;

/**
 * This class represents the binary number system.
 * This class is equal to an instance of {@link Base}
 * with a base of 2.
 * @see Base
 */
public class Binary extends Base {

    ///////////////////////////////////
    ///// constructor
    ///////////////////////////////////

    /**
     * The default constructor.
     */
    public Binary() {
        super(2);
    }

    /**
     * Creates an instance of {@code Binary} and sets the
     * text that should be worked with.
     * @param txt The text
     */
    public Binary(String txt) {
        super(txt, 2);
    }

}
