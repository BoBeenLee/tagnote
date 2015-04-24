package kr.tagnote.tag;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TagArticleRepository  extends PagingAndSortingRepository<TagArticle, Long> {
	public Page<TagArticle> findByTag(Tag tag, Pageable pageable);
}
