package kr.tagnote.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.tagnote.user.UserController;
import kr.tagnote.user.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
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

		email = request.getParameter("email");
		isEmail = (userService.findByEmail(email) != null);

		if (!isEmail)
			errorMsg =  "email";
		else
			errorMsg = "password";
		response.sendRedirect("/user/login?error=" + errorMsg);
	}
}
