package com.projects.travelandshare.security;

import com.projects.travelandshare.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Pradeau Marion
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userDetailsService;

    /**
     * Method used by the default implementation of authenticationManager() to attempt to obtain an AuthenticationManager.
     * @param auth AuthenticationManagerBuilder allow for easily building in memory authentication, and adding AuthenticationProviders's.
     * @throws Exception if the authentication is not build
     */
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    /**
     * Method permit to configure the HttpSecurity. csrf().disable() permit to prevent some web attack. AuthenticationProvider permit to perform authentication.
     * Configuration of Login.
     * Configuration of logout.
     * Configuration of pages can be access if we are authenticated or not and pages can't be access only if we are authenticated.
     * @param httpSecurity the HttpSecurity to modify.
     * @throws Exception The class Exception and its subclasses are a form of Throwable that indicates conditions that a reasonable application might want to catch.
     */
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(new Http403ForbiddenEntryPoint(){})
                .and()
                .authenticationProvider(getProvider())
                .formLogin()
                .loginProcessingUrl("/api/login")
                .successHandler(new AuthenticationLoginSuccessHandler())
                .failureHandler(new SimpleUrlAuthenticationFailureHandler())
                .and()
                .logout()
                .logoutUrl("/api/logout")
                .logoutSuccessHandler( new AuthenticationLogoutSuccessHandler())
                .invalidateHttpSession(true)
                .and()
                .authorizeRequests()
                .antMatchers("/api/login").permitAll()
                .antMatchers("/api/logout").permitAll()
                .antMatchers("/api/places").authenticated()
                .anyRequest().permitAll();
                httpSecurity.cors();
    }

    private class AuthenticationLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{
        /**
         * Method called when a user has been successfully authenticated
         * @param request the request which caused the successful authentication
         * @param response the response
         * @param authentication the Authentication object which was created during the authentication process.
         * @throws IOException Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O operations.
         * @throws ServletException Defines a general exception a servlet can throw when it encounters difficulty.
         */
        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException{
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }

    private class AuthenticationLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {
        /**
         * Method called when a user has been successfully logout
         * @param request the request which caused the successful logout
         * @param response the response
         * @param authentication the Authentication object which was created during the authentication process.
         * @throws IOException Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O operations.
         * @throws ServletException Defines a general exception a servlet can throw when it encounters difficulty.
         */
        public void onLogoutSuccess (HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException{
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }

    /**
     * Method permit to create a provider
     * @return provider
     */
    @Bean
    public AuthenticationProvider getProvider(){
        AppAuthProvider provider = new AppAuthProvider();
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }
}
