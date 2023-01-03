package com.nikhilsnayak3473.technoblog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.nikhilsnayak3473.technoblog.security.JwtAuthenticationEntryPoint;
import com.nikhilsnayak3473.technoblog.security.JwtAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

	private JwtAuthenticationEntryPoint authenticationEntryPoint;
	private JwtAuthenticationFilter authenticationFilter;

	public SecurityConfig(JwtAuthenticationEntryPoint authenticationEntryPoint,
			JwtAuthenticationFilter authenticationFilter) {
		super();
		this.authenticationEntryPoint = authenticationEntryPoint;
		this.authenticationFilter = authenticationFilter;
	}

	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.csrf().disable()
				.authorizeHttpRequests(auth -> auth.requestMatchers(HttpMethod.GET, "/api/v1/**").permitAll()
						.requestMatchers(HttpMethod.POST, "/api/v1/auth/**").permitAll()
						.requestMatchers(HttpMethod.GET, "/v3/api-docs/**").permitAll()
						.requestMatchers(HttpMethod.GET, "/swagger-ui.html").permitAll()
						.requestMatchers(HttpMethod.GET, "/swagger-ui/**").permitAll()
						.requestMatchers(HttpMethod.GET, "/swagger-resources/**").permitAll()
						.requestMatchers(HttpMethod.GET, "/webjars/**").permitAll().anyRequest().authenticated()

				).exceptionHandling(exception -> exception.authenticationEntryPoint(authenticationEntryPoint))
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)

		;

		return http.build();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {

		return configuration.getAuthenticationManager();

	}

}
