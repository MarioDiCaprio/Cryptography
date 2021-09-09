package cryptography;

/**
 * This class represents a polyalphabetic cipher.
 * In contrast to monoalphabetic ciphers, this
 * technique is vastly more secure, as it uses
 * multiple alphabets and an arbitrary algorithm
 * to encrypt messages.
 */
public abstract class PolyAlphabetic extends SubstitutionCipher{

    /////////////////////////////////////////////////
    ///// constructor
    /////////////////////////////////////////////////

    /**
     * The default constructor.
     */
    public PolyAlphabetic() {
    }

    /**
     * Creates a polyalphabetic cipher that consists of the given text.
     *
     * @param txt The text to decipher
     */
    public PolyAlphabetic(String txt) {
        super(txt);
    }


    /////////////////////////////////////////////////
    ///// methods
    /////////////////////////////////////////////////

    /**
     * Returns a two-dimensional array that contains the
     * the poly alphabet of this cipher.
     * @return The poly alphabet of this cipher
     */
    public abstract char[][] getPolyAlphabet();

}
