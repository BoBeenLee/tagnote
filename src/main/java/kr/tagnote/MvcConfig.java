package kr.tagnote;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {
	@Bean
	public CharacterEncodingFilter characterEncodingFilter() {
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		filter.setEncoding("UTF-8");
		filter.setForceEncoding(true);
		return filter;
	}

	/*
	 * @Bean public CommonsMultipartResolver multipartResolver() {
	 * CommonsMultipartResolver resolver = new CommonsMultipartResolver();
	 * return resolver; }
	 */

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// resourceHandler 서버 url, resourceLocations 현재 파일 위치
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/").setCachePeriod(0);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// registry.addInterceptor(new
		// LoggingInterceptor()).addPathPatterns("/user/*");
		// registry.addInterceptor(new
		// TagNoteLoginHandler()).addPathPatterns("**/login/submit");
	}
}
