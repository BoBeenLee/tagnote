package kr.tagnote.article;

import java.security.Principal;
import java.util.List;

import kr.tagnote.tag.Tag;
import kr.tagnote.tag.TagArticle;
import kr.tagnote.tag.TagArticleRepository;
import kr.tagnote.tag.TagRepository;
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
	private TagRepository tagRepository;
	@Autowired
	private TagArticleRepository tagArticleRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Transactional
	public void saveArticle(Article.Request request, Principal principal) {
		// add Article
		Article article = modelMapper.map(request, Article.class);
		User user = userRepository.findByEmail(principal.getName());
		article.setUserId(user.getUserId());
		articleRepository.save(article);

		// add Tags
		// add TagArticles
		List<String> tags = request.getTags();
		for (int i = 0; tags != null && i < request.getTags().size(); i++) {
			Tag tag = tagRepository.findByName(tags.get(i));
			TagArticle tagArticle = tagArticleRepository.findByArticleAndTag(article, tag);

			if (tag == null) {
				tag = new Tag();
				tag.setName(tags.get(i));
				tag.setColor(CommonUtils.getRandomColor());
				tagRepository.save(tag);
			}

			if (tagArticle == null) {
				tagArticle = new TagArticle();
				tagArticle.setArticle(article);
				tagArticle.setTag(tag);
				tagArticleRepository.save(tagArticle);
			}
		}
	}

	@Transactional
	public Page<Article.Response> findByPage(Pageable pageable) {
		List<Article> articles = articleRepository.findAll(pageable).getContent();
		List<Article.Response> articleDtos = null;
		Page<Article.Response> pages = null;

		articleDtos = modelMapper.map(articles, new TypeToken<List<Article.Response>>() {
		}.getType());

		pages = new PageImpl<Article.Response>(articleDtos);
		return pages;
	}

	public boolean deleteById(long id) {
		articleRepository.delete(id);
		return true;
	}

	@Transactional
	public Article.Response findById(long id) {
		Article article = articleRepository.findOne(id);
		Article.Response response = null;

		if (article != null) {
			response = modelMapper.map(article, Article.Response.class);
		}
		return response;
	}
}
