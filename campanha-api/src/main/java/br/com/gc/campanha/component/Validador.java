package br.com.gc.campanha.component;

import br.com.gc.campanha.domain.Campanha;
import br.com.gc.campanha.domain.TimeCoracao;
import br.com.gc.campanha.exception.DataInicialMaiorDataFinalException;
import br.com.gc.campanha.exception.IdMeuTimeInvalidoException;
import br.com.gc.campanha.domain.MeuTimeCoracao;
import org.springframework.stereotype.Component;

/**
 * Classe de validação do modelo de campanha usaod só
 * para o pacote de campanha.
 *
 * @Class Validador
 */
@Component
public class Validador {

    /**
     * Validar as dadas da capanha
     * @para campanha
     */
    public void validarDatas(final Campanha campanha) {
        if (campanha.validarDatasInicioMaiorDataFim())
            throw new DataInicialMaiorDataFinalException();
    }

    /**
     * Validar o se o id do time do coração é válido
     * @para campanha
     */
    public void validarIdTimeCoracao(final TimeCoracao timeCoracao) {
        if (MeuTimeCoracao.get(timeCoracao.getId()) == null)
            throw new IdMeuTimeInvalidoException();
    }
}
