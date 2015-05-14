package kr.tagnote;

import kr.tagnote.security.TagNoteAuthenticationFailureHandler;
import kr.tagnote.security.TagNoteAuthenticationSuccessHandler;
import kr.tagnote.security.TagNoteLogoutHandler;
import kr.tagnote.security.TagNoteUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	TagNoteUserDetailsService tagMemoDetailsService;
	@Autowired
	TagNoteAuthenticationFailureHandler tagNoteAuthenticationFailureHandler;
	@Autowired
	TagNoteAuthenticationSuccessHandler tagNoteAuthenticationSuccessHandler;
	@Autowired
	TagNoteLogoutHandler tagNoteLogoutHandler;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.userDetailsService(tagMemoDetailsService).passwordEncoder(
				passwordEncoder);
	}

	/*
	 * @Override public void configure(WebSecurity web) throws Exception {
	 * super.configure(web);
	 * // web.ignoring().antMatchers("/"); }
	 */

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();

		// http.authorizeRequests().antMatchers("/**").permitAll();
		http.authorizeRequests().antMatchers("/user/login").permitAll(); // .anyRequest().authenticated().and().httpBasic();
		http.authorizeRequests().antMatchers("/user/register").permitAll();
		http.authorizeRequests().antMatchers("/user/social").permitAll();
		http.authorizeRequests().antMatchers("/user/fblogout").permitAll();
		
		// auth
		http.authorizeRequests().antMatchers("/resources/**").permitAll();

		http.authorizeRequests()
			.antMatchers("/**").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
			.antMatchers("/user/admin/**").access("hasRole('ROLE_ADMIN')");
//		.anyRequest().authenticated()
//				.and().httpBasic();
		
		http.formLogin().loginPage("/user/login").loginProcessingUrl("/user/login/submit").usernameParameter("email")
				.passwordParameter("password").successHandler(tagNoteAuthenticationSuccessHandler)
				.failureHandler(tagNoteAuthenticationFailureHandler)
				.and().logout().logoutUrl("/user/logout").addLogoutHandler(tagNoteLogoutHandler).logoutSuccessUrl("/user/login")
				.and();
//				.rememberMe();
	}
}
