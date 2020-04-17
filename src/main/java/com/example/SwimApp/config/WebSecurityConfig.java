package com.example.SwimApp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//all security logic mentioned here. for spring, login etc
//tells spring that its a configuration file. enable means that app is secured.
//how you secure it, spec in config method
//using BCryptPasswordEncoder which is a spring class that implements password encoder
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	//qualifier means im telling spring map user details 
	//service to user details service class means what class to look at
	@Qualifier("userDetailsServiceImpl")
	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	//every file in resource folder, ignore web security 
	//so app can access it without login
	//even when not logged in you want some css etc
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
	}
	
	//anything that comes after / must be authenticated
	//.and .form etc, when logging in tell spring what to 
	//open (/login) defined in user controller file
	//true = if login is succcessful open welcome page
	//when try to logout, go back to login
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    http.csrf().disable();
		http.authorizeRequests()
		//when / is entered past the localhost, actions need to be authorized
		.antMatchers("/").authenticated()
		.and()
		.formLogin()
		.loginPage("/login")
		.defaultSuccessUrl("/welcome", true)
		.permitAll()
		.and()
		.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login");
		//logout function  redirects user to login page
	}
	//for future custom authentication
	//if admin logs in you want to show them different page to student.
	@Bean
	public AuthenticationManager customAuthenticationManager() throws Exception {
		return authenticationManager();
	}
	//use encryption. you are saving an encrypted password
	//this logic is doing encryption on the password
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}
}
