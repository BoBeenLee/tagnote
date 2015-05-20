package kr.tagnote.user;

import static org.junit.Assert.*;

import javax.transaction.Transactional;

import kr.tagnote.TagNoteApplication;
import kr.tagnote.util.CommonUtils;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TagNoteApplication.class)
public class UserServiceTests {
	@Autowired
	UserService userService;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	UserRepository userRepository;
	
	@Test
	public void findByEmail(){
		String email = "tsettest";
		assertNull(userService.findByEmail(email));
	}
	
	@Test
	public void deleteByEmail(){
		assertTrue(userService.deleteByEmail("admin11@naver.com"));
	}
	
	@Test
	public void saveUser(){
		User user = new User();
		
		user.setEmail("admin11@naver.com");
		user.setPassword(passwordEncoder.encode("1234"));
		user.setAuth(new Auth(){{ setAuthId(1);}}); 
		// authRepository.findOne(1)
		user = userService.saveUser(user);
		System.out.println(user);
//		assertNotEquals(0, user.getUserId());
	}
	
	@Test
	public void updateUser(){
		User user = userService.findByEmail("admin1@naver.com");
		user.setPassword(passwordEncoder.encode("1234"));
		
		userService.saveUser(user);
	}
	
	@Test
	public void findByUid(){
		assertNotNull(userService.findByUid("1"));
	}
	
	@Test
	public void isExistsByUid(){
		assertTrue(!userService.isExistsByUid("1DbTeNcrdI"));
	}
	
	@Test
	public void getRandomId(){
		assertNotNull(CommonUtils.getRandomId());
	}
}
