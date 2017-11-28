package br.com.gc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import javax.jms.ConnectionFactory;

/***
 * Classe responsavel para inicializar o serviço Netty.
 */
@SpringBootApplication
@EnableReactiveMongoRepositories
@EnableFeignClients
@EnableCircuitBreaker
public class SocioTorcedorApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocioTorcedorApplication.class, args);
	}
}
