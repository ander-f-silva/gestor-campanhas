package br.com.gc.campanha.component;

import br.com.gc.campanha.domain.Campanha;
import br.com.gc.campanha.domain.Notificacao;
import br.com.gc.campanha.domain.TimeCoracao;
import br.com.gc.campanha.repository.CampanhaRepository;
import br.com.gc.campanha.domain.MeuTimeCoracao;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/***
 * Classe responsavel por tratar de campanhas vigentes
 */
@Slf4j
@Component
@AllArgsConstructor
public class CampanhaVigente {

    private CampanhaRepository campanhaRepository;

    private Notificador notificador;

    /***
     * Tem a responsabilidade por atauliar todas as campanhas vigentes
     * @param campanha que será atualizada ou incluida
     * @return retorna o flux com todas as campanhas vigentes
     */
    public Flux<Campanha> atualizar(Campanha campanha) {

        log.info("Adicionar novas campanhas");

        List<Campanha> campanhasAtualizadas = new LinkedList<>();
        Campanha campanhaAtual = new Campanha();
        ModelMapper modelMapper = new ModelMapper();

        Flux<Campanha> campanhas = campanhaRepository.findByDataInicioVigenciaAndDataFimVigencia(
                                                        campanha.getDataInicioVigencia(),
                                                        campanha.getDataFimVigencia(),
                                                        Sort.by(new Sort.Order(Sort.Direction.ASC, "dataFimVigencia")));

        String idTimeCoracao = campanha.getTimeCoracao().getId();
        campanha.setTimeCoracao(new TimeCoracao(idTimeCoracao, MeuTimeCoracao.get(idTimeCoracao)));

        modelMapper.map(campanha, campanhaAtual);
        campanhasAtualizadas.add(campanha);

        List<Campanha> campanhasCadastradas = campanhas
                                                .toStream()
                                                .collect(Collectors.toList());

        campanhasCadastradas.forEach(
                campanhaExistente -> {
                    campanhaExistente.adicionarUmDiaParaDataFinal(campanhaAtual);
                    campanhasAtualizadas.add(campanhaExistente);
                    modelMapper.map(campanhaExistente, campanhaAtual);
                    notificador.enviar(new Notificacao<Campanha>(campanhaExistente, LocalDate.now()));
                }
        );

        log.info("Salvando todas as atulizações e a nova campanha");

        Flux<Campanha> campanhasFlux = campanhaRepository.saveAll(campanhasAtualizadas);

        return campanhasFlux.filter(campanhaVigente -> LocalDate.now().isBefore(campanhaVigente.getDataFimVigencia()));
    }

}
