package com.project.hm.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.project.hm.jwt.JwtFilter;

@Configuration
@EnableWebSecurity
public class security extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private JwtFilter jwtFilter;
	@Autowired
	private MyUserDetailsService details;

	/*
	 * @Bean AuthenticationProvider authenticationProvider() {
	 * DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
	 * provider.setUserDetailsService(userDetailsService);
	 * provider.setPasswordEncoder(encoder()); return provider; }
	 */
	public void configure(AuthenticationManagerBuilder authenBuilder) throws Exception {
		authenBuilder.userDetailsService(userDetailsService).passwordEncoder(encoder());
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider dao = new DaoAuthenticationProvider();

		dao.setUserDetailsService(details);
		dao.setPasswordEncoder(new BCryptPasswordEncoder());
		return dao;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/**").permitAll()
		.antMatchers("/api/room/**").permitAll()
				.and().exceptionHandling().and().sessionManagement();
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

		http.cors().disable();

	}

	@Bean
	PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();

	}

	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}
