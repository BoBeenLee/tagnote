package kr.tagnote.tag;

import kr.tagnote.Application;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class TagArticleApplicationTests {
	@Autowired
	TagArticleService tagArticleService;
	
	@Autowired
	TagArticleRepository tagArticleRepository;
	
	@Test
	@Ignore
	public void findAllTagArticle(){
		System.out.println(tagArticleService.findAll().getContent());
	}
	
	@Test
	@Transactional
	public void findByTagAndArticle(){
		Pageable pageable = new PageRequest(0, 100);
		System.out.println(tagArticleRepository.findByTagIdAndUserId(9, 2, pageable).getContent().size());
	}
}
