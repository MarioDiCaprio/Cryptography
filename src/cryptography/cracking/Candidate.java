package cryptography.cracking;

/**
 * This class represents a solution for a cracked cipher.
 * Each candidate is made up of the decoded message and
 * an error count, which is used to estimate the semantic
 * correctness of the message. This class also implements
 * the {@link Comparable} interface, which sorts a number
 * of candidates in descending order, according to the
 * number of errors.
 */
public class Candidate implements Comparable<Candidate> {

    ///////////////////////////////////////////////////////////
    ///// fields
    ///////////////////////////////////////////////////////////

    private String text;
    private int errors = 0;


    ///////////////////////////////////////////////////////////
    ///// constructor
    ///////////////////////////////////////////////////////////

    /**
     * The default constructor.
     */
    public Candidate() {

    }

    /**
     * Builds a candidate with the given decoded text.
     *
     * @param text The decoded text
     */
    public Candidate(String text) {
        this.text = text;
    }


    ///////////////////////////////////////////////////////////
    ///// methods
    ///////////////////////////////////////////////////////////


    /**
     * Retrieves the text of this candidate.
     *
     * @return the text of this candidate
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the decoded text of this candidate.
     *
     * @param text The decoded text of this candidate
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Retrieves the number of semantic errors of this candidate.
     * Note that this method does not, by itself, evaluate this
     * candidate's text's correctness. To do so, use the
     * {@code estimateErrors(} {@link SpellChecker} {@code spellChecker)} method.
     *
     * @return The number of errors of this candidate.
     */
    public int getErrors() {
        return errors;
    }


    /**
     * Uses the given {@link SpellChecker} to estimate this candidate's
     * text's semantic errors.
     *
     * @param spellChecker The spell checker that holds the evaluation criteria
     * @return The estimated number of mistakes
     */
    public int estimatedErrors(SpellChecker spellChecker) {
        String tmp = spellChecker.getText();
        spellChecker.setText(text);
        int err = spellChecker.possibleMistakes();
        spellChecker.setText(tmp);
        return err;
    }


    /**
     * Estimates this candidate's text's semantic errors.
     * This method delegates to: {@code estimatedErrors(new SpellChecker)}.
     *
     * @return The estimated number of mistakes
     */
    public int estimatedErrors() {
        return estimatedErrors(new SpellChecker(text));
    }


    /**
     * Increases the number of errors by the given number.
     *
     * @param errors The errors to add
     */
    public void addErrors(int errors) {
        this.errors += errors;
    }


    /**
     * Compares the given candidate to this one. This method
     * subtracts the given candidate's errors from this
     * candidate's errors in order to sort them in descending
     * order.
     *
     * @param c The candidate to compare
     * @return {@code this.getErrors() - c.getErrors()}
     */
    @Override
    public int compareTo(Candidate c) {
        return this.errors - c.errors;
    }


    /**
     * Converts this candidate to a String. This method is equal to:
     * {@code this.getText() + "; " + this.getErrors()}
     *
     * @return This candidate as a String
     */
    @Override
    public String toString() {
        return getText() + "; " + getErrors();
    }

}
