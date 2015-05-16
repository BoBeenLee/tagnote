package kr.tagnote.test;

import kr.tagnote.tag.TagController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/test")
public class TestController {
	private static final Logger logger = LoggerFactory
			.getLogger(TestController.class);

	/*@RequestMapping(value = "")
	public String main() {
		System.out.println("test: test");
		return "test";
	}*/
/*	@RequestMapping(value = "/post", method=RequestMethod.POST)
	public String post(@RequestParam("test") String test){
		logger.info("test post : " + test);
		return "test";
	}*/
}
