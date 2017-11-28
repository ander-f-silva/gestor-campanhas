package br.com.gc.sociotorcedor.controller;

import br.com.gc.sociotorcedor.domain.SocioTorcedor;
import br.com.gc.sociotorcedor.exception.EmailCadastradoException;
import br.com.gc.sociotorcedor.service.SocioTorcedorService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/***
 * @Class Api responsavel pela serviços de restfull pelos dados do socio torcedor.
 */
@Slf4j
@RestController
@AllArgsConstructor(onConstructor = @_(@Autowired))
@RequestMapping(value = "/api/v1/socios-torcedores", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SocioTorcedorController {

    private SocioTorcedorService socioTorcedorService;

    /**
     * Serviço rest adicionar um novo socio torcedor.
     *
     * @param socio torcedor a ser adicionado
     * @return socio torcedor
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<SocioTorcedor> adicionar(@RequestBody @Valid SocioTorcedor socioTorcedor) {
        log.info("Acessando a o serviço para adicionar um novo socio torcedor.");
        return socioTorcedorService.salvar(socioTorcedor);
    }

    /**
     * Handler para erro de conflito no request.
     *
     * @param ex erro ocorrido durante a validação dos dados
     * @return responta para o usuário
     */
    @ExceptionHandler(value = {EmailCadastradoException.class})
    public ResponseEntity manipularErroValidacoes(Exception ex) {
        log.error("Erro ocorrido durante a execução do request.", ex);

        Map<String, String> messages = new HashMap<>();
        messages.put("error", ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(messages);
    }
}
