package com.oop2.tim6.NakitWebTim6.config;

import com.oop2.tim6.NakitWebTim6.error.CustomAccessDeniedHandlerTim6;
import com.oop2.tim6.NakitWebTim6.service.UserSecurityServiceTim6;
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

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfigTim6 extends WebSecurityConfigurerAdapter {

    private UserSecurityServiceTim6 userService;
    private CustomAccessDeniedHandlerTim6 customAccessDeniedHandler;
    private CustomLoginSuccessfulHandlerTim6 loginSuccessfulHandler;
    private DataSource dataSource;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.authorizeRequests().antMatchers(HttpMethod.POST, "/saveKorisnik").permitAll();

        http.authorizeRequests().antMatchers("/dashboard","/korisnik/**","/oglas/**").access("hasRole('ROLE_USER')");

        http.authorizeRequests().antMatchers("/dashboardAdmin").access("hasRole('ROLE_ADMIN')")
                .and().exceptionHandling().accessDeniedHandler(customAccessDeniedHandler);



        http.authorizeRequests().and().formLogin()
                    .loginPage("/login")
                    .failureUrl("/login?error=true")
                    .usernameParameter("korisnickoIme")
                    .passwordParameter("lozinka")
                    .successHandler(loginSuccessfulHandler)

                .and()
                    .logout()
                    .logoutUrl("/logout");

        http
                .authorizeRequests()
                    .antMatchers("/","/fillData","/resources/**").permitAll();


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

   /* @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
    }*/

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception { auth.authenticationProvider(authenticationProvider()); }

    @Autowired
    public void setUserService(UserSecurityServiceTim6 userService) {
        this.userService = userService;
    }

    @Autowired
    public void setCustomAccessDeniedHandler(CustomAccessDeniedHandlerTim6 customAccessDeniedHandler) { this.customAccessDeniedHandler = customAccessDeniedHandler; }
    
    @Autowired
    public void setCustomLoginSuccessfulHandler(CustomLoginSuccessfulHandlerTim6 loginSuccessfulHandler) {this.loginSuccessfulHandler = loginSuccessfulHandler;}

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
