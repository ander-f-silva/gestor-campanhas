package br.com.gc.sociotorcedor.repository;

import br.com.gc.sociotorcedor.domain.Notificacao;
import br.com.gc.sociotorcedor.domain.SocioTorcedor;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

/***
 * @Interface responsavel manipulação dos dados das notificações.
 */
public interface NotificacaoRepository extends ReactiveMongoRepository<Notificacao, String>  {

}
