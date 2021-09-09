package cryptography;

/**
 * This class represents the Atbash cipher.
 * The atbash cipher, also called the mirror
 * cipher, is an Affine cipher with the keys
 * {@code (a=m, b=m)}, where m is the alphabet's
 * length. Because of this, the cipher has
 * incredibly low security, as there is only
 * one possible solution for this cipher.
 * This cipher is believed to be the first cipher
 * ever used, its roots being the ancient egypt.
 */
public class Atbash extends Affine {

    /////////////////////////////////////////
    ///// constructor
    /////////////////////////////////////////

    /**
     * The default constructor.
     */
    public Atbash() {
        super(25, 25);
    }

    /**
     * Creates an Atbash cipher with the given
     * text.
     * @param txt The text to encrypt
     */
    public Atbash(String txt) {
        super(txt, 25, 25);
    }


    /////////////////////////////////////////
    ///// methods
    /////////////////////////////////////////

    /**
     * This method does nothing.
     * @param a The first coefficient
     * @param b The second coefficient
     * @deprecated This method is redundant
     */
    @Deprecated
    @Override
    public void setKeys(int a, int b) {
        int m = getAlphabet().length() - 1;
        super.setKeys(m, m);
    }

    /**
     * This method does nothing.
     * @param a The first coefficient
     * @param b The second coefficient
     * @deprecated This method is redundant
     */
    @Deprecated
    @Override
    public void setSecureKeys(int a, int b) {
        int m = getAlphabet().length() - 1;
        super.setKeys(m, m);
    }


    /**
     * Sets the alphabet of this cipher.
     * @param alphabet This cipher's alphabet
     */
    @Override
    public void setAlphabet(String alphabet) {
        int m = alphabet.length() - 1;
        super.setAlphabet(alphabet);
        super.setKeys(m, m);
    }


    /**
     * Returns an array containing all possible solutions for
     * this cipher. Since the Atbash cipher has only one solution,
     * the only element in the array is given by: {@code this.decrypt()}
     * @return an array with all possible solutions for this cipher.
     */
    @Override
    public String[] crack() {
        return new String[] { this.decrypt() };
    }

}
