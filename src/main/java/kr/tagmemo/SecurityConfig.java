package kr.tagmemo;

import kr.tagmemo.security.TagMemoUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	TagMemoUserDetailsService tagMemoDetailsService;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(tagMemoDetailsService).passwordEncoder(passwordEncoder);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
		
		web.ignoring().antMatchers("/");
	}



	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();

	/*	http.authorizeRequests().antMatchers(HttpMethod.POST, "/accounts").permitAll().anyRequest().authenticated()
				.and().httpBasic();*/
	}
}
