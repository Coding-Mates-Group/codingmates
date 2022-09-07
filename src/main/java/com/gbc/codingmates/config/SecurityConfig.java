package com.gbc.codingmates.config;

import com.gbc.codingmates.jwt.JwtTokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@AllArgsConstructor
public class SecurityConfig {
    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/h2-console/**"
                , "/favicon.ico"
                , "/error"
                , "/front/auth/**");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors() //(1)
                .and()
                // token을 사용하는 방식이기 때문에 csrf를 disable합니다.
                .csrf().disable()

//                    .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)

                .exceptionHandling()
//                    .authenticationEntryPoint(jwtAuthenticationEntryPoint)
//                    .accessDeniedHandler(jwtAccessDeniedHandler)

                // 세션을 사용하지 않기 때문에 STATELESS로 설정
                .and()
                .sessionManagement() //(4)
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                // enable h2-console
                .and()
                .headers()
                .frameOptions()
                .sameOrigin()

                .and()
                .authorizeRequests()
                .antMatchers( "/admin/**").hasAnyRole("ADMIN")
                .antMatchers("/member/**").hasAnyRole("MEMBER")
                .antMatchers("/**").permitAll()
//                    any other requests need to be authenticated
                .anyRequest().authenticated()
                .anyRequest().permitAll()

                .anyRequest().authenticated()

                .and()
                .apply(new com.gbc.codingmates.jwt.JwtSecurityConfig(jwtTokenProvider));

        return httpSecurity.build();
    }
}
