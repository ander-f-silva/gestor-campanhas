package br.com.gc.campanha;

import br.com.gc.campanha.domain.TimeCoracao;
import br.com.gc.campanha.component.Validador;
import br.com.gc.campanha.domain.Campanha;
import br.com.gc.campanha.exception.DataInicialMaiorDataFinalException;
import br.com.gc.campanha.exception.IdMeuTimeInvalidoException;
import org.junit.Test;

import java.time.LocalDate;


/**
 * @Class Teste Unitário dos validadores
 */
public class ValidadorTest {

    private Validador validador = new Validador();

    @Test(expected = DataInicialMaiorDataFinalException.class)
    public void deveIdenticarSeDataInioMaiorQueDataFinal() {
        Campanha campanha = new Campanha();
        campanha.setDataInicioVigencia(LocalDate.now().plusDays(1));
        campanha.setDataFimVigencia(LocalDate.now());

        validador.validarDatas(campanha);
    }


    @Test(expected = IdMeuTimeInvalidoException.class)
    public void deveVerificarSeIdTimeDoCoracaoValido() {
        Campanha campanha = new Campanha();
        campanha.setTimeCoracao(new TimeCoracao("idInvalido", null));

        validador.validarIdTimeCoracao(campanha.getTimeCoracao());
    }

}