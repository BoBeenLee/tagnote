package kr.tagnote.tag;

import java.security.Principal;
import java.util.List;

import kr.tagnote.user.User;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/tag")
public class TagController {
	private static final Logger logger = LoggerFactory.getLogger(TagController.class);

	@Autowired
	private TagService tagService;
	@Autowired
	private ModelMapper modelMapper;

	@RequestMapping(value = "/list")
	public String main(Model model, Principal principal) {
		List<Tag> tags = tagService.findByEmail(principal.getName());
		List<Tag.Reponse> responses = null;
		
		responses = modelMapper.map(tags, new TypeToken<List<Tag.Reponse>>() {
		}.getType());

		model.addAttribute("tags", responses);
		return "main";
	}

	@RequestMapping(value = "")
	public String tag(@RequestParam("name") String tagName, Model model, Principal principal) {
		Pageable pageable = new PageRequest(0, 100);

		// logger.info("tag : " + tagName + " " + principal.getName());
		Tag tag = tagService.findByTagName(tagName);
		List<TagArticle> tagArticles = tagService.findByTagNameAndEmailAndPage(tagName, principal.getName(), pageable).getContent();
		List<TagArticle.Response> tagArticleDtos = null;
		Page<TagArticle.Response> responses = null;
		
		tagArticleDtos = modelMapper.map(tagArticles, new TypeToken<List<TagArticle.Response>>() {
		}.getType());
		for (int i = 0; i < tagArticles.size(); i++) {
			List<TagArticle.Response> tags = modelMapper.map(tagArticles.get(i).getArticle().getTagArticles(),
					new TypeToken<List<TagArticle.Response>>() {
					}.getType());
			tagArticleDtos.get(i).getArticle().setTags(tags);
		}
		responses =  new PageImpl<TagArticle.Response>(tagArticleDtos);

		model.addAttribute("tag", tag);
		model.addAttribute("tagArticles", responses);
		return "tag";
	}

	@RequestMapping(value = "/ajax")
	@ResponseBody
	public List<Tag.Reponse> ajaxTag(@RequestParam("name") String name) {
		List<Tag> tags = tagService.findByNameContaining(name);
		List<Tag.Reponse> responses = modelMapper.map(tags, new TypeToken<List<Tag.Reponse>>() {
		}.getType());

		
		
		return responses;
	}
}
