package kr.tagnote.tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagService {
	@Autowired
	private TagRepository tagRepository;
	
	
	
}