package cryptography;

/**
 * This class represents the <i>Vigenere</i> cipher.
 * Something particular about this cipher is that,
 * in contrast to common substitution ciphers, it
 * utilizes a polyalphabet.
 */
public class Vigenere extends PolyAlphabetic {

    ///////////////////////////////////
    ///// fields
    ///////////////////////////////////

    private String encoding;


    ///////////////////////////////////
    ///// constructor
    ///////////////////////////////////


    /**
     * The default constructor.
     */
    public Vigenere() {
        super();
    }

    /**
     * Creates a Vigenere cipher and sets its key.
     * @param encoding The key that should be used for encoding
     */
    public Vigenere(String encoding) {
        this();
        setEncoding( encoding.toLowerCase() );
    }

    /**
     * Creates an instance of {@code Vigenere} and sets the
     * text that should be worked with.
     * @param txt The text
     * @param encoding The key that should be used for encoding
     */
    public Vigenere(String txt, String encoding) {
        this(encoding);
        setText(txt);
    }


    ///////////////////////////////////
    ///// methods
    ///////////////////////////////////

    public String getEncoding() {
        return encoding;
    }


    public void setEncoding(String encoding) {
        StringBuilder tmp = new StringBuilder();
        char[] arr = encoding.toLowerCase().toCharArray();
        for (char c : arr) {
            if (getAlphabet().indexOf(c) != -1) {
                tmp.append(c);
            }
        }

        this.encoding = tmp.toString();
    }



    public char[][] getPolyAlphabet() {
        Cesar cesar = new Cesar( getAlphabet() );
        int len = getAlphabet().length();
        char[][] result = new char[len][len];

        for (int i=0; i<len; i++) {
            cesar.setShift(i);
            result[i] = cesar.encrypt().toCharArray();
        }

        return result;
    }


    /**
     * Encrypts the text that is being worked with.
     *
     * @return The encrypted text
     */
    @Override
    public String encrypt() {
        StringBuilder result = new StringBuilder();
        char[][] tableau = getPolyAlphabet();
        char[] arr1 = getText().toLowerCase().toCharArray();
        char[] arr2 = new char[arr1.length];

        for (int i=0, j=0; i<arr1.length; i++, j++) {
            j = (getEncoding().length()-1 < j)? 0 : j;
            char c = getEncoding().charAt(j), check = arr1[i];
            if (getAlphabet().indexOf(check) == -1) {
                arr2[i] = check;
                j--;
                continue;
            }
            arr2[i] = c;
        }

        for (int i=0; i<arr1.length; i++) {
            boolean isUpper = Character.isUpperCase(getText().charAt(i));
            int row = getAlphabet().indexOf( arr2[i] );
            int col = getAlphabet().indexOf( arr1[i] );
            if (row == -1 || col == -1) {
                result.append(isUpper ? Character.toUpperCase(arr1[i]) : arr1[i]);
                continue;
            }
            result.append(isUpper ? Character.toUpperCase(tableau[col][row]) : tableau[col][row]);
        }

        setText(result.toString());
        return result.toString();
    }

    /**
     * Decrypts the text that is being
     * worked with.
     *
     * @return The decrypted text
     */
    @Override
    public String decrypt() {
        StringBuilder result = new StringBuilder();
        char[][] tableau = getPolyAlphabet();
        char[] arr1 = getText().toLowerCase().toCharArray();
        char[] arr2 = new char[arr1.length];

        for (int i=0, j=0; i<arr1.length; i++, j++) {
            j = (getEncoding().length()-1 < j)? 0 : j;
            char c = getEncoding().charAt(j), check = arr1[i];
            if (getAlphabet().indexOf(check) == -1) {
                arr2[i] = check;
                j--;
                continue;
            }
            arr2[i] = c;
        }

        for (int i=0; i<arr1.length; i++) {
            boolean isUpper = Character.isUpperCase(getText().charAt(i));
            char row = arr2[i], col = arr1[i];
            int rowIndex = getAlphabet().indexOf(row), colIndex = -1;

            if (rowIndex == -1) {
                result.append(col);
                continue;
            }

            for (int j=0; j<getAlphabet().length(); j++) {
                if (tableau[j][rowIndex] == col) {
                    colIndex = j;
                    break;
                }
            }

            char c = getAlphabet().charAt(colIndex);
            result.append(isUpper ? Character.toUpperCase(c) : c);
        }
        setText(result.toString());
        return result.toString();
    }

}
