package kr.tagnote.user;

import java.security.Principal;
import java.util.HashMap;

import javax.validation.Valid;

import kr.tagnote.common.Value;
import kr.tagnote.util.CommonUtils;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	private UserValidator userValidator;
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	PasswordEncoder passwordEncoder;

	@InitBinder("register")
	public void userBinder(WebDataBinder binder) {
		binder.addValidators(userValidator);
	}

	@RequestMapping(value = "/login")
	public String loginView() {
		return "login";
	}

	@RequestMapping(value = "/logout")
	public String logout() {
		return "redirect:/user/login";
	}

	@RequestMapping(value = "/register/submit")
	public String register(@Valid @ModelAttribute("register") User.Request request) {
		User user = modelMapper.map(request, User.class);
		user.setAuth(new Auth() {
			{
				this.setAuthId(Auth.Role.USER.ordinal());
			}
		});
		userService.saveUser(user);

		return "redirect:/user/login";
	}

	@RequestMapping(value = "/register")
	public String registerView(Model model) {
		logger.debug("registerView : ");
		model.addAttribute("register", new User.Request());
		return "register";
	}

	@RequestMapping(value = "/setting")
	public String settingView(Model model, Principal principal) {
		User user = userService.findByEmail(principal.getName());
		User.Response response = null;

		response = modelMapper.map(user, User.Response.class);

		model.addAttribute("user", response);
		return "setting";
	}

	@RequestMapping(value = "/setting/submit")
	public String setting(@ModelAttribute("user") User.Request request, Principal principal) {
		boolean isExists = true;
		User user = userService.findByEmail(principal.getName());
		isExists = userService.isExistsByUid(request.getUid());

		if (isExists && !(user.getUid().equals(request.getUid())))
			return "redirect:/setting?msg=uid";

		user.setUid(request.getUid());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		userService.saveUser(user);

		return "redirect:/tag/list";
	}

	@RequestMapping(value = "/id/get")
	@ResponseBody
	public Value<String> getId() {
		Value<String> response = new Value<String>();
		boolean isExists = true;
		String uid = "";

		do {
			uid = CommonUtils.getRandomId();
			isExists = userService.isExistsByUid(uid);
		} while (isExists);

		response.setValue(uid);
		return response;
	}
}
