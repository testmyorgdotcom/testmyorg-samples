package com.testmyorg.samples.login;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CrendetialsProviderContext {
    @Bean
    CredentialsProvider credentialsProvider(){
        return new CredentialsProvider();
    }
}
