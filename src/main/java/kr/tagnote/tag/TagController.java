package kr.tagnote.tag;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/tag")
public class TagController {
	private static final Logger logger = LoggerFactory.getLogger(TagController.class);

	@Autowired
	private TagService tagService;
	@Autowired
	private TagArticleService tagArticleService;
	
	@RequestMapping(value = "/list")
	public String main(Model model, Principal principal) {
		model.addAttribute("tags", tagArticleService.findAll());
		return "main";
	}

	@RequestMapping(value = "")
	public String tag(@RequestParam("name") String tagName, Model model, Principal principal) {
		Pageable pageable = new PageRequest(0, 100);
		
//		logger.info("tag : " + tagName + " " + principal.getName());
		Page<TagArticle.Response> tagArticles = tagArticleService.findByTagNameAndEmailAndPage(tagName, principal.getName(), pageable);
		Tag tag = tagService.findByTagName(tagName);
		
		model.addAttribute("tag", tag);
		model.addAttribute("tagArticles", tagArticles);
		return "tag";
	}
}
