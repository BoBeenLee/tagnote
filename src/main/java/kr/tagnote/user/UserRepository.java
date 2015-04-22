package kr.tagnote.user;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
	public User findByEmail(String email);
	@Transactional
	public Long deleteByEmail(String email);
}
