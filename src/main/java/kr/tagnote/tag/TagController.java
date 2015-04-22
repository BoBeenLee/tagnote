package kr.tagnote.tag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/tag")
public class TagController {
	private static final Logger logger = LoggerFactory.getLogger(TagController.class);

	@Autowired
	private TagRepository tagRepository;
	
	@RequestMapping(value = "/list")
	public String main() {
		return "main";
	}

	@RequestMapping(value = "")
	public String tag() {
//		logger.info("testtestetestetset");
		return "tag";
	}
}
