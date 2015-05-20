package kr.tagnote.test;

import static org.junit.Assert.*;
import kr.tagnote.TagNoteApplication;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TagNoteApplication.class)
public class TestApplicationTests {
	@Autowired
	TestRepository testRepository;
	
	@Test
	public void insertTest(){
//		System.out.println(testRepository.findAll());
		kr.tagnote.test.Test test = new kr.tagnote.test.Test();
		test.setName("test");
		test.setValue(1);
		
		testRepository.save(test);
	}
}
