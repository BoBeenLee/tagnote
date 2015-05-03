package kr.tagnote.article;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import kr.tagnote.Application;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class ArticleServiceTests {
	@Autowired
	ArticleService articleService;
	
	Article article = null;
	Principal principal = null;
	
	@Before
	public void insert(){
		article = new Article();
		article.setSubject("testtesttest");
		article.setContent("testtesttesttest");
		article.setTagList(new ArrayList<String>(){{ add("tesT"); }});
		
		principal = new Principal() {
			@Override
			public String getName() {
				return "admin1@naver.com";
			}
		};
		
		articleService.saveArticle(article, principal);
	}
	
	
	@Test
	public void test(){
		System.out.println(article.getArtId());
	}
	
	@Test
	@Ignore
	public void findAll() {
		Pageable pageable = new PageRequest(0, 10);

		Page<Article> pages = articleService.findByPage(pageable);
		List<Article> articles = pages.getContent();

		for (int i = 0; i < articles.size(); i++) {
			System.out.println(articles.get(i).getTagArticles().get(0).getTag().getName());
		}
	}

	@Test
	@Ignore
	public void findOne() {
		Article article = articleService.findById(26);
		System.out.println(article.getTagArticles().get(0).getTag().getName());
	}

	@Test
	@Ignore
	public void findAll1() {
		Pageable pageable = new PageRequest(0, 10);

		Page<Article> pages = articleService.findByPage(pageable);
		List<Article> articles = pages.getContent();

		for (int i = 0; i < articles.size(); i++) {
			System.out.println(articles.get(i).getTagArticles().get(0).getTag().getName());
		}
	}

	@Test
	@Ignore
	public void findOne1() {
		Article article = articleService.findById(26);
		System.out.println(article.getTagArticles().get(0).getTag().getName());
	}
	
	@After
	public void delete(){
		articleService.deleteById(article.getArtId());
	}
}
