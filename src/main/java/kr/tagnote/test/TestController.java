package kr.tagnote.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {
	@RequestMapping("/")
	public String main() {
		System.out.println("test: test");
		return "test";
	}
}
