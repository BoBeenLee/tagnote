package kr.tagnote.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.tagnote.user.Auth;
import kr.tagnote.user.User;
import kr.tagnote.user.UserController;
import kr.tagnote.user.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class TagNoteAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	private static final Logger logger = LoggerFactory.getLogger(TagNoteAuthenticationFailureHandler.class);

	@Autowired
	private UserService userService;

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		String email, errorMsg;
		boolean isEmail = false;
/*
		User user = userService.findByEmail(email);
		password = passwordEncoder.encode(password);
		
		if(user != null && !user.getPassword().equals(password))
			return "login";
		if(user == null){
			user = new User(email, password);
			user.setAuth(new Auth() {
				{
					this.setAuthId(Auth.Role.USER.ordinal());
				}
			});
			user = userService.saveUser(user);
		}
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(user.getAuth().getName()));
		Authentication auth = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword(), authorities);
		SecurityContextHolder.getContext().setAuthentication(auth);
*/
		
		email = request.getParameter("email");
		isEmail = (userService.findByEmail(email) != null);

		if (!isEmail)
			errorMsg =  "email";
		else
			errorMsg = "password";
		response.sendRedirect("/user/login?error=" + errorMsg);
	}
}
