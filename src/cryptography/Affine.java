package cryptography;

import cryptography.cracking.Crackable;

import java.util.LinkedList;

/**
 * This class represents the <i>Affine Cipher</i>.
 * This cipher works with the formula: {@code C(x) = (ax - b) mod m}
 * where {@code a} and {@code b} are the cipher's keys, {@code m}
 * is the alphabet's length and {@code x} is the position of the current
 * character in the alphabet. {@code C(x)} is the position of the encrypted
 * character in the alphabet. <br><br>
 * For example, Let us say that we have an affine cipher with the
 * following properties:
 *
 * <ul>
 *     <li>Alphabet: "ABCDEFGHIJKLMNOPQRSTUVWXYZ" (26 letters)</li>
 *     <li> {@code a = 3} </li>
 *     <li> {@code b = 6} </li>
 *     <li> {@code m = 26} </li>
 *     <li> {@code C(x) = (3x - 6) mod 26} </li>
 * </ul>
 *
 * We want to encrypt the letter 'm'. In the alphabet, it has the position
 * 12 (we start counting from 0). So the position of the encrypted letter is:
 * {@code C(12) = (3*13 - 6) mod 26 = 16}. The 16th letter in the alphabet is
 * 'q' (again, counting from 0), so this is our encrypted message. <br><br>
 *
 * <b>NOTE: </b> This cipher only works if 'a' and 'm' are coprime. Otherwise,
 * the encryption will yield mistakes. To safely assign a key, use the
 * {@code setSecureKeys(int a, int b)} method.
 */
public class Affine extends SubstitutionCipher implements Crackable {

    ///////////////////////////////////
    ///// fields
    ///////////////////////////////////

    private int[] key = new int[2];


    ///////////////////////////////////
    ///// constructor
    ///////////////////////////////////

    /**
     * The default constructor.
     */
    public Affine() {
        super();
    }


    /**
     * Creates an affine cipher that consists of the given text.
     * @param txt The text to decipher
     */
    public Affine(String txt) {
        super(txt);
    }


    /**
     * Creates an affine cipher that consists of the given keys.
     * @param a The first coefficient
     * @param b The second coefficient
     * @throws CipherException If 'a' is not coprime with the alphabet's length
     */
    public Affine(int a, int b) throws CipherException {
        super();
        setSecureKeys(a, b);
    }


    /**
     * Creates an affine cipher that consists of the given text and of the given
     * keys.
     * @param txt The text to decipher
     * @param a The first coefficient
     * @param b The second coefficient
     * @throws CipherException If 'a' is not coprime with the alphabet's length
     */
    public Affine(String txt, int a, int b) throws CipherException {
        super(txt);
        setSecureKeys(a, b);
    }


    ///////////////////////////////////
    ///// methods
    ///////////////////////////////////

    //greatest common divisor
    private static int gcd(int a, int m) {
        return (m == 0)? a : gcd(m, a % m);
    }

    //checks if coprime
    private static boolean isCoprime(int a, int m) {
        return gcd(a, m) == 1;
    }


    /**
     * Retrieves the keys of this affine cipher. The returned
     * array consists of two elements: 'a' and 'b', which are
     * the coefficients used to encrypt and decrypt text.
     * @return An array that consists of this cipher's keys
     */
    public int[] getKeys() {
        return key;
    }


    /**
     * Returns the first key of this cipher. This is the
     * coefficient 'a', which is used to encrypt and decrypt
     * text.
     * @return The key 'a' of this cipher
     */
    public int getA() {
        return key[0];
    }


    /**
     * Returns the second key of this cipher. This is the
     * coefficient 'a', which is used to encrypt and decrypt
     * text.
     * @return The key 'b' of this cipher
     */
    public int getB() {
        return key[1];
    }


    /**
     * Returns the coefficient 'm' of this cipher, which is
     * the alphabet's length. This method delegates to:
     * {@code getAlphabet().length()}.
     * @return The coefficient 'm' of this cipher
     */
    public int getM() {
        return getAlphabet().length();
    }


    /**
     * Sets the keys of this cipher. Note that if the
     * given coefficient 'a' is not coprime with the
     * coefficient 'm' (the alphabet's length) this
     * cipher will yield errors.
     * @param a The first coefficient
     * @param b The second coefficient
     * @deprecated To ensure that the coefficients 'a' and 'm'
     * are coprime use the {@code setSecureKeys(int a, int b)}
     * method.
     */
    @Deprecated
    public void setKeys(int a, int b) {
        key[0] = a;
        key[1] = b;
    }


    /**
     * Sets the keys of this cipher.
     * @param a The first coefficient
     * @param b The second coefficient
     * @throws CipherException If 'a' is not coprime with the alphabet's length
     */
    public void setSecureKeys(int a, int b) throws CipherException {
        int m = getAlphabet().length();
        if ( !isCoprime(a, m) ) {
            throw new CipherException(
                    "ERROR: Entered value 'a' should be coprime with alphabet length 'm': " +
                    "a = " + a + "; m = " + m
            );
        }

        setKeys(a, b);
    }


    /**
     * Encrypts the text that is being worked with.
     *
     * @return The encrypted text
     */
    @Override
    public String encrypt() {
        StringBuilder tmp = new StringBuilder();
        int a = key[0];
        int b = key[1];
        int m = getAlphabet().length();
        char[] arr = getText().toCharArray();
        for (char c : arr) {
            boolean isUpper = Character.isUpperCase(c);
            c = Character.toLowerCase(c);
            int x = getAlphabet().indexOf(c);
            if (x == -1) {
                tmp.append(c);
                continue;
            }
            x = (a*x + b) % m;
            c = getAlphabet().charAt(x);
            tmp.append( isUpper? Character.toUpperCase(c) : c );
        }
        return tmp.toString();
    }

    /**
     * Decrypts the text that is being
     * worked with.
     *
     * @return The decrypted text
     */
    @Override
    public String decrypt() {
        StringBuilder tmp = new StringBuilder();
        int a = key[0];
        int b = key[1];
        int m = getAlphabet().length();
        char[] arr = getText().toCharArray();
        int a1 = -1;

        for (int i=0; i<=m; i++) {
            if ( (a*i) % m == 1 ) {
                a1 = i;
                break;
            }
        }

        for (char c : arr) {
            boolean isUpper = Character.isUpperCase(c);
            c = Character.toLowerCase(c);
            int x = getAlphabet().indexOf(c);
            if (x == -1) {
                tmp.append(c);
                continue;
            }
            c = (char) ((a1 * (c - 'a' - b + m)) % m + 'a');
            tmp.append( isUpper? Character.toUpperCase(c) : c );
        }

        return tmp.toString();
    }


    /**
     * Attempts to crack a message by using brute force.
     * The array returned by this method contains all
     * possible solutions for the encrypted message.
     * For affine ciphers, there is a total of
     * {@code (m-1)*m} solutions.
     * @return An array containing all possible solutions for the message
     */
    @Override
    public String[] crack() {
        final int m = getAlphabet().length();
        LinkedList<String> result = new LinkedList<>();
        Affine affine = new Affine( getText() );
        affine.setAlphabet( getAlphabet() );

        for (int a = 0; a < m; a++) {
            for (int b = 0; b <= m; b++) {
                affine.setKeys(a, b);
                result.add(affine.decrypt());
            }
        }

        return result.toArray(new String[0]);
    }



}
