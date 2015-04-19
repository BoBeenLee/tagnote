package kr.tagmemo.article;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/article")
public class ArticleController {
	@RequestMapping(value = "")
	public String main(){
		return "article";
	}
}
