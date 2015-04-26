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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/article")
public class ArticleController {
	private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);
	@Autowired
	ArticleService articleService;

	@RequestMapping(value = "")
	public String main(@RequestParam("name") String name, Model model, Principal principal) {
		Pageable pageable = new PageRequest(0, 100);

		// logger.info("pageable: " + pageable.toString());
		Page<Article.Response> articles = articleService.findByPage(pageable);
		model.addAttribute("articles", articles);
		return "tag";
	}

	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String writeView(@RequestParam(value = "id", required = false, defaultValue = "0") long id, @RequestParam(value = "name", required = false) String name, Model model) {
		Article.Response article = new Article.Response();
		if(id != 0)
			article = articleService.findById(id);

//		logger.info("writeView : " + article.getTags() + " , " + article.getContent());
		// TODO 유저가 같은 유저인지 체크 해야하나?
		model.addAttribute("name", name);
		model.addAttribute("article", article);
		return "article";
	}

	// 같은 /write url로 할 경우, 에러발생함.
	@RequestMapping(value = "/write/submit", method = RequestMethod.GET)
	public String write(@ModelAttribute("article") Article.Request request, @RequestParam(value = "name", required = false) String name, Model model, Principal principal) {
		String response = (name != null)? "redirect:/tag?name=" + name : "redirect:/tag/list";
		
		articleService.insertArticle(request, principal);
		return response;
	}

	@RequestMapping(value = "/paging")
	public ModelAndView pagingMain() {
		ModelAndView mv = new ModelAndView("article");
		Pageable pageable = new PageRequest(1, 10);
		Page<Article.Response> articles = articleService.findByPage(pageable);

		mv.addObject("articles", articles);
		return mv;
	}

	@RequestMapping(value = "/remove")
	public String remove(@RequestParam("name") String tagName, @RequestParam("id") long id) {
		articleService.deleteById(id);
		return "redirect:/tag?name=" + tagName;
	}
}
