package kr.tagnote.tag;

import java.util.List;

import kr.tagnote.article.Article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagService {
	@Autowired
	private TagRepository tagRepository;
	@Autowired
	private TagArticleRepository tagArticleRepository;
	
	public Tag findByTagName(String tagName) {
		Tag tag = tagRepository.findByName(tagName);
		return tag;
	}

	public List<Tag> findByTagNameLike(String name) {
		List<Tag> tags = tagRepository.findByNameLike(name); 
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
}
