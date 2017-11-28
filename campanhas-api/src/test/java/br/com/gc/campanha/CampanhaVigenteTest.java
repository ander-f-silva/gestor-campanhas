package br.com.gc.campanha;

import br.com.gc.campanha.component.CampanhaVigente;
import br.com.gc.campanha.component.Notificador;
import br.com.gc.campanha.domain.Campanha;
import br.com.gc.campanha.domain.TimeCoracao;
import br.com.gc.campanha.repository.CampanhaRepository;
import br.com.gc.campanha.domain.MeuTimeCoracao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.*;

/**
 * @Class Teste Unitário dos CampanhasVigentes
 */
@RunWith(MockitoJUnitRunner.class)
public class CampanhaVigenteTest {

    private static final String PATTERN_DATA_FORMAT = "dd/MM/yyyy";

    @InjectMocks
    private CampanhaVigente campanhaVigente;

    @Mock
    private CampanhaRepository repository;

    @Mock
    private Notificador notificador;

    @Test
    public void deveRetornarListaCampanhasVigentesAtivas() {

        given(repository.findByDataInicioVigenciaAndDataFimVigencia(
                eq(LocalDate.of(2100, 10, 01)),
                eq(LocalDate.of(2100, 10, 03)),
                eq(Sort.by(new Sort.Order(Sort.Direction.ASC, "dataFimVigencia"))))
        ) .willReturn(TestFactory.retornarListaCampanhasAtivas());

        given(repository.saveAll(any(List.class))).willReturn(TestFactory.retornarCampanhasAtivasParaSalvar());

        doNothing().when(notificador).enviar(any(Campanha.class));

        Campanha campanha = new Campanha();

        campanha.setId("3");
        campanha.setNome("Campanha 3");
        campanha.setTimeCoracao(new TimeCoracao("1",  MeuTimeCoracao.SAO_PAULO));
        campanha.setDataInicioVigencia(LocalDate.of(2100, 10, 1));
        campanha.setDataFimVigencia(LocalDate.of(2100, 10, 3));

        List<Campanha> campanhas = campanhaVigente.atualizar(campanha).toStream().collect(Collectors.toList());

        verify(repository).findByDataInicioVigenciaAndDataFimVigencia(
                eq(LocalDate.of(2100, 10, 1)),
                eq(LocalDate.of(2100, 10, 3)),
                eq(Sort.by(new Sort.Order(Sort.Direction.ASC, "dataFimVigencia"))));

        verify(repository).saveAll(any(List.class));

        assertThat(campanhas, notNullValue());
        assertThat(campanhas.size(), equalTo(3));

        assertThat(campanhas.get(0).getNome(), equalTo("Campanha 3"));
        assertThat(campanhas.get(0).getTimeCoracao().getId(), equalTo("1"));
        assertThat(campanhas.get(0).getDataInicioVigencia().format(DateTimeFormatter.ofPattern(PATTERN_DATA_FORMAT)), equalTo("01/10/2100"));
        assertThat(campanhas.get(0).getDataFimVigencia().format(DateTimeFormatter.ofPattern(PATTERN_DATA_FORMAT)), equalTo("03/10/2100"));

        assertThat(campanhas.get(1).getNome(), equalTo("Campanha 2"));
        assertThat(campanhas.get(1).getTimeCoracao().getId(), equalTo("1"));
        assertThat(campanhas.get(1).getDataInicioVigencia().format(DateTimeFormatter.ofPattern(PATTERN_DATA_FORMAT)), equalTo("01/10/2100"));
        assertThat(campanhas.get(1).getDataFimVigencia().format(DateTimeFormatter.ofPattern(PATTERN_DATA_FORMAT)), equalTo("04/10/2100"));

        assertThat(campanhas.get(2).getNome(), equalTo("Campanha 1"));
        assertThat(campanhas.get(2).getTimeCoracao().getId(), equalTo("1"));
        assertThat(campanhas.get(2).getDataInicioVigencia().format(DateTimeFormatter.ofPattern(PATTERN_DATA_FORMAT)), equalTo("01/10/2100"));
        assertThat(campanhas.get(2).getDataFimVigencia().format(DateTimeFormatter.ofPattern(PATTERN_DATA_FORMAT)), equalTo("05/10/2100"));
    }

    @Test
    public void deveRetornarListaVazia() {

        given(repository.findByDataInicioVigenciaAndDataFimVigencia(
                eq(LocalDate.of(1900, 10, 01)),
                eq(LocalDate.of(1900, 10, 03)),
                eq(Sort.by(new Sort.Order(Sort.Direction.ASC, "dataFimVigencia"))))
        ) .willReturn(TestFactory.retornarListaCampanhasDesativadas());

        given(repository.saveAll(any(List.class))).willReturn(TestFactory.retornarCampanhasDesativadasParaSalvar());

        Campanha campanha = new Campanha();

        campanha.setId("3");
        campanha.setNome("Campanha 3");
        campanha.setTimeCoracao(new TimeCoracao("1",  MeuTimeCoracao.SAO_PAULO));
        campanha.setDataInicioVigencia(LocalDate.of(1900, 10, 1));
        campanha.setDataFimVigencia(LocalDate.of(1900, 10, 3));

        List<Campanha> campanhas = campanhaVigente.atualizar(campanha).toStream().collect(Collectors.toList());

        verify(repository).findByDataInicioVigenciaAndDataFimVigencia(
                eq(LocalDate.of(1900, 10, 1)),
                eq(LocalDate.of(1900, 10, 3)),
                eq(Sort.by(new Sort.Order(Sort.Direction.ASC, "dataFimVigencia"))));

        verify(repository).saveAll(any(List.class));

        assertThat(campanhas, notNullValue());
        assertThat(campanhas.size(), equalTo(0));
    }

}