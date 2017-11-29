package br.com.gc.sociotorcedor;


import br.com.gc.LeitorArquivo;
import br.com.gc.SocioTorcedorApplication;
import br.com.gc.sociotorcedor.domain.SocioTorcedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@SpringBootTest(classes = SocioTorcedorApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
public class SocioTorcedorControllerIT extends AbstractTestNGSpringContextTests {

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
    public void deverCadastrarUmSocioTorcedorComSucesso() throws Exception {
        final SocioTorcedor entrada = leitorArquivo.converterJsonToClass("post/request_cadastro.json", SocioTorcedor.class);

        this.clientTest
                .post().uri("/api/v1/socios-torcedores")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(entrada))
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .jsonPath("$.nome").isEqualTo("Anderson Silva")
                .jsonPath("$.email").isEqualTo("infoander@gmail.com")
                .jsonPath("$.time-coracao.id").isEqualTo("597f6269cce6ff5251a9da0f")
                .jsonPath("$.data-nascimento").isEqualTo("01/11/2017");
    }

}
