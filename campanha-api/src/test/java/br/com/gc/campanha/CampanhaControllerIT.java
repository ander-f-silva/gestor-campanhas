package br.com.gc.campanha;


import br.com.gc.CampanhaApplication;
import br.com.gc.LeitorArquivo;
import br.com.gc.campanha.domain.Campanha;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@SpringBootTest(classes = CampanhaApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
public class CampanhaControllerIT extends AbstractTestNGSpringContextTests {

    @Autowired
    private ApplicationContext context;

    private WebTestClient clientTest;

    private LeitorArquivo leitorArquivo = new LeitorArquivo();

    @LocalServerPort
    private int port;

    @BeforeClass
    public void setUp() {
        clientTest = WebTestClient.bindToApplicationContext(context)
                .configureClient()
                .baseUrl("http://localhost:" + port)
                .build();
    }

    @Test(priority = 1)
    public void deveImpedirGravacaoComNomeNull() throws Exception {
        final Campanha entrada = leitorArquivo.converterJsonToClass("post/request_campo_nome_null.json", Campanha.class);

        this.clientTest
                .post().uri("/api/v1/campanhas")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(entrada))
                .exchange()
                .expectStatus().is4xxClientError()
                .expectBody()
                .jsonPath("$.error").isEqualTo("O nome é de preenchimento obrigatório.");
    }

    @Test(priority = 2)
    public void deveImpedirGravacaoIdTimeCoracaoNull() throws Exception {
        final Campanha entrada = leitorArquivo.converterJsonToClass("post/request_campo_id_time_null.json", Campanha.class);

        this.clientTest
                .post().uri("/api/v1/campanhas")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(entrada))
                .exchange()
                .expectStatus().is4xxClientError()
                .expectBody()
                .jsonPath("$.error").isEqualTo("O campo meu time do coração é de preenchimento obrigatório.");
    }

    @Test(priority = 3)
    public void deveImpedirGravacaoDataInicioNull() throws Exception {
        final Campanha entrada = leitorArquivo.converterJsonToClass("post/request_campo_data_inicio_null.json", Campanha.class);

        this.clientTest
                .post().uri("/api/v1/campanhas")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(entrada))
                .exchange()
                .expectStatus().is4xxClientError()
                .expectBody()
                .jsonPath("$.error").isEqualTo("A data inicio é de preenchimento obrigatório.");
    }

    @Test(priority = 4)
    public void deveImpedirGravacaoDataFimNull() throws Exception {
        final Campanha entrada = leitorArquivo.converterJsonToClass("post/request_campo_data_fim_null.json", Campanha.class);

        this.clientTest
                .post().uri("/api/v1/campanhas")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(entrada))
                .exchange()
                .expectStatus().is4xxClientError()
                .expectBody()
                .jsonPath("$.error").isEqualTo("A data fim é de preenchimento obrigatório.");
    }

    @Test(priority = 5)
    public void deveImpedirGravacaoComDataInicialMaiorDataFinal() throws Exception {
        final Campanha entrada = leitorArquivo.converterJsonToClass("post/request_data_inicial_maior_data_final.json", Campanha.class);

        this.clientTest
                .post().uri("/api/v1/campanhas")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(entrada))
                .exchange()
                .expectStatus().is4xxClientError()
                .expectBody()
                .jsonPath("$.error").isEqualTo("A data final precisa ser maior que a data inicial.");
    }

    @Test(priority = 6)
    public void deveImpedirGracaoComIdTimeCoracaoInvalido() throws Exception {
        final Campanha entrada = leitorArquivo.converterJsonToClass("post/request_id_invalido.json", Campanha.class);

        this.clientTest
                .post().uri("/api/v1/campanhas")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(entrada))
                .exchange()
                .expectStatus().is4xxClientError()
                .expectBody()
                .jsonPath("$.error").isEqualTo("O id do meu time do coração é informado não existe.");
    }

    @Test(priority = 7)
    public void deveSalvarCampanhaAtivaComSucesso() throws Exception {
        final Campanha entrada = leitorArquivo.converterJsonToClass("post/request_ativo_sucesso.json", Campanha.class);

        this.clientTest
                .post().uri("/api/v1/campanhas")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(entrada))
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$[0].id").isEqualTo("abcativo")
                .jsonPath("$[0].nome").isEqualTo("Campanha1-Ativada")
                .jsonPath("$[0].time-coracao.id").isEqualTo("597f6269cce6ff5251a9da0f")
                .jsonPath("$[0].data-inicio-vigencia").isEqualTo("22/12/2100")
                .jsonPath("$[0].data-fim-vigencia").isEqualTo("25/12/2100");
    }

    @Test(priority = 8)
    public void deveSalvarCampanhaDesativadaComSucesso() throws Exception {
        final Campanha entrada = leitorArquivo.converterJsonToClass("post/request_desativado_sucesso.json", Campanha.class);

        this.clientTest
                .post().uri("/api/v1/campanhas")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(entrada))
                .exchange()
                .expectStatus().isCreated()
                .expectBody().json("[]");
    }

    @Test(priority = 9)
    public void deveImpedirAlterarComNomeNull() throws Exception {
        final Campanha entrada = leitorArquivo.converterJsonToClass("put/request_campo_nome_null.json", Campanha.class);

        this.clientTest
                .put().uri("/api/v1/campanhas/{id}", "abcativo")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(entrada))
                .exchange()
                .expectStatus().is4xxClientError()
                .expectBody()
                .jsonPath("$.error").isEqualTo("O nome é de preenchimento obrigatório.");
    }

    @Test(priority = 10)
    public void deveImpedirAlterarIdTimeCoracaoNull() throws Exception {
        final Campanha entrada = leitorArquivo.converterJsonToClass("put/request_campo_id_time_null.json", Campanha.class);

        this.clientTest
                .put().uri("/api/v1/campanhas/{id}", "abcativo")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(entrada))
                .exchange()
                .expectStatus().is4xxClientError()
                .expectBody()
                .jsonPath("$.error").isEqualTo("O campo meu time do coração é de preenchimento obrigatório.");
    }

    @Test(priority = 11)
    public void deveImpedirAlterarDataInicioNull() throws Exception {
        final Campanha entrada = leitorArquivo.converterJsonToClass("put/request_campo_data_inicio_null.json", Campanha.class);

        this.clientTest
                .put().uri("/api/v1/campanhas/{id}", "abcativo")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(entrada))
                .exchange()
                .expectStatus().is4xxClientError()
                .expectBody()
                .jsonPath("$.error").isEqualTo("A data inicio é de preenchimento obrigatório.");
    }

    @Test(priority = 12)
    public void deveImpedirAlterarDataFimNull() throws Exception {
        final Campanha entrada = leitorArquivo.converterJsonToClass("put/request_campo_data_fim_null.json", Campanha.class);

        this.clientTest
                .put().uri("/api/v1/campanhas/{id}", "abcativo")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(entrada))
                .exchange()
                .expectStatus().is4xxClientError()
                .expectBody()
                .jsonPath("$.error").isEqualTo("A data fim é de preenchimento obrigatório.");
    }

    @Test(priority = 13)
    public void deveImpedirAlteraroComDataInicialMaiorDataFinal() throws Exception {
        final Campanha entrada = leitorArquivo.converterJsonToClass("put/request_data_inicial_maior_data_final.json", Campanha.class);

        this.clientTest
                .put().uri("/api/v1/campanhas/{id}", "abcativo")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(entrada))
                .exchange()
                .expectStatus().is4xxClientError()
                .expectBody()
                .jsonPath("$.error").isEqualTo("A data final precisa ser maior que a data inicial.");
    }

    @Test(priority = 14)
    public void deveImpedirAlterarComIdTimeCoracaoInvalido() throws Exception {
        final Campanha entrada = leitorArquivo.converterJsonToClass("put/request_id_invalido.json", Campanha.class);

        this.clientTest
                .put().uri("/api/v1/campanhas/{id}", "abcativo")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(entrada))
                .exchange()
                .expectStatus().is4xxClientError()
                .expectBody()
                .jsonPath("$.error").isEqualTo("O id do meu time do coração é informado não existe.");
    }

    @Test(priority = 15)
    public void deveAlterarCampanhaAtivaComSucesso() throws Exception {
        final Campanha entrada = leitorArquivo.converterJsonToClass("put/request_sucesso.json", Campanha.class);

        this.clientTest
                .put().uri("/api/v1/campanhas/{id}", "abcativo")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(entrada))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo("abcativo")
                .jsonPath("$.nome").isEqualTo("Campanha1-Ativada-Alterado")
                .jsonPath("$.time-coracao.id").isEqualTo("597f6269cce6ff5251a9da0f")
                .jsonPath("$.data-inicio-vigencia").isEqualTo("22/12/2100")
                .jsonPath("$.data-fim-vigencia").isEqualTo("25/12/2100");
    }

    @Test(priority = 16)
    public void deveConsultarUmaComSucesso() throws Exception {
        this.clientTest
                .get().uri("/api/v1/campanhas/{id}", "abcativo")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().is2xxSuccessful();
    }

    @Test(priority = 17)
    public void deveInformarQueCampanhaNaoExiste() throws Exception {
        this.clientTest
                .get().uri("/api/v1/campanhas/{id}", "abcnaoexiste")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().is4xxClientError()
                .expectBody()
                .jsonPath("$.error").isEqualTo("A campanha informada não existe.");
    }

    @Test(priority = 18)
    public void deveListarComSucesso() throws Exception {
        this.clientTest
                .get().uri("/api/v1/campanhas")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().is2xxSuccessful();
    }

    @Test(priority = 19)
    public void deveRemoverComSucesso() throws Exception {

        this.clientTest
                .delete().uri("/api/v1/campanhas/{id}", "abcativo")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().is2xxSuccessful();
    }

    @Test(priority = 20)
    public void deveInformarQueCampanhaNaoExisteQuandoDeletar() throws Exception {

        this.clientTest
                .delete().uri("/api/v1/campanhas/{id}", "abcnaoexiste")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().is4xxClientError()
                .expectBody()
                .jsonPath("$.error").isEqualTo("A campanha informada não existe.");
    }

    @Test(priority = 21)
    public void devePequisarAsCampanhasInformandoTimeCoracaoId() throws Exception {

        this.clientTest
                .get().uri("/api/v1/campanhas/time-coracao/{id}","597f6269cce6ff5251a9da0f")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody();
    }

}
