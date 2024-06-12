package com.falcon.andres.productos.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .csrf(config -> config.disable()).httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/producto/lista").permitAll()
                .antMatchers("/swagger-ui/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                .and()
                .formLogin().successHandler(customAuthenticationSuccessHandler).permitAll()
                .and()
                .logout().permitAll();
    }

    @Autowired
    public void configureSecurityGlobal (AuthenticationManagerBuilder managerBuilder) throws Exception{
        managerBuilder.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder())
                .usersByUsernameQuery("SELECT u.username, u.password, u.enabled FROM USUARIO U WHERE u.username = ?")
                .authoritiesByUsernameQuery("SELECT u.username, r.ROL FROM ROL r INNER JOIN USUARIO u ON r.usuario_id = u.id_usuario WHERE u.username = ?");
    }
}
