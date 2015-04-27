package kr.tagnote.tag;

import java.util.List;

import kr.tagnote.Application;
import kr.tagnote.article.Article;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
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
	public void findAllTagArticle() {
		System.out.println(tagArticleService.findAll().getContent());
	}

	@Test
	@Transactional
	@Ignore
	public void findByTagIdAndArticleId() {
		Pageable pageable = new PageRequest(0, 100);
		System.out.println(tagArticleRepository.findByTagIdAndUserId(9, 2, pageable).getContent().size());
	}

	@Test
	@Transactional
	@Ignore
	public void findByTagAndArticle() {
		Tag tag = new Tag();
		Article article = new Article();

		tag.setTagId(12);
		article.setArtId(26);

		System.out.println(tagArticleRepository.findByArticleAndTag(article, tag).getTag().getName());
	}

	@Test
	public void findByTagNameAndEmailAndPage() {
		Pageable pageable = new PageRequest(0, 100);
		Page<TagArticle.Response> pages = tagArticleService.findByTagNameAndEmailAndPage("test", "admin1@naver.com",
				pageable);
		List<TagArticle.Response> tagArticles = pages.getContent();

		for (int i = 0; i < tagArticles.size(); i++) {
			System.out.println(tagArticles.get(i).getArticle().getTags().size());
		}
	}
}
