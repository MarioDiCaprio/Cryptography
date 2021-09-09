package cryptography;

/**
 * This class represents a hash function.
 * Unlike other ciphers, hash functions
 * are one-way, meaning that they cannot
 * be decrypted.
 * @deprecated This class is unfinished!
 */
@Deprecated
public abstract class HashFunction extends Cipher {

    ////////////////////////////////////////////
    ///// fields
    ////////////////////////////////////////////




    ////////////////////////////////////////////
    ///// constructor
    ////////////////////////////////////////////




    ////////////////////////////////////////////
    ///// methods
    ////////////////////////////////////////////

    /**
     * This method does nothing.
     * @return nothing
     * @throws CipherException Upon use
     * @deprecated since hash functions are irreversible,
     * this method does not work.
     */
    @Deprecated
    @Override
    public String decrypt() throws CipherException {
        throw new CipherException(
                "ERROR: Hash Functions are irreversible"
        );
    }

}
