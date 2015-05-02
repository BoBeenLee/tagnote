package kr.tagnote.tag;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TagRepository  extends JpaRepository<Tag, Integer> {
	public Tag findByName(String name);
	public List<Tag> findByNameContaining(String name);
	@Transactional
	public int deleteByName(String name);
	@Query("SELECT DISTINCT t FROM TagArticle AS ta INNER JOIN ta.tag t INNER JOIN ta.article a WHERE a.userId = :userId")
	public List<Tag> findByUserId(@Param("userId") long userId);
}
