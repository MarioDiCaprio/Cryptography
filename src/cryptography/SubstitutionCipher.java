package cryptography;

/**
 * This class represents a substitution cipher. A
 * substitution cypher uses a method where each character
 * in the message is substituted according to a certain
 * algorithm based on an alphabet. Because of this,
 * substitution ciphers tend to have low security.
 * Classic ciphers include the <i>Cesar</i> and <i>Vigenere</i>
 * ciphers. By default, this cipher utilizes the standard english
 * alphabet, which is given by the
 * {@code Alphabet.english()} method.
 */
public abstract class SubstitutionCipher extends Cipher {

    ///////////////////////////////////
    ///// fields
    ///////////////////////////////////

    /**
     * The cipher's alphabet. By default, it is the
     * standard latin alphabet.
     */
    private String alphabet = Alphabet.english();


    ///////////////////////////////////
    ///// constructor
    ///////////////////////////////////

    /**
     * The default constructor.
     */
    public SubstitutionCipher() {
        super();
    }

    /**
     * Creates a substitution cipher that consists of the given text.
     * @param txt The text to decipher
     */
    public SubstitutionCipher(String txt) {
        super(txt);
    }


    ///////////////////////////////////
    ///// methods
    ///////////////////////////////////


    /**
     * Returns this cipher's alphabet.
     * @return This cipher's alphabet
     */
    public String getAlphabet() {
        return alphabet;
    }


    /**
     * Sets this cipher's alphabet.
     * @param alphabet This cipher's alphabet
     */
    public void setAlphabet(String alphabet) {
        this.alphabet = alphabet;
    }

}