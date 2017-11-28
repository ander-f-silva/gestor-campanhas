package br.com.gc.campanha.component;

import br.com.gc.campanha.MensageriaConfiguracao;
import br.com.gc.campanha.domain.Campanha;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;


/***
 * Classe responsavel reponsavel por enviar as campanhas atualizadas para fila
 */
@Slf4j
@Component
@AllArgsConstructor
public class Notificador {

    private JmsTemplate jmsTemplate;

    /**
     * Método para enviar o
     * @param campanha
     */
    public void enviar (Campanha campanha) {
        log.info("Notificar campanha modificada");
        jmsTemplate.convertAndSend(MensageriaConfiguracao.CAMPANHA_QUEUE, campanha);
    }
}
