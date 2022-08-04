package com.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

//    // http 요청에 대한 보안설정
//    protected void configure(HttpSecurity http) throws Exception {
//
//    }

    // 비밀번호 암호화
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    // 스프링 시큐리티 5.7 버전부터는 WebSecurityConfigurerAdapter가 Deprecated 되었기 때문에
    // 아래와 같이SecurityFilterChain 타입의 빈으로 대체
    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                .disable();
//                .csrf()         // 스프링 시큐리티에서는 CSRF공격을 방어하기 위해서 POST방식의 데이터 전송에는 반드시 CSRF토큰이 있어야함
//                .disable();

        return http.build();
    }
}
