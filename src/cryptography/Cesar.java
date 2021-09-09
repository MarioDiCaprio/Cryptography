package cryptography;

import java.util.LinkedList;
import java.util.List;

/**
 * This class represents the <i>Cesar cipher</i>,
 * also known as <i>shift cipher</i>. This cipher
 * is an {@linkplain Affine} cipher with the property
 * {@code a=1}. Because of this, the possible solutions
 * for this cipher is equal to the alphabet's length.
 */
public class Cesar extends Affine {

    ////////////////////////////////////////
    ///// fields
    ////////////////////////////////////////

    private int shift;


    ////////////////////////////////////////
    ///// constructor
    ////////////////////////////////////////

    /**
     * The default constructor.
     */
    public Cesar() {
        super();
    }

    /**
     * Creates a cesar cipher with the given shift.
     * @param shift The shift
     */
    public Cesar(int shift) {
        super(1, shift);
        setShift(shift);
    }

    /**
     * Creates a cesar cipher and sets its text.
     * @param txt The text
     */
    public Cesar(String txt) {
        super(txt);
    }

    /**
     * Creates a cesar cipher with the given shift and sets
     * the cipher's text.
     * @param txt The text
     * @param shift The shift
     */
    public Cesar(String txt, int shift) {
        super(txt, 1, shift);
        setShift(shift);
    }


    ////////////////////////////////////////
    ///// methods
    ////////////////////////////////////////

    /**
     * Sets this cipher's shift.
     * @param a The first coefficient (unused)
     * @param b The second coefficient (shift)
     * @deprecated Use the {@code setShift(int shift)} method instead
     */
    @Deprecated
    @Override
    public void setKeys(int a, int b) {
        super.setKeys(1, b);
    }


    /**
     * Sets this cipher's shift.
     * @param a The first coefficient (unused)
     * @param b The second coefficient (shift)
     * @deprecated Use the {@code setShift(int shift)} method instead
     */
    @Deprecated
    @Override
    public void setSecureKeys(int a, int b) {
        super.setSecureKeys(a, b);
    }

    /**
     * Returns this cipher's shift.
     * @return The shift
     */
    public int getShift() {
        return shift;
    }


    /**
     * Sets the shift of this cipher.
     * @param shift The shift
     */
    public void setShift(int shift) {
        this.shift = shift;
        setKeys(1, shift);
    }


    /**
     * Returns an array that holds all possible solutions
     * for this cipher. The number of solutions are equal to the alphabet's
     * length for cesar cipher.
     * @return An array containing all possible solutions for this cipher
     */
    @Override
    public String[] crack() {
        List<String> list = new LinkedList<>();
        Cesar cesar = new Cesar(getText(), 0);
        cesar.setAlphabet( getAlphabet() );

        for (int i=0; i<=getAlphabet().length(); i++) {
            cesar.setShift(i);
            list.add( cesar.decrypt() );
        }

        return list.toArray(new String[0]);
    }

}
