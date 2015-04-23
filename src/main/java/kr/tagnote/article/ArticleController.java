package kr.tagnote.article;

import java.security.Principal;
import java.util.List;

import kr.tagnote.tag.Tag;
import kr.tagnote.tag.TagArticle;
import kr.tagnote.tag.TagArticleRepository;
import kr.tagnote.tag.TagRepository;
import kr.tagnote.user.User;
import kr.tagnote.user.UserRepository;
import kr.tagnote.util.CommonUtils;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.reflect.TypeToken;

@Controller
@RequestMapping("/article")
public class ArticleController {
	private static final Logger logger = LoggerFactory
			.getLogger(ArticleController.class);
	@Autowired
	ArticleService articleService;
	
	@RequestMapping(value = "")
	public String main() {
		return "redirect:/tag/list";
	}

	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String writeView() {
		return "article";
	}

	@RequestMapping(value = "/write/submit", method = RequestMethod.GET)
	public String write(@ModelAttribute("article") Article.Request request,
			Model model, Principal principal) {
		articleService.insertArticle(request, principal);
		return "redirect:/tag/list";
	}

	@RequestMapping(value = "/paging")
	public ModelAndView pagingMain() {
		ModelAndView mv = new ModelAndView("article");
		Pageable pageable = new PageRequest(1, 10);
		Page<Article.Response> articles = articleService.findByPage(pageable);
		
		mv.addObject("articles", articles);
		return mv;
	}
}
