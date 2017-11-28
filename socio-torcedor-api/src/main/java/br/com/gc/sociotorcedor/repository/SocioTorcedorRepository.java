package br.com.gc.sociotorcedor.repository;

import br.com.gc.sociotorcedor.domain.SocioTorcedor;
import com.mongodb.MongoClient;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

/***
 * @Interface responsavel manipulação dos dados do sócio torcedor.
 */
public interface SocioTorcedorRepository extends ReactiveMongoRepository<SocioTorcedor, String>  {

    Mono<SocioTorcedor> findByEmail(String email);

}
