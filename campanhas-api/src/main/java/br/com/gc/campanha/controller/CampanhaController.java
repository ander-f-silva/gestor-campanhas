package br.com.gc.campanha.controller;

import br.com.gc.campanha.domain.TimeCoracao;
import br.com.gc.campanha.exception.DataInicialMaiorDataFinalException;
import br.com.gc.campanha.domain.Campanha;
import br.com.gc.campanha.service.CampanhaService;
import br.com.gc.campanha.exception.CampanhaNaoExisteException;
import br.com.gc.campanha.exception.IdMeuTimeInvalidoException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ServerWebInputException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.*;

/***
 * @Class Api responsavel pela serviços de restfull pelos dados da campanha.
 */
@Slf4j
@RestController
@AllArgsConstructor(onConstructor = @_(@Autowired))
@RequestMapping(value = "/api/v1/campanhas", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CampanhaController {

    private CampanhaService campanhaService;

    /**
     * Serviço rest para listar todas as campanhas.
     *
     * @return capamanhas vigentes
     */
    @GetMapping
    public Flux<Campanha> listar() {
        log.info("Acessando a o serviço para listar as campanhas.");
        return campanhaService.listar();
    }

    /**
     * Serviço rest adicionar uma nova campanha.
     *
     * @param campanha a ser adicionada
     * @return capamanhas vigentes
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Flux<Campanha> adicionar(@RequestBody @Valid Campanha campanha) {
        log.info("Acessando a o serviço para adicionar uma nova campanha.");
        return campanhaService.adicionar(campanha);
    }

    /**
     * Serviço rest atualizar uma campanha existente.
     *
     * @param id       do campanha a ser atualizada
     * @param campanha a ser alterada
     * @return capamanha
     */
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Mono<Campanha> alterar(@PathVariable("id") final String id, @Valid @RequestBody Campanha campanha) {
        log.info("Acessando a o serviço para consulta a campanha.");
        return campanhaService.atualizar(id, campanha);
    }

    /**
     * Serviço rest para remover uma campanha.
     *
     * @param id do campanha a ser removida
     */
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public Mono<Void> remover(@PathVariable("id") final String id) {
        log.info("Acessando a o serviço para remover a campanha.");
        return campanhaService.remover(id);
    }

    /**
     * Serviço rest consultar uma campanha existente.
     *
     * @param id da campanha a ser consultada
     * @return capamanha
     */
    @GetMapping(value = "/{id}")
    public Mono<Campanha> consultar(@PathVariable("id") final String id) {
        log.info("Acessando a o serviço para consultar a campanha.");
        return campanhaService.consultar(id);
    }

    /**
     * Serviço rest pesquisar as campanhas informando o id do time do coração.
     *
     * @param id do time do coração
     * @return capamanhas
     */
    @GetMapping("/time-coracao/{timeCoracaoId}")
    public Flux<Campanha> pesquisar(@PathVariable(value = "timeCoracaoId") final String timeCoracaoId) {
        log.info("Acessando a o serviço para pesquisar as campanhas.");
        return campanhaService.pesquisar(new TimeCoracao(timeCoracaoId, null));
    }

    /**
     * Handler para erro de conflito no request.
     *
     * @param ex erro ocorrido durante a validação dos dados
     * @return responta para o usuário
     */
    @ExceptionHandler(value = {CampanhaNaoExisteException.class, DataInicialMaiorDataFinalException.class, IdMeuTimeInvalidoException.class})
    public ResponseEntity manipularErroValidacoes(Exception ex) {
        log.error("Erro ocorrido durante a execução do request.", ex);

        Map<String, String> messages = new HashMap<>();
        messages.put("error", ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(messages);
    }

    /**
     * Handler para erro de request com má formatação.
     *
     * @param ex erro ocorrido durante a validação dos dados
     * @return responta para o usuário
     */
    @ExceptionHandler(value = ServerWebInputException.class)
    public ResponseEntity manipularErroEntrada(ServerWebInputException ex) throws Exception {
        log.error("Erro ocorrido durante a execução do request.", ex);

        Map<String, String> messages = new HashMap<>();

        messages.put("error", ((WebExchangeBindException) ex).getFieldError().getDefaultMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(messages);
    }

    /**
     * Handler para erro de request com má formatação.
     *
     * @param ex erro ocorrido durante a validação dos dados
     * @return responta para o usuário
     */
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity manipularErroException(RuntimeException ex) throws Exception {
        log.error("Erro ocorrido durante a execução do request.", ex);

        Map<String, String> messages = new HashMap<>();

        messages.put("error", ((WebExchangeBindException) ex).getFieldError().getDefaultMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(messages);
    }
}
