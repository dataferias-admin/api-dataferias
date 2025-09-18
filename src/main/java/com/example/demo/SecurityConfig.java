package com.example.demo;

import com.example.demo.jwt.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                // Público
                .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                .requestMatchers(HttpMethod.POST, "/funcionarios").permitAll()
                .requestMatchers(HttpMethod.GET, "/funcionarios").permitAll()

                // Autenticado
                .requestMatchers(HttpMethod.POST, "/solicitacoes").authenticated()
                .requestMatchers(HttpMethod.GET, "/solicitacoes/funcionario/**").authenticated()

                // Autenticado e gestor (demais checagens via @PreAuthorize nos controllers)
                .requestMatchers(HttpMethod.GET, "/solicitacoes").authenticated()
                .requestMatchers(HttpMethod.GET, "/solicitacoes/pendentes").authenticated()
                .requestMatchers(HttpMethod.PATCH, "/solicitacoes/**").authenticated()
                .requestMatchers(HttpMethod.GET, "/info/funcionarios/count").authenticated()
                .requestMatchers(HttpMethod.GET, "/info/solicitacoes/pendentes").authenticated()
                .requestMatchers(HttpMethod.GET, "/info/solicitacoes/aprovadas").authenticated()
                .requestMatchers(HttpMethod.GET, "/info/solicitacoes/rejeitadas").authenticated()

                // Swagger e outros públicos
                .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()

                // Qualquer outra rota precisa estar autenticado
                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
