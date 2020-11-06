package com.webservice.pokedex.config;

import com.webservice.pokedex.services.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true) //activates @Secured in the controller
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationEntryPoint entrypoint;

    @Autowired
    private UserDetailService userDetailService;

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailService);
        /*auth.inMemoryAuthentication()
                .withUser("admin")
                .password(passwordEncoder().encode("password")) //admin:password https://www.base64encode.org/ Basic + result from website
                .roles("USER", "ADMIN");*/
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .csrf().disable()
                .formLogin().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/").permitAll()
                .antMatchers(HttpMethod.GET, "/rest/v1/pokemon").permitAll()
                .antMatchers(HttpMethod.GET, "/rest/v1/item").permitAll()
                .antMatchers(HttpMethod.GET, "/rest/v1/location").permitAll()
                .antMatchers("/api").authenticated()
                .and()
                .httpBasic().authenticationEntryPoint(entrypoint)
                .and()
                .logout(l -> l.logoutSuccessUrl("/"));
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}



