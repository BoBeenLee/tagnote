package kr.tagnote.tag;

import static org.junit.Assert.*;
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
public class TagServiceTests {
	@Autowired
	TagService tagService;
	
	
	
	public void findByTagName(){
		
	}
	
	public void findByTagNameLike(){
		
	}
	
	 @Test
	public void getRandomColor() {
		System.out.println(CommonUtils.getRandomColor());
	}
}
