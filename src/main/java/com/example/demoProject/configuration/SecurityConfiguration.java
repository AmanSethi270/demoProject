package com.example.demoProject.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.GET,"/users").permitAll()
                .antMatchers(HttpMethod.GET,"/users/id/{id}").permitAll()
                .antMatchers(HttpMethod.GET,"/users/email/{email}").permitAll()
                .antMatchers(HttpMethod.POST,"/signup").permitAll()
                .antMatchers(HttpMethod.POST,"/login").permitAll()
                .antMatchers(HttpMethod.GET,"/posts").permitAll()
                .antMatchers(HttpMethod.GET,"/users/id/{id}/posts").permitAll()
                .antMatchers(HttpMethod.GET,"/users/id/{id}/posts/{postId}").permitAll()
                .antMatchers(HttpMethod.POST,"/users/id/{id}/posts").permitAll()
                .antMatchers(HttpMethod.PUT,"/users/id/{id}/posts/{postId}").permitAll()
                .antMatchers(HttpMethod.DELETE,"/users/id/{id}/posts/{postId}").permitAll()
                .antMatchers(HttpMethod.GET,"/users/id/{id}/following").permitAll()
                .antMatchers(HttpMethod.GET,"/users/id/{id}/following/followingId/{followingId}").permitAll()
                .antMatchers(HttpMethod.POST,"/users/id/{id}/following/followingId/{followingId}").permitAll()
                .antMatchers(HttpMethod.GET,"/followings").permitAll()
                .antMatchers(HttpMethod.GET,"/users/id/{id}/followers").permitAll()
                .antMatchers(HttpMethod.DELETE,"/users/id/{id}/following/followingId/{followingId}").permitAll()
                .anyRequest().authenticated();
    }
}
