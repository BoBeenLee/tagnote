package kr.tagnote.tag;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository  extends JpaRepository<Tag, Integer> {
	public Tag findByName(String name);
	@Transactional
	public int deleteByName(String name);
}