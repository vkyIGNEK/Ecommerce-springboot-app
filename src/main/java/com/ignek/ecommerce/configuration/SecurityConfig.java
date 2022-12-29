package com.ignek.ecommerce.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.ignek.ecommerce.service.CustomUserDetailService;

@Configuration
@EnableWebSecurity
@EnableGlobalAuthentication
public class SecurityConfig {

	@Autowired
	private GoogleOAuth2SuccessHandler googleOAuth2SuccessHandler;

	@Autowired
	private CustomUserDetailService customUserDetailService;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.authorizeHttpRequests().requestMatchers("/", "/shop/**", "/register").permitAll()
				.requestMatchers("/admin/**").hasRole("ADMIN").anyRequest().authenticated().and().formLogin()
				.loginPage("/login").permitAll().failureUrl("/login?error=true").defaultSuccessUrl("/")
				.usernameParameter("email").passwordParameter("password").and().oauth2Login().loginPage("/login")
				.successHandler(googleOAuth2SuccessHandler).and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
				.invalidateHttpSession(true).deleteCookies("JSESSIONID").and().exceptionHandling().and().csrf()
				.disable();

		http.authenticationProvider(authenticationProvider());
		return http.build();


	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	  public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
	    return authConfig.getAuthenticationManager();
	  }

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(customUserDetailService);
		provider.setPasswordEncoder(bCryptPasswordEncoder());
		return provider;
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().requestMatchers("/resources/**", "/static/**", "/images/**", "/productImages/**",
				"/css/**", "/js/**");
	}

}
