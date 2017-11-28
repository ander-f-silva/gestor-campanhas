package br.com.search;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;
import static org.hamcrest.core.IsEqual.*;

public class LetterTest {

    @Test
    public void should_return_to_first_vowel() {
       char character = Letter.firstChar(new StreamImpl("aAbBABacafe"));

       assertThat('e', equalTo(character));
    }

    @Test
    public void should_return_first_vowel_with_having_two_vowels_that_does_not_repeat() {
        char character = Letter.firstChar(new StreamImpl("aAbBABocafe"));

        assertThat('o', equalTo(character));
    }

    @Test(expected = NoSuchElementException.class)
    public void must_inform_that_i_can_not_find_vowel() {
        Letter.firstChar(new StreamImpl("aAbBABgcafy"));
    }
}