package kr.tagnote.article;

import static org.junit.Assert.*;
import java.util.List;

import kr.tagnote.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class ArticleApplicationTests {
	@Autowired
	ArticleService articleService;

	@Test
	public void findAll() {
		Pageable pageable = new PageRequest(0, 10);

		Page<Article.Response> pages = articleService.findByPage(pageable);
		List<Article.Response>articles = pages.getContent();
		
		for(int i=0; i<articles.size(); i++){
			System.out.println(articles.get(i).getTags().get(0).getTag().getName());
		}
	}
	
	@Test
	public void findOne() {
		Article.Response article = articleService.findById(25);
		System.out.println(article.getTags().get(0).getTag().getName());
	}
	
	@Test
	public void findAll1() {
		Pageable pageable = new PageRequest(0, 10);

		Page<Article.Response> pages = articleService.findByPage(pageable);
		List<Article.Response>articles = pages.getContent();
		
		for(int i=0; i<articles.size(); i++){
			System.out.println(articles.get(i).getTags().get(0).getTag().getName());
		}
	}
	
	@Test
	public void findOne1() {
		Article.Response article = articleService.findById(25);
		System.out.println(article.getTags().get(0).getTag().getName());
	}
	/*
	 * static class SList { List<Integer> name; }
	 * 
	 * static class DList { List<String> name; }
	 * 
	 * // @Test public void shouldMapListToListOfDifferentTypes() { SList list =
	 * new SList(); list.name = Arrays.asList(Integer.valueOf(1),
	 * Integer.valueOf(2), Integer.valueOf(3)); Type targetListType = new
	 * TypeToken<DList>() { }.getType();
	 * 
	 * DList d = modelMapper.map(list, targetListType);
	 * 
	 * System.out.println(d.name); // assertEquals(d.name, Arrays.asList("1",
	 * "2", "3")); }
	 */
}
