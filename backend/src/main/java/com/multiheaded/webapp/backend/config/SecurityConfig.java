package com.multiheaded.webapp.backend.config;

// TODO Play with '@PreAuthorize', '@Secured' and '@RolesAllowed'

import com.multiheaded.webapp.backend.security.CustomUserDetailsService;
import com.multiheaded.webapp.backend.security.JwtAuthenticationEntryPoint;
import com.multiheaded.webapp.backend.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/*
    This configuration handles security and Jwt auth token
    JWT - Json web token (it is a standard). Signed token that we use to verify identity
        TODO Learn theory https://medium.com/@xoor/jwt-authentication-service-44658409e12c
    JWT token passed to server through "Authorization" header
    @EnableGlobalMethodSecurity - enables method security (for ex. "@Secured("ROLE_ADMIN") public User getAllUsers()")
        restricts method getAllUsers() to ADMIN role only.
    JSR 250 - java annotations specification (no idea)
*/

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    // Service that loads user's details
    @Autowired
    CustomUserDetailsService customUserDetailsService;

    // Return 401 when someone sniffs around
    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    /*
        Read JWT token from header, validate it, load user details, set user details into SecurityContext
        "SecurityContext" - Interface defining the minimum security information associated
            with the current thread of execution.
        We can also access the user details stored in the SecurityContext in our controllers
            to perform our business logic.
     */
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    // TODO Build JDBC authentication
    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(unauthorizedHandler)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/",
                        "/favicon.ico",
                        "/**/*.png",
                        "/**/*.gif",
                        "/**/*.svg",
                        "/**/*.jpg",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js")
                .permitAll()
                .antMatchers("/api/auth/**")
                .permitAll()
                .antMatchers("/api/user/checkUsernameAvailability", "/api/user/checkEmailAvailability")
                .permitAll()
                .anyRequest()
                .authenticated();

        // Add custom JWT security filter
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

    }
}
