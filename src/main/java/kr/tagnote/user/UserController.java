package kr.tagnote.user;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserController {
	@RequestMapping(value = "/login", method=RequestMethod.GET)
	public String login() {
		return "login";
	}
	
	@RequestMapping(value = "/logout")
	public String logout() {
		return "redirect:/user/login";
	}
}
