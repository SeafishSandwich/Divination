package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/images/**",
                                "/users/**",
                                "/login_div.html",
                                "/signup_div.html",
                                "/home.html",
                                "/error.html",
                                "/hexaFirstStage.html",
                                "/hexaSecondStage.html",
                                "/css/**",
                                "/js/**",
                                "/div/**",
                                "/fonts/**"
                        ).permitAll()
                        .anyRequest().authenticated()
                )

                .formLogin()
                .loginPage("/login_div.html")
                .loginProcessingUrl("/login")
                .permitAll()

                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login_div.html")
                .permitAll()

                .and()
                .rememberMe()
                .key("uniqueAndSecret")
                .tokenValiditySeconds(86400)
                .rememberMeParameter("remember-me");

        return http.build();
    }
}
