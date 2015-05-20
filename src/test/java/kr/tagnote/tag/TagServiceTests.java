package kr.tagnote.tag;

import static org.junit.Assert.*;

import java.util.List;

import kr.tagnote.TagNoteApplication;
import kr.tagnote.article.Article;
import kr.tagnote.user.User;
import kr.tagnote.util.CommonUtils;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TagNoteApplication.class)
public class TagServiceTests {
	@Autowired
	TagService tagService;
	@Autowired
	ModelMapper modelMapper;

	public void findByTagName() {

	}

	@Test
	public void findByNameContaining() {
		List<Tag> tags = tagService.findByNameContaining("sdf");
		
		System.out.println(tags);
	}

	@Test
	public void getRandomColor() {
		assertNotNull(CommonUtils.getRandomColor());
	}

	@Test
	public void findByTagNameAndEmailAndPageResponse() {
		Pageable pageable = new PageRequest(0, 100);
		List<TagArticle> tagArticles = tagService.findByTagNameAndEmailAndPage("sdfsdf", "admin1@naver.com",
				pageable).getContent();
		List<TagArticle.Response> tagArticleDtos = null;

		tagArticleDtos = modelMapper.map(tagArticles, new TypeToken<List<TagArticle.Response>>() {
		}.getType());

		for (int i = 0; i < tagArticles.size(); i++) {
			List<TagArticle.Response> tags = modelMapper.map(tagArticles.get(i).getArticle().getTagArticles(),
					new TypeToken<List<TagArticle.Response>>() {
					}.getType());
			tagArticleDtos.get(i).getArticle().setTags(tags);
		}
		
		for(int i=0; i<tagArticleDtos.size(); i++)
			assertTrue(tagArticleDtos.get(i).getArticle().getTags().size() >= 0);
		// System.out.println(tagArticles.size());
	}

	@Test
	@Transactional
	@Ignore
	public void findByTagIdAndArticleId() {
		Pageable pageable = new PageRequest(0, 100);
		// System.out.println(tagArticleRepository.findByTagIdAndUserId(9, 2,
		// pageable).getContent().size());
	}

	@Test
	@Transactional
	@Ignore
	public void findByTagAndArticle() {
		Tag tag = new Tag();
		Article article = new Article();

		tag.setTagId(12);
		article.setArtId(26);

		// System.out.println(tagArticleRepository.findByArticleAndTag(article,
		// tag).getTag().getName());
	}
}
