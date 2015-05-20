package kr.tagnote.user;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.security.Principal;

import javax.transaction.Transactional;

import kr.tagnote.TagNoteApplication;
import kr.tagnote.util.CommonUtils;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TagNoteApplication.class)
public class UserControllerTests {
	@Autowired
	UserController userController;

	@Test
	@Ignore
	public void getId(){
//		String uid = userController.getId();
//		assertNotNull(uid);
	}	
	
	/*	@Autowired
	protected WebApplicationContext wac;
	
	private MockMvc mockMvc;

	@Autowired
	Environment environment;

	@Before
	public void setup() throws Exception {
		this.mockMvc = webAppContextSetup(this.wac).alwaysExpect(
				status().isOk()).build();
	}*/
/*
	public  <T> void printJson(APICode<T> reqCode){
		try {
			this.mockMvc
			.perform(
					post("/skhu").contentType(
							MediaType.APPLICATION_JSON).content(
									JacksonUtils.<APICode<T>>objectToJson(reqCode).getBytes()))
			.andDo(print());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
}
