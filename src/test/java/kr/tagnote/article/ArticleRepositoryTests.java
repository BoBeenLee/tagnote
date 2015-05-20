package kr.tagnote.article;

import static org.junit.Assert.*;

import java.util.List;

import kr.tagnote.TagNoteApplication;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TagNoteApplication.class)
public class ArticleRepositoryTests {
	@Autowired
	ArticleRepository articleRepository;

	
}
