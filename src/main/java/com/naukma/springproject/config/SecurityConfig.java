package com.naukma.springproject.config;

import com.naukma.springproject.service.CustomUserDetailsService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Order(1)
public class SecurityConfig {

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception
//    {
//        auth.inMemoryAuthentication().withUser("user").password(passwordEncoder().encode("password")).roles("USER");
//    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public UserDetailsService userDetailsService(){
        return new CustomUserDetailsService();
    }
//PasswordEncoderFactories.createDelegatingPasswordEncoder()
//    @Bean
//    public DaoAuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setPasswordEncoder(passwordEncoder());
//        authProvider.setUserDetailsService(userDetailsService());
//
//        return authProvider;
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http.authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .and().authorizeRequests()
                .antMatchers("/student/**").hasAnyRole("STUDENT","ADMIN")
                .and().authorizeRequests()
                .antMatchers("/organization/**").hasAnyRole("STUDENT","ADMIN")
                .and().authorizeRequests()
                .antMatchers("/project/**").hasAnyRole("ADMIN", "STUDENT")
                .and().authorizeRequests()
                .antMatchers("/**").permitAll()
                .and().formLogin()
                .and()
                .logout()
                .logoutSuccessUrl("/");


        return http.build();
    }


//    protected void configure(HttpSecurity http) throws Exception{
//        http.authorizeRequests()
//                .antMatchers("");
//
//    }
//








//
//    @Bean
//    public SecurityFilterChain filterChain1(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/student/*").hasAnyRole("STUDENT", "ADMIN")
//                .antMatchers("/student/register").permitAll()
//                .antMatchers("/admin/*").hasRole("ADMIN")
//                .antMatchers("/organization/*").hasAnyRole("ADMIN", "STUDENT")
//                .antMatchers("/organization/register").hasRole("ADMIN")
//                .antMatchers("/project/*").hasRole("STUDENT");
//        return http.build();
//    }
}
