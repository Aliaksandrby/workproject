package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@ComponentScan(basePackages = {"com.example.demo"})
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .formLogin()
                .loginPage("/login.html")
                .failureUrl("/loginError.html")
                .defaultSuccessUrl("/")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .authorizeRequests()
                .antMatchers("/update/**","/delete/**","/create/**","/editUser/**").hasRole("ADMIN")
                .and()
                //.httpBasic()// for rest auth
                //.and()
                .exceptionHandling()
                //.accessDeniedPage("/403.html")
                .and()
                .csrf()
                .ignoringAntMatchers("/**");
    }
}

/*
    INSERT INTO t_role(id, name) VALUES (1, 'ROLE_ADMIN');
    INSERT INTO t_user(id, username,password) VALUES (1, 'admin','$2a$10$oLjEcISCg9p7FPicTn4bKOzC6EYYxSmXiid2UxdJILuQdlZYAx1Ba');
    INSERT INTO t_user_roles VALUES (1,1);
*/

