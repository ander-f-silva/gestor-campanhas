package br.com.gc.campanha.exception;

/**
 * Exceptions para invalidar data inicial maior data final
 */
public class DataInicialMaiorDataFinalException extends RuntimeException {
    public DataInicialMaiorDataFinalException() {
        super("A data final precisa ser maior que a data inicial.");
    }
}
