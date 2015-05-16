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
public class UserRepositoryTests {
	@Autowired
	UserRepository userRepository;
	@Autowired
	AuthRepository authRepository;
	
	@Test
	public void findByEmail(){
		assertNotNull(userRepository.findByEmail("admin1@naver.com"));
	}
	
	@Test
	public void deleteByEmail(){
		assertTrue(0 == userRepository.deleteByEmail("admin8@naver.com"));
	}
	
	@Test
	public void isExistsByUid(){
		assertNotNull(userRepository.isExistsByUid("1"));
	}
}