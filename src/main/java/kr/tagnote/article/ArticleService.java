package kr.tagnote.article;

import java.security.Principal;
import java.util.List;

import kr.tagnote.tag.Tag;
import kr.tagnote.tag.TagArticle;
import kr.tagnote.tag.TagArticleRepository;
import kr.tagnote.tag.TagRepository;
import kr.tagnote.tag.TagService;
import kr.tagnote.user.User;
import kr.tagnote.user.UserRepository;
import kr.tagnote.util.CommonUtils;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ArticleService {
	@Autowired
	private ArticleRepository articleRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TagService tagService;
	
	@Transactional
	public void saveArticle(Article article, Principal principal) {
		// add Article
		User user = userRepository.findByEmail(principal.getName());
		article.setUserId(user.getUserId());
		articleRepository.save(article);

		// add Tags
		// add TagArticles
		List<String> tags = article.getTagList();
		for (int i = 0; tags != null && i < tags.size(); i++) {
			String tagName = tags.get(i);
			Tag tag = tagService.findByTagName(tagName);
			TagArticle tagArticle = tagService.findByArticleAndTag(article, tag);

			if (tag == null) {
				tag = new Tag();
				tag.setName(tagName);
				tag.setColor(CommonUtils.getRandomColor());
				tagService.saveTag(tag);
			}

			if (tagArticle == null) {
				tagArticle = new TagArticle();
				tagArticle.setArticle(article);
				tagArticle.setTag(tag);
				tagService.saveTagArticle(tagArticle);
			}
		}
	}

	@Transactional
	public Page<Article> findByPage(Pageable pageable) {
		Page<Article> articles = articleRepository.findAll(pageable);
		return articles;
	}

	public boolean deleteById(long id) {
		articleRepository.delete(id);
		return true;
	}

	@Transactional
	public Article findById(long id) {
		Article article = articleRepository.findOne(id);
		return article;
	}
}
