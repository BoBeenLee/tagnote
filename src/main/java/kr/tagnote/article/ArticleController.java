package kr.tagnote.article;

import java.util.HashMap;
import java.util.stream.Collector;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/article")
public class ArticleController {
	private static final Logger logger = LoggerFactory
			.getLogger(ArticleController.class);
	@Autowired
	private ArticleRepository articleRepository;

	@RequestMapping(value = "")
	public String main() {
		return "redirect:/tag/list";
	}

	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String writeView() {
		return "article";
	}

	@RequestMapping(value = "/write/submit", method = RequestMethod.GET)
	public String write(@ModelAttribute("article") Article.ArticleDTO article) {
		logger.info("article : " + article);
		return "redirect:/tag/list";
	}

	@RequestMapping(value = "/paging")
	public ModelAndView pagingMain() {
		ModelAndView mv = new ModelAndView("article");

		Pageable pageable = new PageRequest(1, 10);

		Page<Article> articles = articleRepository.findAll(pageable);

//		articles.getContent().stream().map(post -> of(post, sss)).collect(Collector.toList());
		
		mv.addObject("articles", articles);
		return mv;
	}
}
