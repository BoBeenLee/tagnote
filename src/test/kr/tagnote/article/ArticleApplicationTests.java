package kr.tagnote.article;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import kr.tagnote.Application;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class ArticleApplicationTests {
	@Autowired
	ArticleRepository articleRepository;
	@Autowired
	ModelMapper modelMapper;

	@Test
	public void findAll() {
		Pageable pageable = new PageRequest(0, 10);

		List<Article> articles = articleRepository.findAll(pageable)
				.getContent();
		List<Article.Response> articleDtos = null;
		Page<Article.Response> articleResponse = null;

		articleDtos = modelMapper.map(articles,
				new TypeToken<List<Article.Response>>() {
				}.getType());
		articleResponse = new PageImpl<Article.Response>(articleDtos);
		
		System.out.println(articleResponse.getContent());
	}
	
	
/*
	static class SList {
		List<Integer> name;
	}

	static class DList {
		List<String> name;
	}

	// @Test
	public void shouldMapListToListOfDifferentTypes() {
		SList list = new SList();
		list.name = Arrays.asList(Integer.valueOf(1), Integer.valueOf(2),
				Integer.valueOf(3));
		Type targetListType = new TypeToken<DList>() {
		}.getType();

		DList d = modelMapper.map(list, targetListType);

		System.out.println(d.name);
		// assertEquals(d.name, Arrays.asList("1", "2", "3"));
	}*/
}
