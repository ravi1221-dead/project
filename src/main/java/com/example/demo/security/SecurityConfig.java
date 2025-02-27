package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/req/login", "/req/signup","/req/index", "/css/**", "/js/**", "/images/**").permitAll() // Allow access to login, signup & static resources
                .anyRequest().authenticated()
            )
            .formLogin(login -> login
                .loginPage("/req/login")  // Custom login page
                .loginProcessingUrl("/req/doLogin")  // This URL will handle login requests
                .defaultSuccessUrl("/req/index", true)  // Redirect to home page after login
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/req/logout")
                .logoutSuccessUrl("/req/login")
                .permitAll()
            )
            .csrf(csrf -> csrf.disable());  // Disable CSRF if not using forms

        return http.build();
    }
}
