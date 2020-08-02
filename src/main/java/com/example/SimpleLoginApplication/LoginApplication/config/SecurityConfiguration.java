package com.example.SimpleLoginApplication.LoginApplication.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/").permitAll().antMatchers("/loginuser").permitAll()
				.antMatchers("/registeruser").permitAll().antMatchers("/activation").permitAll()
				.antMatchers("/getQuestions").permitAll()
				.antMatchers("/v2/api-docs", "/swagger-resources/**", "/swagger-ui.html", "/webjars/**",
						"/swagger.json")
				.permitAll().antMatchers("/verifyAnswers").permitAll().antMatchers("/home/**")
				.hasAnyAuthority("SUPER_USER", "ADMIN_USER", "SITE_USER").anyRequest().authenticated().and().csrf()
				.disable().exceptionHandling().accessDeniedPage("/access-denied");

	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}
