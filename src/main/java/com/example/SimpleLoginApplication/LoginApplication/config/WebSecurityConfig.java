package com.example.SimpleLoginApplication.LoginApplication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



@Configuration
@EnableWebSecurity
public class WebSecurityConfig implements WebMvcConfigurer {

	//private UserService userService;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder BCryptPasswordEncoder = new BCryptPasswordEncoder();
		return BCryptPasswordEncoder;
	}

	/*@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		
		httpSecurity.authorizeRequests().antMatchers("/sign-up/**", "/sign-in/**").permitAll().anyRequest()
		.authenticated().and().formLogin().loginPage("/sign-in").permitAll();
	
		
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
	    return super.authenticationManagerBean();
	}
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("admin").password("admin@123").roles("admin");
           }*/

}
