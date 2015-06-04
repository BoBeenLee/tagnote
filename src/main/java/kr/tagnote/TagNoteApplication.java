package kr.tagnote;

import java.util.List;

import kr.tagnote.article.Article;
import kr.tagnote.file.TagFile;
import kr.tagnote.tag.Tag;
import kr.tagnote.tag.TagArticle;
import kr.tagnote.user.User;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeToken;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.web.client.RestTemplate;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@SpringBootApplication
public class TagNoteApplication extends SpringBootServletInitializer {
	@Value("${cloudinary.name}")
	String cloudName;
	@Value("${cloudinary.key}")
	String cloudKey;
	@Value("${cloudinary.secret}")
	String cloudSecret;
	
	public static void main(String[] args) {
		String webPort = System.getenv("PORT");
        if (webPort == null || webPort.isEmpty()) {
            webPort = "8080";
        }
        System.setProperty("server.port", webPort);
        
		SpringApplication.run(TagNoteApplication.class, args);
	}
	
	@Bean
	public Cloudinary cloudinary(){
		return new Cloudinary(ObjectUtils.asMap(
				  "cloud_name", cloudName,
				  "api_key", cloudKey,
				  "api_secret", cloudSecret));
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
		
		Converter<TagFile, TagFile.Response> tagFileconverter = new Converter<TagFile, TagFile.Response>() {
			@Override
			public TagFile.Response convert(MappingContext<TagFile, TagFile.Response> context) {
				TagFile file = context.getSource();
				TagFile.Response response = new TagFile.Response();
				
				response.setFileId(file.getFileId());
				response.setName(file.getName());
				response.setPublicId(file.getPublicId());
				response.setUrl(file.getUrl());
				response.setSize(file.getSize());
				response.setType(file.getType());
				response.setCreated(file.getCreated());
				
				return response;
			}
		};
		
		
		Converter<Article, Article.Response> converter = new Converter<Article, Article.Response>() {
			@Override
			public Article.Response convert(MappingContext<Article, Article.Response> context) {
				Article article = context.getSource();

				// Article의 lazy된 tagArticles의 depth 1만 호출한다.
				List<TagArticle.Response> responses = modelMapper.map(article.getTagArticles(),
						new TypeToken<List<TagArticle.Response>>() {
						}.getType());
				User.Response userResponse = modelMapper.map(article.getUser(), new TypeToken<User.Response>() {
						}.getType());
				Article.Response response = new Article.Response();
				
				response.setTags(responses);
				response.setArtId(article.getArtId());
				response.setUser(userResponse);
				response.setSubject(article.getSubject());
				response.setContent(article.getContent());
				response.setUpdated(article.getUpdated());
				
				return response;
			}
		};
		modelMapper.addConverter(converter);
		modelMapper.addConverter(tagFileconverter);
		return modelMapper;
	}

	@Bean
	public RestTemplate restTemplate() {
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
}
