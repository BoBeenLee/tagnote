package kr.tagnote.tag;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import kr.tagnote.Application;
import kr.tagnote.article.Article;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class TagRepositoryTests {
	@Autowired
	TagRepository tagRepository;
	@Autowired
	TagArticleRepository tagArticleRepository;
	
	@Before
	public void setup(){
		Tag tag = new Tag();
		tag.setName("test1");
		tag.setColor("asdfbz");
		tagRepository.save(tag);
	}
	
	@Test
	public void findByName(){
		assertNotNull(tagRepository.findByName("test1"));
	}

	@Test
	public void findByNameContaining(){
	/*	System.out.println("findByNameContaining : ");
		List<Tag> tags = tagRepository.findByNameContaining("tes");
		for(Tag tag : tags)
			System.out.println(tag.getName());*/
		assertEquals(2, tagRepository.findByNameContaining("tes").size());
	}
	
	@Test
	public void findByTagIdAndUserId(){
		int tagId = tagRepository.findByName("test1").getTagId();
		long userId = 1;
		Pageable pageable = new PageRequest(0, 100);
		
		assertEquals(0, tagArticleRepository.findByTagIdAndUserId(tagId, userId, pageable).getTotalPages());
	}
	
	@Test
	public void findByTag(){
		Tag tag = tagRepository.findByName("test1");
		Pageable pageable = new PageRequest(0, 100);
		
		assertEquals(0, tagArticleRepository.findByTag(tag, pageable).getTotalPages());
	}
	
	@Test
	public void findByArticleAndTag(){
		Article article = new Article(){{
			setArtId(1);
		}};
		Tag tag = tagRepository.findByName("test1");
		
		assertNull(tagArticleRepository.findByArticleAndTag(article, tag));
	}
	
	@Test
	public void findByUserId(){
		System.out.println(tagRepository.findByUserId(2).size());
	}
	
	 @After
	public void deleteByName() {
		tagRepository.deleteByName("test1");
	}
}
