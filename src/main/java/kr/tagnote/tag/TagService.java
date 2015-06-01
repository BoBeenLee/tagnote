package kr.tagnote.tag;

import java.util.List;

import kr.tagnote.article.Article;
import kr.tagnote.user.User;
import kr.tagnote.user.UserRepository;
import kr.tagnote.user.UserService;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TagService {
	@Autowired
	private TagRepository tagRepository;
	@Autowired
	private TagArticleRepository tagArticleRepository;

	@Autowired
	private UserService userService;
	
	public Tag findByTagName(String tagName) {
		Tag tag = tagRepository.findByName(tagName);
		return tag;
	}

	public List<Tag> findByNameContaining(String name) {
		List<Tag> tags = tagRepository.findByNameContaining(name); 
		return tags;
	}
	
	public TagArticle findByArticleAndTag(Article article, Tag tag){
		TagArticle tagArticle = tagArticleRepository.findByArticleAndTag(article, tag);
		return tagArticle;
	}
	
	public Tag saveTag(Tag tag){
		tag = tagRepository.save(tag);
		return tag;
	}
	
	public TagArticle saveTagArticle(TagArticle tagArticle){
		tagArticle = tagArticleRepository.save(tagArticle);
		return tagArticle;
	}
	
	public Page<TagArticle> findAll() {
		Pageable pageable = new PageRequest(0, 10000);
		return tagArticleRepository.findAll(pageable);
	}
	
	public List<Tag> findByEmail(String email){
		User user = userService.findByEmail(email);
		List<Tag> tags = tagRepository.findByUserId(user.getUserId());
		return tags;
	}
	
	@Transactional
	public Page<TagArticle> findByTagNameAndEmailAndPage(String tagName, String email, Pageable pageable){
		Tag tag = tagRepository.findByName(tagName);
		User user = userService.findByEmail(email);
		
		if(tag == null || user == null)
			return null;
		List<TagArticle> tagArticles = tagArticleRepository.findByTagIdAndUserId(tag.getTagId(), user.getUserId(), pageable).getContent();
		for(int i=0; i<tagArticles.size(); i++)
			tagArticles.get(i).getArticle().getTagArticles().size();
		Page<TagArticle> pages = new PageImpl<TagArticle>(tagArticles);

		return pages;
	}

	public List<Tag> findByEmailAndNameLike(String email, String name) {
		User user = userService.findByEmail(email);
		List<Tag> tags = tagRepository.findByUserIdAndNameLike(user.getUserId(), name);
		return tags;
	}
}
