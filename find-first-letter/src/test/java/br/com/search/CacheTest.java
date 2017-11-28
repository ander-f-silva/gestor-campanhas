package br.com.search;

import org.junit.Test;

import static org.junit.Assert.*;

public class CacheTest {


    @Test
    public void should_check_the_string_if_it_exists() {
        Cache cache = new Cache();
        cache.add("a");
        cache.add("a");

        assertTrue(cache.exist("A"));
    }

    @Test
    public void should_check_the_string_if_not_found() {
        Cache cache = new Cache();
        assertFalse(cache.exist("A"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_return_error_for_uninformed_string() {
        Cache cache = new Cache();
        assertFalse(cache.exist(null));
    }

}