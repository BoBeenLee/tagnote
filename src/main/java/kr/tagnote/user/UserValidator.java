package kr.tagnote.user;

import kr.tagnote.user.User.Request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
	@Autowired
	UserService userService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(User.Request.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User.Request request = (User.Request) target;
		validateEmail(request, errors);
		validatePassword(request, errors);
	}

	private void validatePassword(Request request, Errors errors) {
		if(!request.getPassword().equals(request.getPassword1())){
			errors.reject("password_no_match", "Passwords do not match");
		}
	}

	private void validateEmail(Request request, Errors errors) {
		if(userService.findByEmail(request.getEmail()) != null){
			errors.reject("email_exists", "User with this email already exists");
		}
	}
}
