package com.lookie.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppConfig {
   @Bean // IoC가 됨
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
