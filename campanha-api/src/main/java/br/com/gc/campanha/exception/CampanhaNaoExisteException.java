package br.com.gc.campanha.exception;

/**
 * Exceptions para campanha não existe
 */
public class CampanhaNaoExisteException extends RuntimeException {
    public CampanhaNaoExisteException() {
        super("A campanha informada não existe.");
    }
}
