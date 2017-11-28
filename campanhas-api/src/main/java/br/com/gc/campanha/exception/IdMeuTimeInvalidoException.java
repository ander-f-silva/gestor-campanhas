package br.com.gc.campanha.exception;

/**
 * Exceptions Id ivanlido
 */
public class IdMeuTimeInvalidoException extends RuntimeException {
    public IdMeuTimeInvalidoException() {
        super("O id do meu time do coração é informado não existe.");
    }
}
