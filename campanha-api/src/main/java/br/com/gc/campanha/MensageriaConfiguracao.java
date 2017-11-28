package br.com.gc.campanha;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

/***
 * Classe responsavel carregar as configurações para enviar dados para fila, no caso esta sendo usado
 * em MOCK (memória)
 */
@Configuration
public class MensageriaConfiguracao {

    @Value("${default.brocker.url}")
    private String defaultBrokerUrl;

    @Value("${queue.campanha}")
    public String queueCampanha;

    /**
     * Constroi a instacia para carregar o active MQ na memória
     * @return objeto de conexão
     */
    @Bean
    public ActiveMQConnectionFactory connectionFactory(){
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(defaultBrokerUrl);
        return connectionFactory;
    }

    /**
     * Template do JMS com as configurações da fila
     * @return objeto para gerenciar envio ou recebimento dos dados na fila
     */
    @Bean
    public JmsTemplate jmsTemplate(){
        JmsTemplate template = new JmsTemplate();
        template.setPubSubDomain(true);
        template.setConnectionFactory(connectionFactory());
        template.setDefaultDestinationName(queueCampanha);
        return template;
    }

}
