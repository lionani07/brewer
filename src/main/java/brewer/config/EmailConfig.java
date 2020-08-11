package brewer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

@Configuration
@PropertySource({"classpath:env/mail.properties"})
public class EmailConfig {

    @Autowired
    private Environment environment;

    @Bean
    public void readEmailConfig() {
        System.out.println(">>> lendo...");
        System.out.println(">>> user: " + environment.getProperty("user"));
        System.out.println(">>> password: " + environment.getProperty("password"));
    }
}
