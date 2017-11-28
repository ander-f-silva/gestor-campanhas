package br.com.search;

/**
 * Enum to represent the vowels
 */
public enum Vowel {
    A, E, I, O, U;

    /**
     * Verify if letter is vowel
     * @param letter
     * @return true or false
     */
    public static boolean is(final String letter) {
        try {
            valueOf(letter.toUpperCase());
            return true;
        } catch (IllegalArgumentException iAEx) {
            return false;
        }
    }
}