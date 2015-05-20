package kr.tagnote.tag;

import java.util.List;

import kr.tagnote.article.Article;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface TagArticleRepository extends PagingAndSortingRepository<TagArticle, Long> {
	@Query(value="SELECT ta FROM TagArticle AS ta INNER JOIN ta.tag t INNER JOIN ta.article a"
			+ " WHERE t.tagId = :tagId AND a.userId = :userId")
	public Page<TagArticle> findByTagIdAndUserId(@Param("tagId") int tagId, @Param("userId") long userId, Pageable pageable);
	public Page<TagArticle> findByTag(Tag tag, Pageable pageable);
	public TagArticle findByArticleAndTag(Article article, Tag tag);
}
