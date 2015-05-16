package kr.tagnote.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.tagnote.user.Auth;
import kr.tagnote.user.User;
import kr.tagnote.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

@Component
public class TagNoteLoginFilter extends OncePerRequestFilter  {
	@Autowired
	private UserService userService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
/*	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		
		return super.attemptAuthentication(request, response);
	}*/

/*	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		User user = userService.findByEmail(email);
		
		System.out.println("preHandler : login");
		
		if(user == null && email != null && password != null){
			user = new User();
			user.setEmail(email);
			user.setPassword(passwordEncoder.encode(password));
			user.setAuth(new Auth(){{ setAuthId(Auth.Role.USER.ordinal()); }});
		}
		return true;
	}*/

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		String email = (request.getParameter("email") == null)? "" : request.getParameter("email");
		String password = request.getParameter("password");
		User user = userService.findByEmail(email);
		
		System.out.println("email : " + email + " password : " + password);
		
		if(user == null && email != null && password != null){
			user = new User();
			user.setEmail(email);
			user.setPassword(passwordEncoder.encode(password));
			user.setAuth(new Auth(){{ setAuthId(Auth.Role.USER.ordinal()); }});
			userService.saveUser(user);
		}
		chain.doFilter(request, response);
	}
}
