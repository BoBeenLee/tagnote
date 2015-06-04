package kr.tagnote.file;

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
public class FileApplicationTests {
	@Autowired
	FileRepository testRepository;
	
	@Test
	public void test(){
		
	}
}
