package kr.tagnote.user;

import java.security.Principal;

import kr.tagnote.util.CommonUtils;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;
	@Autowired
	ModelMapper modelMapper;
	
	@RequestMapping(value = "/login", method=RequestMethod.GET)
	public String login() {
		return "login";
	}
	
	@RequestMapping(value = "/logout")
	public String logout() {
		return "redirect:/user/login";
	}
	
	@RequestMapping(value = "/setting")
	public String settingView(Model model){
		return "setting";
	}
	
	@RequestMapping(value = "/setting/submit")
	public String setting(User.Request request, Principal principal){
		
		
		return "redirect:/tag/list";
	}
	
	
	@RequestMapping(value = "/id/get")
	@ResponseBody
	public String getId(Principal principal){
		boolean isExists = true;
		String uid = "";
		User user = userService.findByEmail(principal.getName());
		
		do {
			uid = CommonUtils.getRandomId();
			isExists = userService.isExistsByUid(uid);
		} while(isExists);
		
		user.setUid(uid);
		userService.saveUser(user);
		return uid;
	}
}
