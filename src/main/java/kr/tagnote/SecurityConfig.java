package kr.tagnote;

import kr.tagnote.security.TagMemoUserDetailsService;

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
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	TagMemoUserDetailsService tagMemoDetailsService;
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

		// auth
		http.authorizeRequests().antMatchers("/resources/**").permitAll();

		http.authorizeRequests().antMatchers("/**")
				.access("hasRole('ROLE_USER')").antMatchers("/user/admin/**")
				.access("hasRole('ROLE_ADMIN')");
//		.anyRequest().authenticated()
//				.and().httpBasic();

		http.formLogin().loginPage("/user/login").usernameParameter("email")
				.passwordParameter("password").defaultSuccessUrl("/tag/list")
				.failureUrl("/user/login?status=error").and().logout()
				.logoutUrl("/user/logout").logoutSuccessUrl("/user/login");
	}
}
