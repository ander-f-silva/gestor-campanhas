package br.com.gc.campanha.repository;

import br.com.gc.campanha.domain.Campanha;
import br.com.gc.campanha.domain.TimeCoracao;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

/***
 * @Interface responsavel manipulação dos dados da campanhas.
 */
public interface CampanhaRepository extends ReactiveMongoRepository<Campanha, String> {

    /**
     * Pesquisa as campanhas dentro de uma vigência
     * 
     * @param to data inicial
     * @param from data final
     * @param sort para definir o campo da ordenação
     * @return campanhas dentro do perído de vigência
     */
    @Query("{ 'dataInicioVigencia': {$eq: ?0}, 'dataFimVigencia': {$lte: ?1}}")
    Flux<Campanha> findByDataInicioVigenciaAndDataFimVigencia(LocalDate to, LocalDate from, Sort sort);

    /**
     * Pesquisa as campanhas vigentes associadas a um time do coração
     *
     * @param time coracao
     * @param from data final
     * @return campanhas vigentes
     */
    @Query("{'dataFimVigencia': {$gte: ?1}}")
    Flux<Campanha> findByTimeCoracaoAndDataFim(TimeCoracao timeCoracao, LocalDate from);
}
