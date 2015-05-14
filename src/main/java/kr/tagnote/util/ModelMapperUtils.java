package kr.tagnote.util;

import java.util.List;

import kr.tagnote.article.Article;
import kr.tagnote.tag.Tag;
import kr.tagnote.tag.TagArticle;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeToken;
import org.modelmapper.spi.MappingContext;

public class ModelMapperUtils {
/*	public static ModelMapper newInstance() {
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
	}*/
}
