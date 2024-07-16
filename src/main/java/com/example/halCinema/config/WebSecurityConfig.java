//package com.example.halCinema.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//import com.example.halCinema.service.UserService;
//
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig {
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private PasswordEncoder bCryptPasswordEncoder;
//
//    public WebSecurityConfig() {
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//            .authorizeHttpRequests((authz) -> authz
//                .requestMatchers("/login").authenticated()
//                .anyRequest().permitAll()
//            )
//            .formLogin((form) -> form
//                .loginPage("/login")
//                .defaultSuccessUrl("/toppage", true)
//                .permitAll()
//            )
//            .logout((logout) -> logout
//                .permitAll()
//            );
//        return http.build();
//    }
//
//    @Bean
//    public DaoAuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(userService);
//        authProvider.setPasswordEncoder(bCryptPasswordEncoder);
//        return authProvider;
//    }
//}
