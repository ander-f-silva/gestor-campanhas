package br.com.search;

import java.util.Scanner;

/**
 * Class conduct the main test with the character string reported in the test
 */
public class MainTest {

    public static void main(String[] args) throws Exception {

        System.out.print("Information seguence of letters: ");

        Scanner sc = new Scanner(System.in);
        String sequenceLetter = sc.next();

        System.out.println(String.format("Result: %s", Letter.firstChar(new StreamImpl(sequenceLetter))));

        System.exit(0);
    }
}