package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(requests -> requests
        						.requestMatchers("/").anonymous()
        						.requestMatchers("/styles/login.css", "/styles/register.css", "/styles/index.css").anonymous()
        						.requestMatchers("/security/login.jsp").anonymous()
        						.requestMatchers("/security/register.jsp").anonymous()
                                
                                .anyRequest().authenticated())
                		.formLogin(form -> form
                				.loginPage("/index.jsp").permitAll()
                				.loginProcessingUrl("/login")
                				.defaultSuccessUrl("/games/home"))
                		
                		.exceptionHandling(handling -> handling.accessDeniedPage("/security/access_denied.jsp"))
                		.csrf(csrf -> csrf.disable())
                		.logout((logout) -> logout.logoutSuccessUrl("/"));
		return http.build();
	}

	@Bean
	AuthenticationManager authenticationManager(UserDetailsService userDetailsService,
			PasswordEncoder passwordEncoder) {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder);

		return new ProviderManager(authenticationProvider);
	}

	@Bean
	PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}