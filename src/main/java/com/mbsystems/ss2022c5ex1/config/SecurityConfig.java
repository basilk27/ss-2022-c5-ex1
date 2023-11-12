package com.mbsystems.ss2022c5ex1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .httpBasic()
                .and()
                .authorizeRequests()
//                    .anyRequest().authenticated()   //endpoint level authorization
//                .anyRequest().permitAll()
//                .anyRequest().hasAnyAuthority("read", "write")
//                .anyRequest().access("isAuthenticated() and hasAnyAuthority('read', 'write')")
                .mvcMatchers("/demo").hasAuthority("read")
                .anyRequest().authenticated()
                .and()
                .build();

        //matcher method + authorization rule
        //1. which matcher methods should you use and how - anyRequest(), mvcMatchers(), antMatchers(), regexMatchers()
        //2. how to apply different authorization rules
    }

    @Bean
    public UserDetailsService userDetailsService() {
        var userDetailMgr = new InMemoryUserDetailsManager();

        var currentUser = User.withUsername("bill")
                .password(passwordEncoder().encode("12345"))
                .authorities("read")
                .build();

        userDetailMgr.createUser(currentUser);

        var currentUser2 = User.withUsername("john")
                .password(passwordEncoder().encode("12345"))
                .authorities("write")
                .build();

        userDetailMgr.createUser(currentUser2);

        return userDetailMgr;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
