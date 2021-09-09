package cryptography;

/**
 * This class represents a cipher. A cipher has the ability
 * to encrypt and decrypt a given text with an arbitrarily
 * chosen algorithm. Note that there are various kinds
 * of ciphers, like substitution ciphers and hash functions,
 * all of which work differently. All ciphers extend from this
 * class.
 */
public abstract class Cipher {

    ///////////////////////////////////
    ///// fields
    ///////////////////////////////////

    /**
     * This String represents the text that
     * is being worked with.
     */
    private String txt;


    ///////////////////////////////////
    ///// constructor
    ///////////////////////////////////

    /**
     * The default constructor.
     */
    public Cipher() {

    }

    /**
     * Creates an instance of {@code Cipher} and
     * assigns the text that should be worked with.
     * @param txt The text
     */
    public Cipher(String txt) {
        setText(txt);
    }


    ///////////////////////////////////
    ///// methods
    ///////////////////////////////////

    /**
     * @return The text that is being worked with.
     */
    public String getText() {
        return txt;
    }

    /**
     * Sets the text that should be worked with.
     * @param txt The text
     */
    public void setText(String txt) {
        this.txt = txt;
    }


    /**
     * Encrypts the text that is being worked with.
     * @return The encrypted text
     */
    public abstract String encrypt();

    /**
     * Decrypts the text that is being
     * worked with.
     * @return The decrypted text
     */
    public abstract String decrypt();

}
