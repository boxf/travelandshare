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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userDetailsService;

    /**
     * Method used by the default implementation of authenticationManager() to attempt to obtain an AuthenticationManager.
     * @param auth AuthenticationManagerBuilder allow for easily building in memory authentication, and adding AuthenticationProviders's.
     * @throws Exception
     */
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    /**
     * Method permit to configure the HttpSecurity. csrf().disable() permit to prevent some web attack. AuthenticationProvider permit to perform authentication.
     * Configuration of Login() : formLogin() specifies to support form based authentication, the URL used for login is determinate in loginProcessingUrl(). successHandler() is call if the authenticate is done, and failureHandler() is call if authenticate failed.
     * Configuration of logout() : if the logout success invalidation of the session.
     * Configuration of pages can be access if we are authenticated or not with antMatchers("URL").permitAll() and pages can't be access only if we are authenticated with antMatchers("URL").authenticated()
     * @param httpSecurity
     * @throws Exception
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
    }

    private class AuthenticationLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{
        /**
         * Method called when a user has been successfully authenticated
         * @param request
         * @param response
         * @param authentication
         * @throws IOException
         * @throws ServletException
         */
        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException{
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }

    private class AuthenticationLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {
        /**
         * Method called when a user has been successfully logout
         * @param request
         * @param response
         * @param authentication
         * @throws IOException
         * @throws ServletException
         */
        public void onLogoutSuccess (HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException{
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }

    /**
     *
     * @return provider
     */
    @Bean
    public AuthenticationProvider getProvider(){
        AppAuthProvider provider = new AppAuthProvider();
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }
}
