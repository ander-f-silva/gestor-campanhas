package br.com.gc.campanha;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

@Configuration
public class MensageriaConfiguracao {

    private static final String DEFAULT_BROKER_URL = "vm://localhost?broker.persistent=false";

    public static final String CAMPANHA_QUEUE = "campanha-queue";

    @Bean
    public ActiveMQConnectionFactory connectionFactory(){
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(DEFAULT_BROKER_URL);
        return connectionFactory;
    }

    @Bean
    public JmsTemplate jmsTemplate(){
        JmsTemplate template = new JmsTemplate();
        template.setPubSubDomain(true);
        template.setConnectionFactory(connectionFactory());
        template.setDefaultDestinationName(CAMPANHA_QUEUE);
        return template;
    }

}
