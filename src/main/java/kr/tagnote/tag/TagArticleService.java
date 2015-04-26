package kr.tagnote.tag;

import java.util.List;

import kr.tagnote.user.User;
import kr.tagnote.user.UserRepository;

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
public class TagArticleService {
	@Autowired
	private TagArticleRepository tagArticleRepository;
	@Autowired
	private TagRepository tagRepository;
	@Autowired
	private UserRepository userRepository;
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
	public Page<TagArticle.Response> findByTagNameAndEmailAndPage(String tagName, String email, Pageable pageable){
		Tag tag = tagRepository.findByName(tagName);
		User user = userRepository.findByEmail(email);
		
		List<TagArticle> tagArticles = tagArticleRepository.findByTagIdAndUserId(tag.getTagId(), user.getUserId(), pageable).getContent();
		List<TagArticle.Response> tagArticleDtos = null;
		Page<TagArticle.Response> pages = null;
		
		for(int i=0; i<tagArticles.size(); i++)
			tagArticles.get(i).getArticle().getTagArticles();
		tagArticleDtos = modelMapper.map(tagArticles,  new TypeToken<List<TagArticle.Response>>() {
		}.getType());
		
		pages = new PageImpl<TagArticle.Response>(tagArticleDtos);
		return pages;
	}
}
