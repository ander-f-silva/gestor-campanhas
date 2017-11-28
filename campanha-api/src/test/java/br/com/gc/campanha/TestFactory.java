package br.com.gc.campanha;

import br.com.gc.campanha.domain.Campanha;
import br.com.gc.campanha.domain.TimeCoracao;
import br.com.gc.campanha.domain.MeuTimeCoracao;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @Class Gerar instancias do testes unitários
 */
public class TestFactory {

    public static Flux<Campanha> retornarListaCampanhasAtivas() {
        List<Campanha> campanhasRetornadas = new ArrayList<>();

        TimeCoracao timeCoracao = new TimeCoracao("1", MeuTimeCoracao.SAO_PAULO);
        LocalDate dataInicio = LocalDate.of(2100, 10, 1);
        LocalDate dataFimVigenciaCampanha2 = LocalDate.of(2100, 10, 2);
        LocalDate dataFimVigenciaCampanha1 = LocalDate.of(2100, 10, 3);



        campanhasRetornadas.add(new Campanha("2", "Campanha 2", timeCoracao, dataInicio, dataFimVigenciaCampanha2));
        campanhasRetornadas.add(new Campanha("1", "Campanha 1", timeCoracao, dataInicio, dataFimVigenciaCampanha1));

        return Flux.fromIterable(campanhasRetornadas);
    }

    public static Flux<Campanha> retornarCampanhasAtivasParaSalvar() {
        List<Campanha> campanhasRetornadas = new ArrayList<>();

        TimeCoracao timeCoracao = new TimeCoracao("1",  MeuTimeCoracao.SAO_PAULO);
        LocalDate dataInicio = LocalDate.of(2100, 10, 1);

        LocalDate dataFimVigenciaCampanha3 = LocalDate.of(2100, 10, 3);
        LocalDate dataFimVigenciaCampanha2 = LocalDate.of(2100, 10, 4);
        LocalDate dataFimVigenciaCampanha1 = LocalDate.of(2100, 10, 5);

        campanhasRetornadas.add(new Campanha("3", "Campanha 3", timeCoracao, dataInicio, dataFimVigenciaCampanha3));
        campanhasRetornadas.add(new Campanha("2", "Campanha 2", timeCoracao, dataInicio, dataFimVigenciaCampanha2));
        campanhasRetornadas.add(new Campanha("1", "Campanha 1", timeCoracao, dataInicio, dataFimVigenciaCampanha1));

        return Flux.fromIterable(campanhasRetornadas);
    }

    public static Flux<Campanha> retornarListaCampanhasDesativadas() {
        List<Campanha> campanhasRetornadas = new ArrayList<>();

        TimeCoracao timeCoracao = new TimeCoracao("1",  MeuTimeCoracao.SAO_PAULO);
        LocalDate dataInicio = LocalDate.of(1900, 10, 1);
        LocalDate dataFimVigenciaCampanha2 = LocalDate.of(1900, 10, 2);
        LocalDate dataFimVigenciaCampanha1 = LocalDate.of(1900, 10, 3);

        campanhasRetornadas.add(new Campanha("2", "Campanha 2", timeCoracao, dataInicio, dataFimVigenciaCampanha2));
        campanhasRetornadas.add(new Campanha("1", "Campanha 1", timeCoracao, dataInicio, dataFimVigenciaCampanha1));

        return Flux.fromIterable(campanhasRetornadas);
    }

    public static Flux<Campanha> retornarCampanhasDesativadasParaSalvar() {
        List<Campanha> campanhasRetornadas = new ArrayList<>();

        TimeCoracao timeCoracao = new TimeCoracao("1",  MeuTimeCoracao.SAO_PAULO);
        LocalDate dataInicio = LocalDate.of(1900, 10, 1);

        LocalDate dataFimVigenciaCampanha3 = LocalDate.of(1900, 10, 3);
        LocalDate dataFimVigenciaCampanha2 = LocalDate.of(1900, 10, 4);
        LocalDate dataFimVigenciaCampanha1 = LocalDate.of(1900, 10, 5);

        campanhasRetornadas.add(new Campanha("3", "Campanha 3", timeCoracao, dataInicio, dataFimVigenciaCampanha3));
        campanhasRetornadas.add(new Campanha("2", "Campanha 2", timeCoracao, dataInicio, dataFimVigenciaCampanha2));
        campanhasRetornadas.add(new Campanha("1", "Campanha 1", timeCoracao, dataInicio, dataFimVigenciaCampanha1));

        return Flux.fromIterable(campanhasRetornadas);
    }
}
