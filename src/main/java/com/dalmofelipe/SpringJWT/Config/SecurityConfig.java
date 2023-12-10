package com.dalmofelipe.SpringJWT.Config;

import com.dalmofelipe.SpringJWT.Auth.AuthService;
import com.dalmofelipe.SpringJWT.Auth.AuthTokenFilter;
import com.dalmofelipe.SpringJWT.Auth.TokenService;
import com.dalmofelipe.SpringJWT.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig  {

    @Autowired
    private AuthService authService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserRepository userRepository;

    @Bean
    @Primary
    public AuthenticationManagerBuilder configureAuthenticationManagerBuilder
            (AuthenticationManagerBuilder authenticationManagerBuilder)
                throws Exception {
        authenticationManagerBuilder
                .userDetailsService(authService).passwordEncoder(this.passwordEncoder());
        return authenticationManagerBuilder;
    }

    @Bean
    public AuthenticationManager authenticationManager
            (AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .cors(AbstractHttpConfigurer::disable)
            .csrf(AbstractHttpConfigurer::disable)
            .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .addFilterBefore(new AuthTokenFilter(this.tokenService, this.userRepository),
                    UsernamePasswordAuthenticationFilter.class)
            .authorizeHttpRequests((authorize) -> authorize
                    .requestMatchers("error").permitAll()
                    .requestMatchers("/admin").hasRole("ADMIN")
                    .requestMatchers("/roles").permitAll()//.hasRole("ADMIN")
                    .requestMatchers("/users/**").permitAll()//.hasRole("USER")
                    .requestMatchers("/auth/**").permitAll()
                    .requestMatchers("/h2/**").permitAll()
            )
            .headers((header) -> header.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));
        return http.build();
    }
}
