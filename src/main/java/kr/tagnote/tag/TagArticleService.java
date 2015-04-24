package kr.tagnote.tag;

import java.util.List;

import kr.tagnote.article.Article;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.reflect.TypeToken;

@Service
public class TagArticleService {
	@Autowired
	private TagArticleRepository tagArticleRepository;
	@Autowired
	private TagRepository tagRepository;
	@Autowired
	private ModelMapper modelMapper;

	public Page<TagArticle.Response> findAll() {
		Pageable pageable = new PageRequest(0, 10000);
		
		List<TagArticle> tagArticles = tagArticleRepository.findAll(pageable)
				.getContent();
		List<TagArticle.Response> tagArticleDtos = null;
		Page<TagArticle.Response> pages = null;
		
		tagArticleDtos = modelMapper.map(tagArticles,
				new TypeToken<List<TagArticle.Response>>() {
				}.getType());
		pages = new PageImpl<TagArticle.Response>(tagArticleDtos);
		return pages;
	}

	@Transactional
	public Page<TagArticle.Response> findByTagNameAndPage(String name, Pageable pageable){
		Tag tag = tagRepository.findByName(name);
		List<TagArticle> tagArticles = tagArticleRepository.findByTag(tag, pageable).getContent();
		List<TagArticle.Response> tagArticleDtos = null;
		Page<TagArticle.Response> pages = null;
		
		tagArticleDtos = modelMapper.map(tagArticles,  new TypeToken<List<TagArticle.Response>>() {
		}.getType());
		
		pages = new PageImpl<TagArticle.Response>(tagArticleDtos);
		return pages;
	}
	
}
