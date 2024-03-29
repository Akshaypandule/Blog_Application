//package com.blogApplication.config;
//
//import com.blogApplication.Security.CustomUserDetailsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//import static org.springframework.security.config.Customizer.withDefaults;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//    @Autowired
//    private CustomUserDetailsService customUserDetailsService;
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//        http
//                .csrf()
//                .disable()
//                .authorizeHttpRequests((authorize)->authorize.anyRequest().authenticated()).
//                httpBasic(Customizer.withDefaults());
//
//            return http.build();
//
//    }
//
//
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
//    }
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//
//
////    @Bean
////    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
////        http.authorizeRequests()
////                .anyRequest()
////                .authenticated()
////                .and()
////                .httpBasic();
////        return http.build();
////    }
//
//}
