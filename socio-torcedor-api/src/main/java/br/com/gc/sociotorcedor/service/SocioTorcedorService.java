package br.com.gc.sociotorcedor.service;

import br.com.gc.sociotorcedor.client.CampanhaClient;
import br.com.gc.sociotorcedor.domain.Campanha;
import br.com.gc.sociotorcedor.domain.MeuTimeCoracao;
import br.com.gc.sociotorcedor.domain.SocioTorcedor;
import br.com.gc.sociotorcedor.domain.TimeCoracao;
import br.com.gc.sociotorcedor.exception.EmailCadastradoException;
import br.com.gc.sociotorcedor.repository.SocioTorcedorRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

/***
 * Classe responsavel pelas regras de negócio do sócio torcedor
 */
@Slf4j
@Service
@AllArgsConstructor(onConstructor = @_(@Autowired))
public class SocioTorcedorService {

    private SocioTorcedorRepository socioTorcedorRepository;

    private CampanhaClient campanhaClient;

    /**
     * Serviço para adicionar um novo sócio torcedor.
     *
     * @return socio torcedor
     * @para socio torcedor
     */
    public Mono<SocioTorcedor> salvar(SocioTorcedor socioTorcedor) {

        SocioTorcedor socioTorcedorCadastrado = socioTorcedorRepository.findByEmail(socioTorcedor.getEmail()).block();

        if (socioTorcedorCadastrado != null) {

            if (socioTorcedorCadastrado.getCampanhas() != null && !socioTorcedorCadastrado.getCampanhas().isEmpty()) {
                throw new EmailCadastradoException();
            }

            List<Campanha> campanhas = campanhaClient.pesquisa(socioTorcedorCadastrado.getId());

            socioTorcedorCadastrado.setCampanhas(campanhas);

            return  socioTorcedorRepository.save(socioTorcedorCadastrado);

        } else {
            List<Campanha> campanhas = campanhaClient.pesquisa(socioTorcedor.getId());

            socioTorcedor.setCampanhas(campanhas);

            Mono<SocioTorcedor> socioTorcedorMono =  socioTorcedorRepository.save(socioTorcedor);

            return socioTorcedorMono.map( st -> {
                        String idTimeCoracao = socioTorcedor.getTimeCoracao().getId();
                        st.setTimeCoracao(new TimeCoracao(idTimeCoracao, MeuTimeCoracao.get(idTimeCoracao)));
                        return st;
                    }
            );
        }
    }

}
