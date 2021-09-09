# Cryptography

This project contains cryprographic utilities. It is possible to both encrypt and decrypt secret messages.
It is also possible to crack some arbitrarily simple ciphers by using frequency and semantic analysis.

## Ciphers

The following ciphers are available:
- Affine
- ASCII
- Atbash
- Base (up to 32)
  - Binary
  - Hexadecimal
- Cesar
- Morse
- Vigenere

## Example

Suppose we want to use the *Cesar Cypher*. We can encrypt a text in the following way:

- String msg = new Cesar(text, 5).encrypt()

To decrypt a text, we use:

- String msg = new Cesar(text, 5).decrypt();

To crack (brute force) an encrypted text, we use:

- String[] solutions = new Cesar(text).crack();

To sort these solutions by performing semantic and frequency analysis, we use:

- String[] sorted = new Cesar(text).analyse(new SpellChecker(), "etai");
