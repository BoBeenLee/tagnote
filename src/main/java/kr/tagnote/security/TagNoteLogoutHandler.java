package kr.tagnote.security;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.tagnote.user.User;
import kr.tagnote.user.UserService;
import kr.tagnote.util.HttpUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class TagNoteLogoutHandler implements LogoutHandler {
	@Autowired
	private UserService userService;
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication auth) {
		boolean isLogout = true;
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userService.findByEmail(email); 
		HashMap<String, String> params = new HashMap<String, String>();

		try {
			if(isLogout)
				request.logout();
		} catch (ServletException e) {
			e.printStackTrace();
		}
		
		// revoke token
		if(user.getType() != null){
			if(user.getType().equals("google")){
				params.put("token", user.getAccessToken());
				HttpUtils.getJson(restTemplate, User.Google.REQUEST_REVOKE, params);
			} else if(user.getType().equals("facebook")) {
				try {
					response.sendRedirect("/user/fblogout");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
