package br.com.gc.sociotorcedor.component;

import br.com.gc.sociotorcedor.client.CampanhaClient;
import br.com.gc.sociotorcedor.domain.Campanha;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe para retotnar o método de fallback caso o serivço esteja indisponivel
 */
@Component
public class CampanhaClientFallback implements CampanhaClient {

    /**
     * Métdodo retorna a instancia do objeto caso esteja vazio
     * @param timeCoracaoId
     * @return lista vazia de campanhas
     */
    @Override
    public List<Campanha> getCampanhasVigentes(String timeCoracaoId) {
        return new ArrayList<>();
    }
}
