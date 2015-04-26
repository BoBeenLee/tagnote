package kr.tagnote;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import kr.tagnote.article.Article;
import kr.tagnote.tag.Tag;
import kr.tagnote.tag.TagArticle;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeToken;
import org.modelmapper.spi.MappingContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import scala.annotation.meta.setter;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		final ModelMapper modelMapper = new ModelMapper();

		PropertyMap<Article, Article.Response> propertyArticleMap = new PropertyMap<Article, Article.Response>() {
			@Override
			protected void configure() {
				skip().setTags(null);
			}
		};
		PropertyMap<Tag, Tag.Reponse> propertyTagMap = new PropertyMap<Tag, Tag.Reponse>() {
			@Override
			protected void configure() {
			}
		};
		PropertyMap<TagArticle, TagArticle.Response> propertyTagArticleMap = new PropertyMap<TagArticle, TagArticle.Response>() {
			@Override
			protected void configure() {
			}
		};
		
		modelMapper.addMappings(propertyArticleMap);
		modelMapper.addMappings(propertyTagMap);
		modelMapper.addMappings(propertyTagArticleMap);

		Converter<Article, Article.Response> converter = new Converter<Article, Article.Response>() {
			@Override
			public Article.Response convert(MappingContext<Article, Article.Response> context) {
				Article article = context.getSource();

				List<TagArticle.Response> responses = modelMapper.map(article.getTagArticles(), new TypeToken<List<TagArticle.Response>>() {
				}.getType());
				
				Article.Response response = new Article.Response();
				response.setTags(responses);
				return response;
			}
		};
		modelMapper.addConverter(converter);
		return modelMapper;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new StandardPasswordEncoder();
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}
	/*
	 * @Bean InternalResourceViewResolver internalResourceViewResolver () {
	 * InternalResourceViewResolver viewResolver = new
	 * InternalResourceViewResolver(); viewResolver.setPrefix("/WEB-INF/view/");
	 * viewResolver.setSuffix(".jsp"); return viewResolver; }
	 */
}