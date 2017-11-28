package br.com.search;

/**
 *Stores characters that have already been interacted
 */
public class Cache {
    StringBuilder letters = new StringBuilder();

    public boolean exist(final String letter) {

        if (letter == null)
            throw new IllegalArgumentException("Informe o caracter para realizar esta operação.");

        return letters.indexOf(letter.toLowerCase()) != letters.lastIndexOf(letter.toLowerCase());
    }

    public void add(final String letra) {
        if (exist(letra))
            return;

        letters.append(letra.toLowerCase());
    }

}
