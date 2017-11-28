package br.com.gc.campanha.service;

import br.com.gc.campanha.component.CampanhaVigente;
import br.com.gc.campanha.domain.Campanha;
import br.com.gc.campanha.domain.TimeCoracao;
import br.com.gc.campanha.exception.CampanhaNaoExisteException;
import br.com.gc.campanha.repository.CampanhaRepository;
import br.com.gc.campanha.component.Validador;

import br.com.gc.campanha.domain.MeuTimeCoracao;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

/***
 * Classe responsavel pelas regras de negócio da campanha
 */
@Slf4j
@Service
@AllArgsConstructor(onConstructor = @_(@Autowired))
public class CampanhaService {

    private Validador validador;
    private CampanhaVigente campanhaVigente;
    private CampanhaRepository campanhaRepository;

    /**
     * Serviço para listar todas as campanhas.
     *
     * @return capamanhas cadastradas
     */
    public Flux<Campanha> listar() {
        log.info("Realizando a buscar para retornar todas as campanhas");

        return campanhaRepository.findAll();
    }

    /**
     * Serviço para consultar os dados da camanha.
     *
     * @param id para efetuar a search
     * @return capamanha referente ao id
     */
    public Mono<Campanha> consultar(final String id) {
        log.info("Relizando a search para recuperar uma campanha");

        return campanhaRepository.existsById(id)
                .flatMap(resultado -> {
                    if (resultado)
                        return campanhaRepository.findById(id);

                    throw new CampanhaNaoExisteException();
                });
    }

    /**
     * Serviço para adicionar uma nova campanha.
     *
     * @return capamanhas vigentes
     * @para campanha a ser persistida
     */
    public Flux<Campanha> adicionar(final Campanha campanha) {
        log.info("Relizando a inclusão de uma nova campanha.");

        validador.validarDatas(campanha);
        validador.validarIdTimeCoracao(campanha.getTimeCoracao());

        return campanhaVigente.atualizar(campanha);
    }

    /**
     * Serviço para atualizar os dados da camanha existente.
     *
     * @return capamanha
     * @para id da campanha a ser alterada
     */
    public Mono<Campanha> atualizar(final String id, Campanha campanha) {
        log.info("Relizando a alteração de uma campanha.");

        validador.validarDatas(campanha);
        validador.validarIdTimeCoracao(campanha.getTimeCoracao());

        return campanhaRepository.existsById(id)
                                    .flatMap(resultado -> {
                                        if (resultado) {
                                            campanha.setId(id);
                                            String idTimeCoracao = campanha.getTimeCoracao().getId();
                                            campanha.setTimeCoracao(new TimeCoracao(idTimeCoracao, MeuTimeCoracao.get(idTimeCoracao)));
                                            return campanhaRepository.save(campanha);
                                        }
                                        throw new CampanhaNaoExisteException();
                                    });
    }

    /**
     * Serviço para deletar uma campanha
     *
     * @return capamanha
     */
    public Mono<Void> remover(final String id) {
        log.info("Relizando a remoção de uma campanha.");

        return campanhaRepository.existsById(id)
                                    .flatMap(resultado -> {
                                        if (resultado) return campanhaRepository.deleteById(id);
                                        throw new CampanhaNaoExisteException();
                                    });
    }

    /**
     * Serviço para pesquisar campanhas vigentes por time do coração
     *
     * @return capamanha
     */
    public Flux<Campanha> pesquisar(final TimeCoracao timeCoracao) {
        log.info("Relizando pesquisa de campanhas vigentes informando o time do coração.");

        validador.validarIdTimeCoracao(timeCoracao);

        return campanhaRepository.findByTimeCoracaoAndDataFim(timeCoracao, LocalDate.now());
    }

}
