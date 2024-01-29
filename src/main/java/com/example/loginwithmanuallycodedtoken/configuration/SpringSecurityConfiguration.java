package com.example.loginwithmanuallycodedtoken.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //If csrf and cors are not disable then it wont allow request matcher for auth/login

        //http.csrf().disable().cors().disable();  // This is depreciated, so we use below one.
        http.cors(AbstractHttpConfigurer::disable);
        http.csrf(AbstractHttpConfigurer::disable);

        http.
                authorizeHttpRequests((requests) -> requests
                        .anyRequest().permitAll()

                );

        return http.build();
    }
}
