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
                .antMatchers("/h2-console/**").hasAuthority("ADMIN")
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .antMatchers("/student/register-from-form/**").permitAll()
                .antMatchers("/student/**").hasAnyAuthority("STUDENT","ADMIN")
                .antMatchers("/organization/register-from-form", "/organization/register", "/organization/addStudent").hasAuthority("ADMIN")
                .antMatchers("/organization/**").hasAnyAuthority("STUDENT","ADMIN")
                .antMatchers("/project/addTo", "/project/addStudent").hasAuthority("ADMIN")
                .antMatchers("/project/**").hasAnyAuthority("STUDENT", "ADMIN")
                .antMatchers("/home", "/profile").authenticated()
                .antMatchers("/**").permitAll()
                .and()
                .formLogin()
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/home")
                .and()
                .logout()
                .logoutSuccessUrl("/login");

        http.csrf().disable();
        http.headers().frameOptions().disable();

        return http.build();
    }
}
