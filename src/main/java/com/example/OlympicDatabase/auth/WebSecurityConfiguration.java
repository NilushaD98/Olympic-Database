package com.example.OlympicDatabase.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private  PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().and()
                .authorizeRequests()
                .antMatchers("/api/v1/usersSingup/saveLocalUser","/api/v1/usersSingup/saveInternationalUser","/api/v1/admins/getAllVideos","/","index","/css/**","/js/**")
                .permitAll()
                .antMatchers("api/v1/admins/getAllVideos")
                .hasAnyRole("USER","ADMIN")
                .antMatchers("api/v1/users/**")
                .hasAnyRole("ADMIN","USER")
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic().and()
                .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/courses")
                    .loginProcessingUrl("api/v1/login").permitAll()
                    .passwordParameter("password")
                    .usernameParameter("username")
                    .successHandler((request,response, authentication)->{
                        response.setContentType("application/jason");
                        response.setCharacterEncoding("UTF-8");
                        response.getWriter().write("{\"message\": \"Login successful\"}");
                    })
                    .failureHandler((request, response, exception) -> {
                    // If the login fails, send a JSON response with an error message
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write("{\"message\": \"Invalid username or password\"}");
                    })
                .permitAll()
                    .and().rememberMe()
                    .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21))
                    .key("somethingverysecured")
                .and()
                .logout()
                    .logoutUrl("/api/v1/logout")
                    .logoutRequestMatcher(new AntPathRequestMatcher("/api/v1/logout","GET"))
                    .clearAuthentication(true)
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID","remember-me")
                    .permitAll()
                ;
    }
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}