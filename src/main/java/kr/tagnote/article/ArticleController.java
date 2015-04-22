package kr.tagnote.article;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/article")
public class ArticleController {
	@Autowired
	private ArticleRepository articleRepository;
	
	@RequestMapping(value = "")
	public String main() {
		return "article";
	}
	
	@RequestMapping(value="/paging")
	public ModelAndView pagingMain(){
		ModelAndView mv = new ModelAndView("article");
		
		Pageable pageable = new PageRequest(1, 10);

		Page<Article> articles = articleRepository.findAll(pageable);
		
		mv.addObject("articles", articles);
		return mv;
	}
	
	@RequestMapping(value = "test")
	@ResponseBody
	public String test() {
		return "test";
	}
}
