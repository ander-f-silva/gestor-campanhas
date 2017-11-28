package br.com.gc.sociotorcedor;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.stereotype.Service;

import javax.jms.ConnectionFactory;

/**
 * Classe responsevel por carregar as configurações do ouvinte da fila
 * (Tudo MOCK)
 */
@Configuration
@Slf4j
@EnableJms
@AllArgsConstructor(onConstructor = @_(@Autowired))
public class OuvinteConfiguracao {

    private ConnectionFactory connectionFactory;

    /**
     * Método para configurar a instacia do ouvinte da fila quando haver uma atualização
     * @return instancia do container
     */
    @Bean
    public DefaultJmsListenerContainerFactory containerFactory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setConcurrency("3-10");
        return factory;
    }

}
