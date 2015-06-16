package kr.tagnote.tag;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import kr.tagnote.TagNoteApplication;
import kr.tagnote.article.Article;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TagNoteApplication.class)
public class TagRepositoryTests {
	@Autowired
	TagRepository tagRepository;
	@Autowired
	TagArticleRepository tagArticleRepository;
	
	Tag tag = null;
	
	@Before
	public void insert(){
		tag = new Tag();
		tag.setName("test1");
		tag.setColor("asdfbz");
		tagRepository.save(tag);
	}
	
	@Test
	@Ignore
	public void findByName(){
		assertNotNull(tagRepository.findByName("test1"));
	}

	@Test
	@Ignore
	public void findByNameContaining(){
	/*	System.out.println("findByNameContaining : ");
		List<Tag> tags = tagRepository.findByNameContaining("tes");
		for(Tag tag : tags)
			System.out.println(tag.getName());*/
		assertEquals(2, tagRepository.findByNameContaining("tes").size());
	}
	
	@Test
	@Ignore
	public void findByTagIdAndUserId(){
		int tagId = tagRepository.findByName("test1").getTagId();
		long userId = 1;
		Pageable pageable = new PageRequest(0, 100);
		
		assertEquals(0, tagArticleRepository.findByTagIdAndUserId(tagId, userId, pageable).getTotalPages());
	}
	
	@Test
	@Ignore
	public void findByTag(){
		Tag tag = tagRepository.findByName("test1");
		Pageable pageable = new PageRequest(0, 100);
		
		assertEquals(0, tagArticleRepository.findByTag(tag, pageable).getTotalPages());
	}
	
	@Test
	@Ignore
	public void findByArticleAndTag(){
		Article article = new Article(){{
			setArtId(1);
		}};
		Tag tag = tagRepository.findByName("test1");
		
		assertNull(tagArticleRepository.findByArticleAndTag(article, tag));
	}
	
	@Test
	@Ignore
	public void findByUserId(){
		System.out.println(tagRepository.findByUserId(2).size());
	}
	
	 @After
	public void delete() {
		tagRepository.deleteByName("test1");
	}
}
