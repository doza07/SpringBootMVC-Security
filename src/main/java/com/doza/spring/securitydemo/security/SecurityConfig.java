package com.doza.spring.securitydemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;


@Configuration
public class SecurityConfig {

    // support jdbc
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {

        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        // define query to retrieve a user by username
        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "select user_id, pw, active from members where user_id=?");

        // define query to retrieve the authorities/roles by username
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "select user_id, role from roles where user_id=?");

        return jdbcUserDetailsManager;
    }



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(configure ->
                configure
                        .requestMatchers("/systems/**").hasRole("ADMIN")
                        .requestMatchers("/leaders/**").hasRole("MANAGER")
                        .requestMatchers("/home/**").hasRole("USER")
                        .anyRequest().authenticated()
        )
                .formLogin(form ->
                        form
                                .loginPage("/auth/myLoginPage")
                                .loginProcessingUrl("/authTheUser")
                                .permitAll()
                )
                .logout(logout ->
                        logout.permitAll()
                )
                .exceptionHandling(configure ->
                        configure.accessDeniedPage("/auth/access-denied")
                );


        return http.build();
    }

}



//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager() {
//
//        UserDetails daniil = User.builder()
//                .username("Daniil")
//                .password("{noop}Daniil")
//                .roles("USER", "MANAGER", "ADMIN")
//                .build();
//
//        UserDetails kristina = User.builder()
//                .username("Kristina")
//                .password("{noop}Kristina")
//                .roles("USER", "MANAGER")
//                .build();
//
//        UserDetails chok = User.builder()
//                .username("Chok")
//                .password("{noop}Chok")
//                .roles("USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(daniil, kristina ,chok);
//    }