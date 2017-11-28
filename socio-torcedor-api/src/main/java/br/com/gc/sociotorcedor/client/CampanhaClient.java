package br.com.gc.sociotorcedor.client;

import br.com.gc.sociotorcedor.component.CampanhaClientFallback;
import br.com.gc.sociotorcedor.domain.Campanha;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "campanhaClient", url = "http://localhost:9080", fallback = CampanhaClientFallback.class)
public interface CampanhaClient {

    @RequestMapping(value = "/api/v1/campanhas/time-coracao/{timeCoracaoId}", method = RequestMethod.GET)
    List<Campanha> getCampanhasVigentes(@PathVariable("timeCoracaoId") String timeCoracaoId);

}