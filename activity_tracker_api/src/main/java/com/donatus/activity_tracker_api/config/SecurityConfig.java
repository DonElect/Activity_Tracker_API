package com.donatus.activity_tracker_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);

        manager.setUsersByUsernameQuery("SELECT email, password, active FROM clients WHERE email = ?");
        manager.setAuthoritiesByUsernameQuery("SELECT email, role FROM clients WHERE email = ?");

        return manager;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(configure ->
                configure
                        .requestMatchers(antMatcher(HttpMethod.GET, "/client/**")).hasRole("USER")
                        .requestMatchers(antMatcher(HttpMethod.POST, "/client/login")).permitAll()
                        .requestMatchers(antMatcher(HttpMethod.POST, "/client/signup")).permitAll()
                        .requestMatchers(antMatcher(HttpMethod.DELETE, "/client/**")).hasRole("USER")
                        // Task security filter
                        .requestMatchers(antMatcher(HttpMethod.GET, "/task/**")).hasRole("USER")
                        .requestMatchers(antMatcher(HttpMethod.GET, "/task/**/task_id/**")).hasRole("USER")
                        .requestMatchers(antMatcher(HttpMethod.POST, "/task/**")).hasRole("USER")
                        .requestMatchers(antMatcher(HttpMethod.PUT, "/task/**/task_id/**")).hasRole("USER")
                        .requestMatchers(antMatcher(HttpMethod.DELETE, "/task/**/task_id/**")).hasRole("USER")
                        .anyRequest().authenticated());

        http.httpBasic(Customizer.withDefaults());

        http.csrf(csrf -> csrf.disable());

        return http.build();
    }
}
