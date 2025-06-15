package com.moduletwo.innopolis.security;

import com.moduletwo.innopolis.constants.RoleConstant;
import com.moduletwo.innopolis.security.CustomFailureHandler;
import com.moduletwo.innopolis.security.CustomSuccessHandler;
import com.moduletwo.innopolis.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@EnableWebSecurity
@Configuration
public class SecurityConfig  {
    @Autowired
    private UserServiceImpl userService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers(RoleConstant.ADMIN_API + "**").hasAnyRole("ADMIN")
                        .requestMatchers("/registration").permitAll()
                        .anyRequest()
                        .authenticated()
                ).formLogin().loginProcessingUrl("/auth")
                .successHandler(authSuccessHandler())
                .failureHandler(authFailureHandler())
                .and()
                .cors().disable()
                .csrf().disable().httpBasic();
        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userService);
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CustomSuccessHandler authSuccessHandler() {
        return new CustomSuccessHandler();
    }

    @Bean
    public CustomFailureHandler authFailureHandler() {
        return new CustomFailureHandler();
    }
}
