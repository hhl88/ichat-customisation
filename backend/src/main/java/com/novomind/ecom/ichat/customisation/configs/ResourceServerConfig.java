package com.novomind.ecom.ichat.customisation.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    /**
     * Configure which endpoints require authentication
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.cors().disable().httpBasic().and()
                .authorizeRequests()
                .antMatchers("/", "/csrf", "/v2/api-docs", "/swagger-resources/configuration/ui", "/configuration/ui", "/swagger-resources", "/swagger-resources/configuration/security", "/configuration/security", "/swagger-ui.html", "/webjars/**").permitAll()
                // GET CURRENT LOGGED IN USER
                .antMatchers(HttpMethod.POST, "/api/v1/users").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/ichats/*").permitAll()

                .antMatchers(HttpMethod.GET, "/api/v1/frontends").authenticated()
                .antMatchers(HttpMethod.GET, "/api/v1/layouts").authenticated()
                .antMatchers(HttpMethod.GET, "/api/v1/layouts/*/backgroundImg").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/layouts/*/logoImg").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/v1/users/*").authenticated()
                .antMatchers(HttpMethod.GET, "/api/v1/auth/me").authenticated()
                // ...
                .anyRequest().permitAll();
    }
}