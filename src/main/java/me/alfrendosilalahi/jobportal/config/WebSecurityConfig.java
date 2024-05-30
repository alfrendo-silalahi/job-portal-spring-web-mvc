package me.alfrendosilalahi.jobportal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import lombok.RequiredArgsConstructor;
import me.alfrendosilalahi.jobportal.service.CustomUserDetailsService;

@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    private final AuthenticationSuccessHandler authenticationSuccessHandler;

    private final String[] publicUrl = {
            "/",
            "/global-search/**",
            "/register",
            "/register/**",
            "/webjars/**",
            "/resources/**",
            "/assets/**",
            "/css/**",
            "/summernote/**",
            "/js/**",
            "/*.css",
            "/*.js", 
            "/*.js.map",
            "/fonts**",
            "/favicon.ico",
            "/resources/**", 
            "/error"
    };
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(auth -> auth
                            .requestMatchers(publicUrl).permitAll()
                            .anyRequest().authenticated()
                )
                .authenticationProvider(authenticationProvider())
                .formLogin(form -> form
                        .loginPage("/login").permitAll()
                        .successHandler(authenticationSuccessHandler)
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                )
                .cors(Customizer.withDefaults())
                .build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(customUserDetailsService);
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
