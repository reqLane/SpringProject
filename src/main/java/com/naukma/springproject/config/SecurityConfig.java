package com.naukma.springproject.config;

import com.naukma.springproject.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public UserDetailsService userDetailsService(){
        return new CustomUserDetailsService();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http.authorizeRequests()
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .and().authorizeRequests()
                .antMatchers("/student/**").hasAnyAuthority("STUDENT","ADMIN")
                .and().authorizeRequests()
                .antMatchers("/organization/**").hasAnyAuthority("STUDENT","ADMIN")
                .and().authorizeRequests()
                .antMatchers("/project/**").hasAnyAuthority("ADMIN", "STUDENT")
                .and().authorizeRequests()
                .antMatchers("/**").permitAll()
                .antMatchers("/**").authenticated()
                .and()
                .formLogin()
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/homepage")
                .and()
                .logout()
                .logoutSuccessUrl("/");

        return http.build();
    }
}
