package gc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class RegistroApplication {
    public static void main(String args[]) {
        SpringApplication.run(ConfiguracaoApplication.class, args);
    }
}
