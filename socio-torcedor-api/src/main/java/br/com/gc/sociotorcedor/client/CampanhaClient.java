package br.com.gc.sociotorcedor.client;

import br.com.gc.sociotorcedor.component.CampanhaClientFallback;
import br.com.gc.sociotorcedor.domain.Campanha;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Interface para consumir os serviços da api de campanha
 */
@FeignClient(name = "campanha", fallback = CampanhaClientFallback.class)
public interface CampanhaClient {

    /**
     * Método responsavel por pesquisar as campanhas vigentes pelo id do time do coração
     * @param timeCoracaoId
     * @return lista de campanhas
     */
    @RequestMapping(value = "/api/v1/campanhas/time-coracao/{timeCoracaoId}", method = RequestMethod.GET)
    List<Campanha> pesquisa(@PathVariable("timeCoracaoId") String timeCoracaoId);

}