package br.com.gc.sociotorcedor.exception;

/**
 * Exceptions para e-mail já cadastrado
 */
public class EmailCadastradoException extends RuntimeException {
    public EmailCadastradoException() {
        super("Cliente já esta cadastrado.");
    }
}
