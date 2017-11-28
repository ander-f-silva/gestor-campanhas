package br.com.gc.sociotorcedor.component;

import br.com.gc.sociotorcedor.domain.Campanha;
import br.com.gc.sociotorcedor.domain.Notificacao;
import br.com.gc.sociotorcedor.repository.NotificacaoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.jms.Message;
import javax.jms.Session;
import java.time.LocalDate;

@Slf4j
@Component
@AllArgsConstructor
public class Notificador {

    private NotificacaoRepository notificacaoRepository;

    @JmsListener(destination = "campanha-queue")
    public void mensagemRecebidas(@Payload Campanha campanha, @Headers MessageHeaders headers, Message message, Session session) {
        log.info("Salvando uma nova campanha que foi atualizada");
        notificacaoRepository.save(new Notificacao<Campanha>(campanha, LocalDate.now()));
    }
}
