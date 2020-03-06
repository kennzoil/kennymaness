package com.kennymaness.kennymaness.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    MyUserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/admin").hasRole("ADMIN")
//                .antMatchers("/user", "/blog/**").hasRole("USER")
//                .antMatchers(HttpMethod.POST, "/blog/add").hasRole("ADMIN")
//                .antMatchers(HttpMethod.POST, "/registration/**").permitAll()
//                .antMatchers(
//                        "/",
//                        "src/main/resources/static/images/**",
//                        "src/main/resources/static/script.js",
//                        "src/main/resources/static/styles.css"
//                ).permitAll()
//                .and().formLogin()
//                .loginPage("/login").permitAll()
////                .loginProcessingUrl("/login/process")
//                .and().logout()
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                .and().csrf().disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
//                .maximumSessions(1);

        http.authorizeRequests()
                .antMatchers("/", "/blog").permitAll()
//                .antMatchers("/blog").hasRole("USER")
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .and().csrf().disable()
                .sessionManagement().maximumSessions(1);

    }
}