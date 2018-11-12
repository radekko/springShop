package com.shop.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService myDBAauthenticationService;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(myDBAauthenticationService);
	}
	
	@Bean
	public AuthenticationTrustResolver getAuthenticationTrustResolver() {
	    return new AuthenticationTrustResolverImpl();
	}

	/*
	 Site locations:
		/register
		/main/displayCart
		/login
		/main/displayOffer
		/order/accomplished
		/admin
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		
		http.
			authorizeRequests()
				.antMatchers("/register","/login","/","/403").permitAll()
				.antMatchers("/main/**").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
				.antMatchers("/order/**","/admin/**").access("hasRole('ROLE_ADMIN')")
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.defaultSuccessUrl("/login")
				.loginProcessingUrl("/login")
				.failureUrl("/login?error=true")
				.usernameParameter("username")
				.passwordParameter("password")
				.and()
			.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/login")
				.and()
			.exceptionHandling().accessDeniedPage("/403");
	}
	
}
