package kr.tagnote.tag;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository  extends JpaRepository<Tag, Integer> {
	public Tag findByName(String name);
	public List<Tag> findByNameLike(String name);
	@Transactional
	public int deleteByName(String name);
}
