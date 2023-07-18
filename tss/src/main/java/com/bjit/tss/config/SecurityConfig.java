package com.bjit.tss.config;

import com.bjit.tss.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserRepository userRepository;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .antMatchers("/user/register").permitAll()
                .antMatchers("/user/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
        ;
        return http.build();
    }
}


//.csrf()
//        .disable()
//        .authorizeHttpRequests()
//        .requestMatchers("/user/register")
//        .permitAll()
//        .requestMatchers("/user/login")
//        .permitAll()
//        .requestMatchers("/books/create").hasAuthority("ADMIN")
//        .requestMatchers("/books/update/**").hasAuthority("ADMIN")
//        .requestMatchers("/books/all").hasAnyAuthority("ADMIN", "CUSTOMER")
//        .requestMatchers("/books/id/**").hasAnyAuthority("ADMIN", "CUSTOMER")
//        .requestMatchers("/books/author/**").hasAnyAuthority("ADMIN", "CUSTOMER")
//        .requestMatchers("/books/delete/**").hasAuthority("ADMIN")
//        .anyRequest()
//        .authenticated()
//        .and()
//        .sessionManagement()
//        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//        .and()
//        .authenticationProvider(authenticationProvider)
//        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
//        ;