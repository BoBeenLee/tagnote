package kr.tagnote;

import java.util.List;

import kr.tagnote.article.Article;
import kr.tagnote.tag.Tag;
import kr.tagnote.tag.TagArticle;

import org.apache.catalina.connector.Connector;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeToken;
import org.modelmapper.spi.MappingContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class TagNoteApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(TagNoteApplication.class, args);
	}

	
	
	
	@Bean
	public ModelMapper modelMapper() {
		final ModelMapper modelMapper = new ModelMapper();

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
				response.setArtId(article.getArtId());
				response.setUserId(article.getUserId());
				response.setSubject(article.getSubject());
				response.setContent(article.getContent());
				response.setUpdated(article.getUpdated());

				return response;
			}
		};
		modelMapper.addConverter(converter);
		return modelMapper;
	}

	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new StandardPasswordEncoder();
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(TagNoteApplication.class);
	}
	
	/*
	 * @Bean InternalResourceViewResolver internalResourceViewResolver () {
	 * InternalResourceViewResolver viewResolver = new
	 * InternalResourceViewResolver(); viewResolver.setPrefix("/WEB-INF/view/");
	 * viewResolver.setSuffix(".jsp"); return viewResolver; }
	 */
}
