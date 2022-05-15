package com.taxes.sec;


import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private DataSource dataSource;
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new PasswordEncoder() {
	        @Override
	        public String encode(CharSequence charSequence) {
	            return getMd5(charSequence.toString());
	        }

	        @Override
	        public boolean matches(CharSequence charSequence, String s) {
	            return getMd5(charSequence.toString()).equals(s);
	        }
	    };
	}

	public static String getMd5(String input) {
	    try {
	        // Static getInstance method is called with hashing SHA
	        MessageDigest md = MessageDigest.getInstance("MD5");

	        // digest() method called
	        // to calculate message digest of an input
	        // and return array of byte
	        byte[] messageDigest = md.digest(input.getBytes());

	        // Convert byte array into signum representation
	        BigInteger no = new BigInteger(1, messageDigest);

	        // Convert message digest into hex value
	        String hashtext = no.toString(16);

	        while (hashtext.length() < 32) {
	            hashtext = "0" + hashtext;
	        }

	        return hashtext;
	    }

	    // For specifying wrong message digest algorithms
	    catch (NoSuchAlgorithmException e) {
	        System.out.println("Exception thrown"
	                + " for incorrect algorithm: " + e);
	        return null;
	    }
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		/*
		auth.inMemoryAuthentication()
		.withUser("admin").password("{noop}1234").roles("ADMIN","USER");
		
		auth.inMemoryAuthentication()
		.withUser("user").password("{noop}1234").roles("USER");
		*/
		
		auth.jdbcAuthentication()
		.dataSource(dataSource).usersByUsernameQuery("select username as principal,password as credentials, active from users where username = ?  ")
		.authoritiesByUsernameQuery("select username as principal,role from users_roles where username = ? ")
		.rolePrefix("ROLE_").passwordEncoder(passwordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.formLogin().loginPage("/login");
		/*http.authorizeRequests()
		.anyRequest().authenticated();*/
		http.authorizeRequests().antMatchers("/add","/delete","/save","/edit")
		.hasRole("ADMIN");
		
		http.authorizeRequests().antMatchers("/entreprises")
		.hasRole("USER");
		
		//http.exceptionHandling().accessDeniedPage("/403");
	}
}