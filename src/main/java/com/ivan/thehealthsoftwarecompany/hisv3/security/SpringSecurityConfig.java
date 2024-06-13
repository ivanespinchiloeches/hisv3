package com.ivan.thehealthsoftwarecompany.hisv3.security;

import com.ivan.thehealthsoftwarecompany.hisv3.service.impl.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static com.ivan.thehealthsoftwarecompany.hisv3.security.TokenJwtConfig.*;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig{

    @Bean
    UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                        .requestMatchers("/users").hasAnyAuthority(ROLE_USER, ROLE_CREATOR, ROLE_EDITOR, ROLE_ADMIN)
                        .requestMatchers("/users/new").hasAnyAuthority(ROLE_ADMIN, ROLE_CREATOR)
                        .requestMatchers("/users/edit/**").hasAnyAuthority(ROLE_ADMIN, ROLE_EDITOR)
                        .requestMatchers("/users/delete/**").hasAuthority(ROLE_ADMIN)
                        .anyRequest().authenticated()
                )
                .formLogin(formLogin -> {
                    formLogin.loginPage("/login");
                    formLogin.permitAll();
                    formLogin.defaultSuccessUrl("/users/");
                })
                .logout(LogoutConfigurer::permitAll)
                .exceptionHandling(eh -> eh.accessDeniedPage("/403"))
        ;

        return http.build();
    }
}
