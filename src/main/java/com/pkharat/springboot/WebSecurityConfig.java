package com.pkharat.springboot;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private DataSource dataSource;
	
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
        .jdbcAuthentication()
        .dataSource(dataSource)
        .passwordEncoder(passwordEncoder())
        .usersByUsernameQuery(
            "SELECT email, password,id from users where email = ?")
        .authoritiesByUsernameQuery("select email, 'ROLE_USER' from users where email=?");
		
		//ref- https://grobmeier.solutions/spring-security-5-using-jdbc.html
		//ref - https://stackoverflow.com/questions/36775601/spring-security-jdbc-authentication-without-authorization
        
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			//not login - block matched urls and send to login page
			.antMatchers("/users").authenticated()
			.anyRequest().permitAll()
			.and()
			//after login 
			.formLogin()
				.usernameParameter("email")
				.defaultSuccessUrl("/")
				.permitAll()
			.and()
			//logout action - where to go
			.logout().logoutSuccessUrl("/logout").permitAll();
	}
	

   
	
}

