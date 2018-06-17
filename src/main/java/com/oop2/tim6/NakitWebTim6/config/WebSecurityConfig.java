package com.oop2.tim6.NakitWebTim6.config;

import com.oop2.tim6.NakitWebTim6.error.CustomAccessDeniedHandler;
import com.oop2.tim6.NakitWebTim6.service.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserSecurityService userService;
    private CustomAccessDeniedHandler customAccessDeniedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.authorizeRequests().antMatchers(HttpMethod.POST, "/saveKorisnik").permitAll();
        http.authorizeRequests().antMatchers("/dashboard").access("hasRole('ROLE_USER')");

        http.authorizeRequests().antMatchers("/dashboardAdmin").access("hasRole('ROLE_ADMIN')")
                .and().exceptionHandling().accessDeniedHandler(customAccessDeniedHandler);



        http.authorizeRequests().and().formLogin()//
                // Submit URL of login page.
                //.loginProcessingUrl("/j_spring_security_check") // Submit URL
                .loginPage("/login")//
                .defaultSuccessUrl("/dashboard")//
                .failureUrl("/login?error=true")//
                .usernameParameter("korisnickoIme")//
                .passwordParameter("lozinka")
                .and().logout().logoutUrl("/logout");

        http
                .authorizeRequests()
                    .antMatchers("/", "/user/**", "/admin/**","/resources/**").permitAll();
                    //.anyRequest()//.authenticated()

    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception { auth.authenticationProvider(authenticationProvider()); }

    @Autowired
    public void setUserService(UserSecurityService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setCustomAccessDeniedHandler(CustomAccessDeniedHandler customAccessDeniedHandler) { this.customAccessDeniedHandler = customAccessDeniedHandler; }
}