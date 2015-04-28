package kr.tagnote.user;

import static org.junit.Assert.*;

import javax.transaction.Transactional;

import kr.tagnote.Application;
import kr.tagnote.util.CommonUtils;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class UserControllerTests {
	@Autowired
	UserService userService;
	@Autowired
	UserRepository userRepository;
	@Autowired
	AuthRepository authRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Test
	public void getUser(){
//		System.out.println(userRepository.findByEmail("admin1@naver.com"));
		assertNotEquals(null, userRepository.findByEmail("admin1@naver.com"));
	}
	
	@Test
	public void findAllAuths(){
		assertNotNull(authRepository.findAll());
	}
	
	@Test
	public void getRandomId(){
		assertNotNull(CommonUtils.getRandomId());
	}
	
	@Test
	public void getId(){
		System.out.println(userService.findByEmail("admin8@naver.com"));
	}
	
	@Test
	@Ignore
	public void insertUser(){
		User user = new User();
		
		user.setEmail("admin9@naver.com");
		user.setPassword(passwordEncoder.encode("1234"));
		user.setAuth(new Auth(){{ setAuthId(1);}}); 
		// authRepository.findOne(1)
		user = userRepository.save(user);
		assertNotEquals(0, user.getUserId());
	}
	
	@Test
	@Ignore
	public void deleteByEmail(){
//		userRepository.delete((long) 10);
		long userId = userRepository.deleteByEmail("admin9@naver.com");
	}
}
