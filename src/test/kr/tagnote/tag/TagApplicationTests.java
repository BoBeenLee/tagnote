package kr.tagnote.tag;

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
public class TagApplicationTests {
	@Autowired
	TagRepository tagRepository;
	@Autowired
	TagArticleRepository tagArticleRepository;

//	@Test
	public void insertTag() {
		Tag tag = new Tag();

		tag.setName("test");
		tag.setColor("155555");

		tagRepository.save(tag);
	}

	@Test
	public void deleteByName() {
		tagRepository.deleteByName("test");
	}

}
