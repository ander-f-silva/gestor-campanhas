package br.com.search;

import java.util.NoSuchElementException;

/**
 * Class to manipulate characters
 */
public class Letter {

    private static final String IDENTICATION_VOWEL = "V";

    private static final String IDENTICATION_CONSONANT = "C";

    private static final String SEQUENTIAL_RULE =  "VCV";

    private static final char EMPTY_CHAR = ' ';

    /**
     * Find a vowel before a consonant that precedes a vowel and is not repeated
     *
     * @param input
     * @return char first
     */
    public static char firstChar(Stream input) {
        String sequenceIdentification = "  ";
        char firstVowel = EMPTY_CHAR;

        Cache cacheLetters = new Cache();

        while (input.hasNext()) {
            String letter = String.valueOf(input.getNext()).toLowerCase();

            cacheLetters.add(letter);

            sequenceIdentification += Vowel.is(letter) ? IDENTICATION_VOWEL : IDENTICATION_CONSONANT;

            if (SEQUENTIAL_RULE.hashCode() == sequenceIdentification.hashCode())
                if(!cacheLetters.exist(letter) && (EMPTY_CHAR == firstVowel || cacheLetters.exist(String.valueOf(firstVowel))))
                    firstVowel = letter.charAt(0);

            sequenceIdentification = sequenceIdentification.substring(sequenceIdentification.length() - 2, sequenceIdentification.length());
        }

        if (EMPTY_CHAR  == firstVowel)
                throw new NoSuchElementException("Não foi encontrada uma vogal antes de uma consoante que antecede de uma vogal e que não seja repetida.");
        return Character.valueOf(firstVowel) ;
    }
}