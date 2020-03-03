package com.kennymaness.kennymaness.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;


@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    @Bean
    public UserDetailsService userDetailsService() { return super.userDetailsService(); }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/user", "/blog/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(
                        "/",
                        "src/main/resources/static/images/**",
                        "src/main/resources/static/script.js",
                        "src/main/resources/static/styles.css"
                ).permitAll()
                .and().formLogin()
                .loginProcessingUrl("/perform_login")
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/", true)
                .failureUrl("/login.html?error=true")
                .and().csrf().disable()
                .sessionManagement().maximumSessions(1);
    }
}