package br.com.search;

import org.junit.Test;

import static org.junit.Assert.*;

public class VowelTest {

    @Test
    public void should_check_the_string_if_it_is_vowel() {
         assertTrue(Vowel.is("a"));
    }

    @Test
    public void should_check_the_string_if_it_is_consonant() {
        assertFalse(Vowel.is("f"));
    }


}