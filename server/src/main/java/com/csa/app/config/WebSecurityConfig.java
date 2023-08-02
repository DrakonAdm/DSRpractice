package com.csa.app.config;

import com.csa.app.security.JWTFilter;
import com.csa.app.service.person.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig{

    @Bean
    public SecurityFilterChain configure(HttpSecurity http, JWTFilter jwtFilter) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(HttpMethod.POST, "/api-auth/login", "/api-auth/auth", "/api-auth/registration").permitAll()
                        .requestMatchers(HttpMethod.GET,  "/api-auth/me").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api-auth/logout").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api-dataPerson/profile", "/api-dataPerson/by-username").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api-dataPerson/search").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/api-dataPerson/{id}/password", "/api-dataPerson/update").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api-friends/unseen-invite-friends/{personId}", "/api-friends/{personId}").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api-friends/remove-friendship", "/api-friends/add-friendship", "/api-friends/create-friendship", "/api-friends/delete-invite").authenticated()
                        .requestMatchers(HttpMethod.GET,  "/api-message/messages/{senderId}/{recipientId}/count", "/api-message/messages/{senderId}/{recipientId}", "/api-message/messages/{id}").authenticated()
                        .anyRequest().denyAll())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .headers(httpSecurityHeadersConfigurer -> httpSecurityHeadersConfigurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .exceptionHandling(httpSecurityExceptionHandlingConfigurer -> httpSecurityExceptionHandlingConfigurer.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(PersonService personService) {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(personService);
        return daoAuthenticationProvider;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}


//.requestMatchers(HttpMethod.HEAD, "/api-message/chat").authenticated()