package br.com.ufms.web.trabalho.beatriz;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("beatriz")
public class MinhaConfiguration {

    @Bean("nomeConfiguracao")
    public String hello(){
        return "Hello World";
    }
}
