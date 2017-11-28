package br.com.gc.sociotorcedor.component;

import br.com.gc.sociotorcedor.client.CampanhaClient;
import br.com.gc.sociotorcedor.domain.Campanha;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CampanhaClientFallback implements CampanhaClient {
    @Override
    public List<Campanha> getCampanhasVigentes(String timeCoracaoId) {
        return new ArrayList<>();
    }
}
