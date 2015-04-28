package kr.tagnote.tag;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagService {
	@Autowired
	private TagRepository tagRepository;
	@Autowired
	private ModelMapper modelMapper;
	
	public Tag.Reponse findByTagName(String tagName) {
		Tag tag = tagRepository.findByName(tagName);
		Tag.Reponse response = null;
		
		response = modelMapper.map(tag, Tag.Reponse.class); 
		return response;
	}

	public List<Tag.Reponse> findByTagNameLike(String name) {
		List<Tag> tags = tagRepository.findByNameLike(name); 
		List<Tag.Reponse> responses = null;
		
		responses = modelMapper.map(tags, new TypeToken<List<Tag.Reponse>>(){}.getType());
		return responses;
	}
}
