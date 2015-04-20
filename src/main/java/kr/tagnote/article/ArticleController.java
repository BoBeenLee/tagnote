package kr.tagnote.article;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/article")
public class ArticleController {
	@RequestMapping(value = "")
	public String main() {
		return "article";
	}

	@RequestMapping(value = "test")
	@ResponseBody
	public String test() {
		return "test";
	}
}
