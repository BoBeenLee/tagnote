package kr.tagnote.user;

import static org.junit.Assert.*;
import kr.tagnote.Application;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class UserApplicationTests {
	@Autowired
	UserRepository userRepository;
	@Autowired
	AuthRepository authRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Test
	public void getUser(){
		assertEquals(null, userRepository.findByEmail("test"));
	}
	
	@Test
	public void findAllAuth(){
		assertNotNull(authRepository.findAll());
	}
	
	@Test
	@Ignore
	public void addUser(){
		User user = new User();
		
		user.setEmail("admin8@naver.com");
		user.setPassword(passwordEncoder.encode("1234"));
		user.setAuth(new Auth(){{ setAuthId(1);}}); 
		// authRepository.findOne(1)
		user = userRepository.save(user);
		assertNotEquals(0, user.getUserId());
	}
	
	@Test
	public void deleteByEmail(){
		userRepository.deleteByEmail("admin8@naver.com");
	}
}
