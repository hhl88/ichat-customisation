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
                // GET CURRENT LOGGED IN USER
                .antMatchers(HttpMethod.GET,"/api/v1/auth/me").authenticated()
                // ...
                .anyRequest().permitAll();
    }
}