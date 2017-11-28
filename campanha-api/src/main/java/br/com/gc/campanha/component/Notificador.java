package br.com.gc.campanha.component;

import br.com.gc.campanha.domain.Campanha;
import br.com.gc.campanha.domain.Notificacao;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;


/***
 * Classe responsavel reponsavel por enviar as campanhas atualizadas para fila
 */
@Slf4j
@Component
public class Notificador {

    @Value("${queue.campanha}")
    public String queueCampanha;

    @Autowired
    private JmsTemplate jmsTemplate;

    /**
     * Método para enviar as campanhas alteradas para fila
     * @param notificacao
     */
    public void enviar (Notificacao<Campanha> notificacao) {
        log.info("Notificar campanha modificada");
        jmsTemplate.convertAndSend(queueCampanha, notificacao);
    }
}
